package com.bank.manage.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bank.core.entity.BizException;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.DateUtils;
import com.bank.core.utils.HttpUtil;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.ProgramDao;
import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dos.PlayAreaMaterialDO;
import com.bank.manage.dos.ProgramDO;
import com.bank.manage.dos.StyleAreaDO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.Progame.PadProgramDto;
import com.bank.manage.dto.Progame.ProgramContent;
import com.bank.manage.dto.Progame.ProgramData;
import com.bank.manage.dto.Progame.ProgramPreviewDTO;
import com.bank.manage.dto.ProgramListDTO;
import com.bank.manage.dto.ProgramUpdate.ProgramMaterialDto;
import com.bank.manage.dto.ProgramUpdate.ProgramStyleDto;
import com.bank.manage.dto.ProgramUpdate.ProgramUpdateDto;
import com.bank.manage.service.PlayAreaMaterialService;
import com.bank.manage.service.ProgramService;
import com.bank.manage.util.ProgramUtil;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl extends ServiceImpl<ProgramDao, ProgramDO> implements ProgramService {

    @Resource
    private ProgramDao programDao;

    @Resource
    private PlayAreaMaterialService playAreaMaterialService;



    @Autowired
    private ConfigFileReader configFileReader;

    /**
     * 节目预览
     *
     * @param programId 节目编号
     * @param deviceId
     * @return
     */
    @Override
    public ProgramPreviewDTO getPreView(Integer programId, String deviceId) {
        ProgramPreviewDTO programPreviewDTO = getProgramPreviewDto(programId,deviceId);
        return programPreviewDTO;
    }

    /**
     * 获取已选择的节目列表
     *
     * @param programId 节目编号
     * @param deviceId  设备编号
     * @return
     */
    @Override
    public List<MaterialDTO> getMaterialList(Integer programId, String deviceId) {
        List<MaterialDTO> list = programDao.getMaterialList(programId, deviceId);
        //绑定 素材地址前缀
        if (list.size() > 0) {
            for (MaterialDTO item : list) {
                item.setMaterialPath(StrUtil.isNotBlank(item.getMaterialPath()) == true ? configFileReader.getHTTP_PATH() + item.getMaterialPath() : null);
            }
        }
        return list;
    }


    /**
     * 通过设备编号 获取节目编号
     *
     * @param programQueryByDeviceVo 查询参数
     * @return
     */
    @Override
    public IPage<ProgramListDTO> getProgramList(ProgramQueryByDeviceVo programQueryByDeviceVo) {
        Page<ProgramListDTO> page = new Page<>(programQueryByDeviceVo.getPageIndex(), programQueryByDeviceVo.getPageSize());
        IPage<ProgramListDTO> listpage = programDao.selectProgrameList(page, programQueryByDeviceVo);
        return listpage;
    }

    /**
     * 修改节目
     * 1.修改节目信息 （需注意时间精确到分钟）
     * 2.修改节目素材列表
     *  2.1 删除 已存在的节目列表
     *  2.2 保存 更新后的素材列表
     * 3.判断时间 小于当前时间 立即推送到MQ
     * @param programUpdateVo 节目参数
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean updatePrograme(ProgramUpdateVo programUpdateVo) {
        try {
            //开始时间
            String startTime = DateUtils.getProgrameDate(programUpdateVo.getStartTime());
            //结束时间
            String endTime = DateUtils.getProgrameDate(programUpdateVo.getEndTime());
            //1.更新节目信息
            ProgramDO programDO = ProgramDO.builder()
                    //设置节目编号
                    .programId(programUpdateVo.getProgramId())
                    //涉资节目名称
                    .programName(programUpdateVo.getProgramName())
                    //开始时间
                    .startTime(startTime)
                    //结束时间
                    .endTime(endTime)
                    .build();
            programDao.updateById(programDO);
            //2.1 删除 原来的节目列表
            QueryWrapper<PlayAreaMaterialDO> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("PROGRAM_ID",programUpdateVo.getProgramId());
            queryWrapper.eq("TERMINAL_NUM",programDO.getTerminalNum());
            playAreaMaterialService.remove(queryWrapper);
            //2.2 保存 更新后的节目列表
            List<PlayAreaMaterialDO> list=new ArrayList<>();
            if(programUpdateVo.getListPlayMaterial().size()>0){
                for (PlayMaterialVo item:programUpdateVo.getListPlayMaterial()){
                    PlayAreaMaterialDO playAreaMaterialDO=PlayAreaMaterialDO.builder()
                            //设备编号
                            .terminalNum(item.getTerminalNum())
                            //样式编号
                            .styleNum(item.getStyleNum())
                            //区域编号
                            .areaNum(item.getAreaNum())
                            //区域名称
                            .areaName(item.getAreaName())
                            //素材编号
                            .materialId(item.getMaterialId())
                            //排序
                            .sort(item.getSort())
                            //播放时间
                            .playTime(item.getPlayTime())
                            //素材类型
                            .type(item.getType())
                            //节目编号
                            .programId(item.getProgramId())
                            //区域类型
                            .areaType(item.getAreaType())
                            .build();
                    list.add(playAreaMaterialDO);
                }
                playAreaMaterialService.saveBatch(list);
                //判断时间大小 如果开始大于当前时间 立即推送
                if(DateUtils.conparDate(startTime, DateUtil.formatDateTime(new Date()),"yyyy-MM-dd HH:mm:ss")){
                    changeProgram(programUpdateVo.getProgramId(),programUpdateVo.getTerminalNum());
                }
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            if (e instanceof BizException) {
                throw new BizException(((BizException) e).getErrorMsg());
            } else {
                throw new BizException("更新节目失败");
            }
        }
    }

    /**
     * 切换节目
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    @Override
    public Boolean changeProgram(Integer programId,String deviceId) {
        try{
            List<ProgramVo> programVoList = playAreaMaterialService.queryProgramListByPromId(programId,deviceId);
            List<Map<String,Object>> listMap=ProgramUtil.getProgramMap(programVoList,configFileReader.getHTTP_PATH());
            for (Map<String,Object> item:listMap ) {
                HttpUtil.sendPost(configFileReader.getMESSAGE_PATH(),item);
            }
            return true;
        }catch (Exception e){
            throw new BizException("切换节目失败");
        }
    }

    /**
     * 查询可用节目列表
     * @param programQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<ProgramDO> selectUseProgramList(ProgramQueryVo programQueryVo) {
        Page<ProgramDO> page=new Page<>(programQueryVo.getPageIndex(),programQueryVo.getPageSize());
        QueryWrapper<ProgramDO> programDOQueryWrapper=new QueryWrapper<>();
        String currentTime=DateUtil.formatDateTime(new Date());
        //开始时间小于 当前时间
        programDOQueryWrapper.le("START_TIME",currentTime);
        //结束时间大于 当前时间
        programDOQueryWrapper.ne("END_TIME",currentTime);

        programDOQueryWrapper.eq("TERMINAL_NUM",programQueryVo.getDeviceNo());
        return programDao.selectPage(page,programDOQueryWrapper);
    }

    /**
     * PAD 查询节目详情
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    @Override
    public PadProgramDto selectPadProgram(Integer programId, String deviceId) {
        PadProgramDto padProgramDto=programDao.selectPadProgram(programId,deviceId);
        if(padProgramDto!=null){
            for(ProgramData data:padProgramDto.getData()){
                for (ProgramContent content:data.getContentList()){
                    content.setPath(StrUtil.isNotBlank(content.getPath()) == true ? configFileReader.getHTTP_PATH() + content.getPath() : null);
                }
            }
        }
        return padProgramDto;
    }

    /**
     * 获取节目样式
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    @Override
    public ProgramStyleDto getStyle(Integer programId, String deviceId) {
        return programDao.getStyle(programId,deviceId);
    }

    /**
     * 获取节目详细信息
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    @Override
    public ProgramUpdateDto getProgramInfo(Integer programId, String deviceId) {
        ProgramUpdateDto programUpdateDto=programDao.getProgramInfo(programId,deviceId);
        if(programUpdateDto!=null){
            if(programUpdateDto.getStyleArea()!=null){
                programUpdateDto.getStyleArea().setStylePath(StrUtil.isNotBlank(programUpdateDto.getStyleArea().getStylePath()) == true ? configFileReader.getHTTP_PATH() + programUpdateDto.getStyleArea().getStylePath() : null);
                if(programUpdateDto.getListMaterial().size()>0){
                    for (ProgramMaterialDto item:programUpdateDto.getListMaterial()){
                        for (MaterialDTO i:item.getListMaterial()) {
                                i.setMaterialPath(StrUtil.isNotBlank(i.getMaterialPath()) == true ? configFileReader.getHTTP_PATH() + i.getMaterialPath() : null);
                        }
                    }
                }
            }
        }
        return programUpdateDto;
    }

    /**
     * 获取模型
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    private ProgramPreviewDTO getProgramPreviewDto(Integer programId, String deviceId){
        ProgramPreviewDTO programPreviewDTO = programDao.getPreView(programId,deviceId);
        ///(ㄒoㄒ)/~~ 这是为素材 添加
        if (programPreviewDTO != null) {
            //转layout
            programPreviewDTO.setLayout(JSONArray.parseArray(programPreviewDTO.getStyle()));
            programPreviewDTO.setCode("000000");
            programPreviewDTO.setStylePath(StrUtil.isNotBlank(programPreviewDTO.getStylePath()) == true ? configFileReader.getHTTP_PATH() + programPreviewDTO.getStylePath() : null);
            if (programPreviewDTO.getData().size() > 0) {
                for (ProgramData item : programPreviewDTO.getData()) {
                    if (item.getContentList().size() > 0) {
                        for (ProgramContent content : item.getContentList()) {
                            content.setPath(StrUtil.isNotBlank(content.getPath()) == true ? configFileReader.getHTTP_PATH() + content.getPath() : null);
                        }
                    }
                }
            }
        }
        return programPreviewDTO;
    }

}

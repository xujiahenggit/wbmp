package com.bank.manage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dao.PartorlModualDao;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dos.PartorlContentDO;
import com.bank.manage.dos.PartorlModualDO;
import com.bank.manage.dos.PartorlProcessDO;
import com.bank.manage.dto.PartorlContentDto;
import com.bank.manage.dto.PartorlDto;
import com.bank.manage.dto.PartorlProveDto;
import com.bank.manage.service.NewProcessService;
import com.bank.manage.service.PartorlModualService;
import com.bank.manage.service.PartorlProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/11 11:28
 */
@Service
public class PartorlModualServiceImpl extends ServiceImpl<PartorlModualDao, PartorlModualDO> implements PartorlModualService {

    @Resource
    private PartorlModualDao partorlModualDao;

    @Resource
    private PartorlProcessService partorlProcessService;


    @Resource
    private NetUtil netUtil;

    /**
     * 获取巡查内容列表
     * 1.判断查询流程编号中是否有对应的巡查记录
     * 2.如果没有就初次返回
     *
     * @param processId 流程编号
     * @return
     */
    @Override
    public List<PartorlDto> getList(Integer processId) {
        List<PartorlDto> list = new ArrayList<>();
        PartorlProcessDO partorlProcessDO = partorlProcessService.getById(processId);
        if (partorlProcessDO != null && 0!=partorlProcessDO.getPartorlRecordId()) {
            list=partorlModualDao.getPartorlListByRecordIdList(partorlProcessDO.getPartorlRecordId());
            //绑定 证明文件 前缀
            if (list.size()>0){
                list=getList(list);
            }
        } else {
            List<PartorlModualDO> listModual = this.list();
            if (listModual.size() > 0) {
                for (PartorlModualDO item : listModual) {
                    PartorlDto partorlDto = new PartorlDto();
                    partorlDto.setPartorlModualId(item.getPartorlModualId());
                    partorlDto.setPartorlModualName(item.getPartorlModualName());
                    partorlDto.setPartorlModualSort(item.getPartorlModualSort());
                    partorlDto.setPartorlRecordId(0);
                    List<PartorlContentDto> listContents=partorlModualDao.SelectPartorlContentNoRecord(item.getPartorlModualId());
                    for(PartorlContentDto content:listContents){
                        content.setPartorlMark("");
                        content.setPartorlResult("0");
                        content.setListProve(new ArrayList<>());
                    }
                    partorlDto.setListContent(listContents);
                    list.add(partorlDto);
                }
            }
        }
        return list;
    }


    /**
     * 绑定 证明文件 前缀
     * @param list
     * @return
     */
    private List<PartorlDto> getList(List<PartorlDto> list){
        if(list.size()>0){
            for (PartorlDto item:list){
                if(item.getListContent().size()>0){
                    for(PartorlContentDto itemContent:item.getListContent()){
                        if(itemContent.getListProve().size()>0){
                            for (PartorlProveDto itemProve:itemContent.getListProve()){
                                itemProve.setPartorlProveFilePath(StrUtil.isNotBlank(itemProve.getPartorlProveFilePath())==false?"":this.netUtil.getUrlSuffix("")+itemProve.getPartorlProveFilePath());
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
}

package com.bank.manage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.manage.dao.DictionaryDao;
import com.bank.manage.dao.DictionaryItemDao;
import com.bank.manage.dos.DictionaryDO;
import com.bank.manage.dos.DictionaryItemDO;
import com.bank.manage.dto.DictionaryDto;
import com.bank.manage.service.DictionaryItemService;
import com.bank.manage.service.DictionaryService;
import com.bank.manage.vo.DictQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:30
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, DictionaryDO> implements DictionaryService {

    @Resource
    private DictionaryDao dictionaryDao;

    @Resource
    private DictionaryItemService dictionaryItemService;

    /**
     * 获取查询列表
     *
     * @param dictQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<DictionaryDO> getPage(DictQueryVo dictQueryVo) {
        QueryWrapper<DictionaryDO> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(dictQueryVo.getDictName())) {
            queryWrapper.like("DICT_NAME " , dictQueryVo.getDictName());
        }
        if (StrUtil.isNotBlank(dictQueryVo.getDictDescribe())) {
            queryWrapper.like("DICT_DESCRIBE " , dictQueryVo.getDictDescribe());
        }
        Page<DictionaryDO> page = new Page<>(dictQueryVo.getPageIndex(), dictQueryVo.getPageSize());
        return dictionaryDao.selectPage(page, queryWrapper);
    }

    /**
     * 批量删除数据字典
     * 1.删除 主表
     * 2.删除 从表
     * @param ids 字典ID 列表
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDict(List<Integer> ids) {
        try{
            if(ids.size()>0){
                //1.删除字典
                dictionaryDao.deleteBatchIds(ids);
                //2.删除字典项目
                dictionaryItemService.DeleteItemByDictIds(ids);
            }
            return true;
        }catch (Exception e){
            throw new BizException("删除失败");
        }
    }

    /**
     * 获取字典详细信息
     * @param dictId 字典ID
     * @return
     */
    @Override
    public DictionaryDto getDictinfo(Integer dictId) {
        return dictionaryDao.getDictinfo(dictId);
    }

    /**
     * 修改字典信息
     * 1.更新 字典信息
     * 2.删除 字典项目
     * 3.添加 字典项目
     * @param dictionaryDto 字典信息内容
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDictInfo(DictionaryDto dictionaryDto) {
        try{
            //1.更新字典信息
            DictionaryDO dictionaryDO=new DictionaryDO();
            dictionaryDO.setDictId(dictionaryDto.getDictId());
            dictionaryDO.setDictDescribe(dictionaryDto.getDictDescribe());
            dictionaryDao.updateById(dictionaryDO);
            //2.删除字典项目
            dictionaryItemService.deleteByDictId(dictionaryDto.getDictId());
            //3.保存字典项目
            if(dictionaryDto.getListItem().size()>0){
                for (DictionaryItemDO itemDO:dictionaryDto.getListItem()){
                    itemDO.setDictId(dictionaryDto.getDictId());
                }
                dictionaryItemService.saveBatch(dictionaryDto.getListItem());
            }
            return true;
        }catch (Exception e){
            throw new BizException("字典修改失败");
        }
    }

    /**
     * 新增字典信息
     * 1.查重
     * 2.新增
     * @param dictionaryDto 字典信息
     * @return
     */
    @Override
    public boolean saveDictInfo(DictionaryDto dictionaryDto) {
       try{
           //1.查重
           QueryWrapper<DictionaryDO> queryWrapper=new QueryWrapper<>();
           queryWrapper.eq("DICT_NAME",dictionaryDto.getDictName());
           int isExistSize=dictionaryDao.selectCount(queryWrapper);
            if(isExistSize>0){
                throw new BizException("添加失败，字典名称【"+dictionaryDto.getDictName()+"】重复!");
            }
           //3.保存字典项目
           DictionaryDO dictionaryDO=new DictionaryDO();
           dictionaryDO.setDictName(dictionaryDto.getDictName());
           dictionaryDO.setDictDescribe(dictionaryDto.getDictDescribe());
           dictionaryDao.insert(dictionaryDO);
           //4.保存字典项目
           if(dictionaryDto.getListItem().size()>0){
               for (DictionaryItemDO itemDO:dictionaryDto.getListItem()){
                   itemDO.setDictId(dictionaryDO.getDictId());
               }
               dictionaryItemService.saveBatch(dictionaryDto.getListItem());
           }
            return true;
       }catch (Exception e){
           if(e instanceof BizException){
               throw new BizException(((BizException) e).getErrorMsg());
           }else {
               throw new BizException("字典新增失败");
           }
       }
    }

    /**
     * 通过名称获取字典列表项
     * @param name 字典名称
     * @return
     */
    @Override
    public List<DictionaryItemDO> getDictinfoByName(String name) {
        List<DictionaryItemDO> list=new ArrayList<>();
        QueryWrapper<DictionaryDO> dictionaryDOQueryWrapper=new QueryWrapper<>();
        dictionaryDOQueryWrapper.eq("DICT_NAME",name);
        DictionaryDO dictionaryDO=dictionaryDao.selectOne(dictionaryDOQueryWrapper);
        if(dictionaryDO!=null){
            QueryWrapper<DictionaryItemDO> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("DICT_ID",dictionaryDO.getDictId());
            list=dictionaryItemService.list(queryWrapper);
        }
        return list;
    }
}

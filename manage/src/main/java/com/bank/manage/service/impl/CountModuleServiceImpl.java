package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dao.CountModuleTempDao;
import com.bank.manage.dos.CountModuleDO;
import com.bank.manage.dao.CountModuleDao;
import com.bank.manage.dos.CountModuleTempDO;
import com.bank.manage.dto.CountModuleDTO;
import com.bank.manage.dto.CountModuleTempDTO;
import com.bank.manage.service.CountModuleService;
import com.bank.manage.vo.CountModuleDetailVo;
import com.bank.manage.vo.CountModuleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  统计模块主表 服务实现类
 */
@Service
@Slf4j
public class CountModuleServiceImpl extends ServiceImpl<CountModuleDao, CountModuleDO> implements CountModuleService {

    @Autowired
    private CountModuleDao countModuleDao;

    @Autowired
    private CountModuleTempDao countModuleTempDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveCountModule(List<CountModuleVo> countModuleVo, TokenUserInfo tokenUserInfo) {
        try {
            Integer moduleYear = countModuleVo.get(0).getModuleYear();
            Map<String,Object> map = new HashMap<>();
            map.put("MODULE_YEAR",moduleYear);
            List<CountModuleDO> countModuleDOS = countModuleDao.selectByMap(map);
            if(CollectionUtil.isNotEmpty(countModuleDOS)){
                throw new BizException("该年份的统计模块已经创建，请直接修改或删除后创建！");
            }
            save( countModuleVo,tokenUserInfo);
            return true;
        } catch (Exception e) {
            log.error("统计模块保存失败！{}",e.getMessage());
            throw new BizException("统计模块保存失败！");
        }
    }

    @Override
    public IPage<CountModuleDTO> queryCountModulePage(PageQueryModel pageQueryModel) {
        Page<CountModuleDTO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        IPage<CountModuleDTO> page1 = countModuleDao.queryCountModulePage(page);
       /* List<CountModuleDTO> records = page1.getRecords();
        List<CountModuleDTO> pageList = new ArrayList<>();
        Map<Integer, List<CountModuleDTO>> map = records.stream().collect(Collectors.groupingBy(CountModuleDTO::getModuleYear));
        for (Map.Entry<Integer, List<CountModuleDTO>> entry : map.entrySet()) {
            List<CountModuleDTO> value = entry.getValue();
            pageList.add(value.get(0));
        }
        page1.setRecords(pageList);
        Map<String, Object> countModuleMap = countModuleDao.queryCountModuleByCount();
        page1.setTotal((Long) countModuleMap.get("MALL"));*/
        return page1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delCountModule(String moduleYear) {
        try {
            del(moduleYear);
            return true;
        } catch (Exception e) {
            log.error("删除统计模块失败！{}",e.getMessage());
            throw new BizException("删除统计模块失败！");
        }
    }

    @Override
    public List<CountModuleVo> getCountModule(String moduleYear) {
        List<CountModuleDetailVo> voList = countModuleDao.getCountModule(moduleYear);
        Map<String, List<CountModuleDetailVo>> map = voList.stream().collect(Collectors.groupingBy(CountModuleDetailVo::getOneModule));
        List<CountModuleVo> list = new ArrayList<>();
        for (Map.Entry<String, List<CountModuleDetailVo>> entry : map.entrySet()) {
            List<CountModuleDetailVo> value = entry.getValue();
            CountModuleVo vo = new CountModuleVo();
            List<CountModuleTempDTO> moduleTemp = new ArrayList<>();
            for (CountModuleDetailVo detailVo: value) {
                if(detailVo.getTwoModule() != null && detailVo.getTwoModuleScore() != null){
                    CountModuleTempDTO temp = new CountModuleTempDTO();
                    temp.setTwoModule(detailVo.getTwoModule());
                    temp.setTwoModuleScore(detailVo.getTwoModuleScore());
                    moduleTemp.add(temp);
                }
            }
            if(CollectionUtil.isNotEmpty(moduleTemp)){
                vo.setCountModuleTemp(moduleTemp);
            }
            vo.setModuleYear(value.get(0).getModuleYear());
            vo.setOneModule(value.get(0).getOneModule());
            vo.setCountScore(value.get(0).getCountScore());
            list.add(vo);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCountModule(List<CountModuleVo> countModuleVo,TokenUserInfo tokenUserInfo) {
        try {
            del(String.valueOf(countModuleVo.get(0).getModuleYear()));
            save(countModuleVo,tokenUserInfo);
            return true;
        } catch (Exception e) {
            log.error("统计模块更新失败！{}",e.getMessage());
            throw new BizException("统计模块更新失败！");
        }
    }

    @Override
    public List<Map<String, Object>> queryCountModule() {
        int year = LocalDateTime.now().getYear();
        List<Map<String,Object>> map = countModuleDao.queryCountModule(String.valueOf(year));
        Map<Object, List<Map<String, Object>>> one_module = map.stream().collect(Collectors.groupingBy(e -> e.get("ONE_MODULE")));
        List<Map<String,Object>> result =new ArrayList<>();
        one_module.forEach((k,s) ->{
            Map<String,Object> oneMap =new HashMap<String,Object>();
            List<String> twoList = new ArrayList<>();
            s.forEach(one_s ->{
                String two_module = (String)one_s.get("TWO_MODULE");
                twoList.add(two_module);
            });
            oneMap.put(String.valueOf(k),twoList);
            result.add(oneMap);
        });
        return result;
    }

    public void save(List<CountModuleVo> countModuleVo, TokenUserInfo tokenUserInfo){
        for (CountModuleVo countModule:countModuleVo) {
            CountModuleDO countModuleDO = CountModuleDO.builder()
                    .moduleYear(countModule.getModuleYear())
                    .countScore(countModule.getCountScore())
                    .oneModule(countModule.getOneModule())
                    .createdTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .createdUser(tokenUserInfo.getUserId()).build();
            countModuleDao.insert(countModuleDO);
            List<CountModuleTempDTO> countModuleTemp = countModule.getCountModuleTemp();
            for (CountModuleTempDTO temp:countModuleTemp) {
                CountModuleTempDO countModuleTempDO = CountModuleTempDO.builder()
                        .moduleId(countModuleDO.getId())
                        .twoModule(temp.getTwoModule())
                        .twoModuleScore(temp.getTwoModuleScore()).build();
                countModuleTempDao.insert(countModuleTempDO);
            }
        }
    }

    public void del(String moduleYear){
        QueryWrapper<CountModuleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MODULE_YEAR",moduleYear);
        List<CountModuleDO> countModuleDOS = countModuleDao.selectList(queryWrapper);
        List<Integer> ids = new ArrayList<>();
        for (CountModuleDO d:countModuleDOS) {
            ids.add(d.getId());
        }
        countModuleDao.delete(queryWrapper);
        countModuleTempDao.delCountModuleTempByIds(ids);
    }

}

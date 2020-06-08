package com.bank.manage.service.impl;

import com.bank.auth.dto.AuthDTO;
import com.bank.manage.dao.CatalogDao;
import com.bank.manage.dos.CataLogDO;
import com.bank.manage.dto.CatalogDTO;
import com.bank.manage.service.CatalogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    public List<Map<String, Object>> findCatalog() {
        List<Map<String, Object>> maps = null;
        List<Map<String, Object>> list = this.catalogDao.selectMaps(new QueryWrapper<CataLogDO>());
        if(list != null && list.size()>0){
             maps = CatalogTree(list);
        }
        log.info("素材目录树形结构：{}",maps);
        return maps;
    }

    @Override
    public Integer insertCatalog(CatalogDTO catalogDTO) {
        Subject subject = SecurityUtils.getSubject();
        AuthDTO authDTO = (AuthDTO)subject.getPrincipal();
        CataLogDO cataLogDO = CataLogDO.builder()
                .catalogName(catalogDTO.getCatalogName())
                .catalogType(catalogDTO.getCatalogType())
                .createdUser(authDTO.getUserId())
                .createdTime(LocalDateTime.now())
                .parentId(catalogDTO.getParentId())
                .build();
        log.info("素材目录插入数据：{}",cataLogDO);
        return catalogDao.insert(cataLogDO);
    }

    @Override
    public Integer updateCatalog(CatalogDTO catalogDTO) {
        CataLogDO cataLogDO = CataLogDO.builder()
                .catalogId(catalogDTO.getCatalogId())
                .catalogName(catalogDTO.getCatalogName()).build();
        log.info("素材目录更新数据：{}",cataLogDO);
        return catalogDao.updateById(cataLogDO);
    }

    @Override
    public Integer deleteCatalog(Integer id) {
        return catalogDao.deleteById(id);
    }


    private List<Map<String, Object>> CatalogTree(List<Map<String, Object>> list){
        //根节点
        List<Map<String, Object>> rootMenu = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> nav : list) {
            String parentId = String.valueOf(nav.get("parent_id"));
            if("null".equals(parentId)){
                rootMenu.add(nav);
            }
        }
        //为根菜单设置子菜单，getClild是递归调用的
        for (Map<String, Object> nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            String id = String.valueOf(nav.get("CATALOG_ID"));
            List<Map<String, Object>> childList = getChild(id, list);
            if(childList != null && childList.size()>0){
                nav.put("child", childList);
            }
        }
        return rootMenu;
    }

    private List<Map<String, Object>> getChild(String id, List<Map<String, Object>> list) {
        //子菜单
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> nav : list) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            String parentId = String.valueOf(nav.get("parent_id"));
            if(id.equals(parentId)){
                childList.add(nav);
            }
        }
        //递归
        for (Map<String, Object> nav : childList) {
            String tempId = String.valueOf(nav.get("CATALOG_ID"));
            List<Map<String, Object>> a = getChild(tempId, list);
            if(a != null && a.size()>0){
                nav.put("child", a);
            }
        }
        if(childList.size() == 0){
            return new ArrayList<Map<String, Object>>();
        }
        return childList;
    }
}

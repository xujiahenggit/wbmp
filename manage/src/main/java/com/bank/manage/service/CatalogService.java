package com.bank.manage.service;

import com.bank.manage.dto.CatalogDTO;

import java.util.List;
import java.util.Map;

public interface CatalogService {
    /**
     * 查询素材分类
     * @return
     */
    List<Map<String, Object>> findCatalog();

    /**
     * 素材目录新增
     * @param catalogDTO
     * @return
     */
    Integer insertCatalog(CatalogDTO catalogDTO);

    /**
     * 素材目录修改
     * @param catalogDTO
     * @return
     */
    Integer updateCatalog(CatalogDTO catalogDTO);

    /**
     * 素材目录删除
     * @param catalogDTO
     * @return
     */
    Integer deleteCatalog(Integer id);
}

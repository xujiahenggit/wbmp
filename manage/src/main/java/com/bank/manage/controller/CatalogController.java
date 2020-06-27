package com.bank.manage.controller;

import com.bank.log.annotation.SystemLog;
import com.bank.manage.dto.CatalogDTO;
import com.bank.manage.service.CatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 素材信息
 *
 * @author
 * @date 2020-4-8
 */
@Api(tags = "素材目录接口")
@RestController
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/findAllCatalogs")
    @ApiOperation(value = "查询素材分类树形结构")
    public List<Map<String,Object>> findCatalog(){
        List<Map<String,Object>> listCataLog = catalogService.findCatalog();
        return listCataLog;
    }

    @PostMapping("/insertCatalog")
    @ApiOperation(value = "新增素材目录")
    @ApiImplicitParam(name = "catalogDTO", value = "素材目录信息", required = true, paramType = "body", dataType = "CatalogDTO")
    public Integer insertCatalog(@RequestBody CatalogDTO catalogDTO){
        return catalogService.insertCatalog(catalogDTO);
    }


    @PostMapping("/updateCatalog")
    @ApiOperation(value = "修改素材目录")
    @ApiImplicitParam(name = "catalogDTO", value = "素材目录信息", required = true, paramType = "body", dataType = "CatalogDTO")
    public Integer updateCatalog(@RequestBody CatalogDTO catalogDTO){
        return catalogService.updateCatalog(catalogDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除素材目录")
    @ApiImplicitParam(name = "id", value = "素材目录唯一标识", required = true, paramType = "path")
    public Integer deleteCatalog(@PathVariable Integer id){
        return catalogService.deleteCatalog(id);
    }
}

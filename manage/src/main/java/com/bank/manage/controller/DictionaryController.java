package com.bank.manage.controller;

import com.bank.manage.dos.DictionaryDO;
import com.bank.manage.dos.DictionaryItemDO;
import com.bank.manage.dto.DictionaryDto;
import com.bank.manage.service.DictionaryItemService;
import com.bank.manage.service.DictionaryService;
import com.bank.manage.vo.DictQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:32
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/dict")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    @ApiOperation(value = "获取字典列表")
    @PostMapping("/list")
    public IPage<DictionaryDO> getlist(@RequestBody DictQueryVo dictQueryVo){
        return dictionaryService.getPage(dictQueryVo);
    }

    @ApiOperation(value = "批量删除字典列表")
    @DeleteMapping("/delete")
    public boolean delete(@RequestBody List<Integer> ids){
        return dictionaryService.deleteDict(ids);
    }

    @ApiOperation(value = "用ID 获取 字典详细信息")
    @GetMapping("/{dictId}")
    public DictionaryDto getDictinfo(@PathVariable Integer dictId){
        return dictionaryService.getDictinfo(dictId);
    }

    @ApiOperation(value = "修改字典信息")
    @PutMapping("/update")
    public boolean updateDictInfo(@RequestBody DictionaryDto dictionaryDto){
        return dictionaryService.updateDictInfo(dictionaryDto);
    }

    @ApiOperation(value = "新增字典信息")
    @PostMapping("/add")
    public boolean saveDictInfo(@RequestBody DictionaryDto dictionaryDto){
        return  dictionaryService.saveDictInfo(dictionaryDto);
    }

    @ApiOperation(value = "用名称获取字典项目列表")
    @GetMapping("/itme/{name}")
    public List<DictionaryItemDO> selectByName(@PathVariable(value = "name") String name){
        return dictionaryService.getDictinfoByName(name);
    }
}

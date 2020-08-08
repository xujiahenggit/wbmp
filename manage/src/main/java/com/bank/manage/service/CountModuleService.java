package com.bank.manage.service;

import java.util.List;
import java.util.Map;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.CountModuleDO;
import com.bank.manage.dto.CountModuleDTO;
import com.bank.manage.vo.CountModuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  统计模块主表 服务类
 *
 */
public interface CountModuleService extends IService<CountModuleDO> {

    /**
     * 保存统计模块信息
     * @param countModuleVo
     * @return
     */
    Boolean saveCountModule(List<CountModuleVo> countModuleVo, TokenUserInfo tokenUserInfo);

    /**
     * 统计模块分页查询
     * @param pageQueryModel
     * @return
     */
    IPage<CountModuleDTO> queryCountModulePage(PageQueryModel pageQueryModel);

    /**
     * 删除统计模块信息
     * @param moduleYear
     * @return
     */
    Boolean delCountModule(String moduleYear);

    /**
     * 统计模块详情
     * @param moduleYear
     * @return
     */
    List<CountModuleVo> getCountModule(String moduleYear);

    /**
     * 更新统计模块
     * @param countModuleVo
     * @return
     */
    Boolean updateCountModule(List<CountModuleVo> countModuleVo, TokenUserInfo tokenUserInfo);

    /**
     * 考核数据分析图统计模块接口
     * @return
     */
    List<Map<String, Object>> queryCountModule(String moduleYear);
}

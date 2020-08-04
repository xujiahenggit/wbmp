package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.SparamDO;
import com.bank.manage.dos.TaccountingOrderDO;
import com.bank.manage.dto.SparamDTO;
import com.bank.manage.dto.TaccountingOrderDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SparamService extends IService<SparamDO> {

    /**
     * 插入回单参数管理
     * @param sparamDTO
     * @param tokenUserInfo
     * @return
     */
    Boolean save(SparamDTO sparamDTO, TokenUserInfo tokenUserInfo);

    /**
     * 删除回单参数管理
     * @param list
     * @return
     */
//    Boolean delete(List<String> list);

    /**
     * 查询回单参数管理
     * @param pageQueryModel
     * @return
     */
    IPage<SparamDTO> queryList(PageQueryModel pageQueryModel);

    /**
     * 修改回单参数管理
     * @param sparamDTO
     * @param tokenUserInfo
     * @return
     */
    Boolean update(SparamDTO sparamDTO, TokenUserInfo tokenUserInfo);

    Boolean updateList(List<Integer> list, String value);
}

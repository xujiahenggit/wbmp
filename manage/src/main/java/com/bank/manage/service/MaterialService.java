package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.vo.ForcePlayVo;
import com.bank.manage.vo.MaterialVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MaterialService extends IService<MaterialDO> {
    /**
     * 保存素材信息
     * @param materialDTO
     * @param catalogId
     * @param tokenUserInfo
     * @return
     */
    Boolean insertMaterial(List<MaterialDTO> materialDTO, String catalogId, TokenUserInfo tokenUserInfo);

    /**
     * 修改素材
     * @param materialDTO
     * @param tokenUserInfo
     * @return
     */
    Boolean updateMaterial(MaterialDTO materialDTO, TokenUserInfo tokenUserInfo);

    /**
     * 素材删除
     * @param id
     * @return
     */
    Boolean deleteMaterial(Integer id);

    /**
     * 分页查询素材
     * @param pageQueryModel
     * @return
     */
    IPage<MaterialVo> queryMaterialList(PageQueryModel pageQueryModel);

    /**
     * 根据ID查询素材信息
     * @param id
     * @return
     */
    MaterialDO queryMaterialById(Integer id);

    /**
     * 修改 素材强制状态
     * @param list 素材列表
     * @return
     */
    boolean updateForcePlayState(List<ForcePlayVo> list);
}

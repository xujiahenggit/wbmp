package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.TacctInfoDao;
import com.bank.manage.dos.TacctInfoDO;
import com.bank.manage.dto.TacctInfoDTO;
import com.bank.manage.service.TacctInfoService;
import com.bank.manage.vo.TacctInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 帐号信息
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TacctInfoServiceImpl extends ServiceImpl<TacctInfoDao, TacctInfoDO> implements TacctInfoService {

    @Autowired
    private TacctInfoDao tacctInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(TacctInfoDTO tacctInfoDTO) {
        try {
            LocalDateTime now = LocalDateTime.now();
            TacctInfoDO tacctInfoDO = TacctInfoDO.builder()
                    .acctno(tacctInfoDTO.getAcctno())
                    .acctna(tacctInfoDTO.getAcctna())
                    .custno(tacctInfoDTO.getCustno())
                    .brchno(tacctInfoDTO.getBrchno())
                    .flagKeyacct(tacctInfoDTO.getFlagKeyacct().equals("1") ? "1" : "0")
                    .flagNotaccounting(tacctInfoDTO.getFlagNotaccounting().equals("1") ? "1" : "0")
                    .flagPaymengt(tacctInfoDTO.getFlagPaymengt().equals("1") ? "1" : "0")
                    .branchAccounting(tacctInfoDTO.getBranchAccounting())
                    .flagBind(tacctInfoDTO.getFlagBind().equals("1") ? "1" : "0")
                    .model(tacctInfoDTO.getModel().equals("1") ? "1" : "0")
                    .tellerUpdate(tacctInfoDTO.getTellerUpdate())
                    .timeUpdate(now)
                    .acctst("0")
                    .build();
            log.info("插入帐号信息：{}", tacctInfoDO);
            this.tacctInfoDao.insert(tacctInfoDO);
                
        }
        catch (Exception e) {
            log.error("插入帐号信息失败！{}", e.getMessage());
            throw new BizException("插入帐号信息失败！" + e.getMessage());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> list) {
        log.info("删除帐号信息，帐号ID：{}", list);
        try {
            this.tacctInfoDao.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("删除帐号信息失败！{}", e.getMessage());
            throw new BizException("删除帐号信息失败！");
        }
        return true;
    }

    @Override
    public IPage<TacctInfoVO> queryList(PageQueryModel pageQueryModel) {
        Page<TacctInfoVO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        TacctInfoDO tacctInfoDO=null;
        if(!MapUtils.isEmpty(queryParam)){
            tacctInfoDO= BeanUtil.mapToBean(queryParam, TacctInfoDO.class,false);
        }
        List<TacctInfoVO> TacctInfoDTOS = tacctInfoDao.selectPageList(page, tacctInfoDO);
        page.setRecords(TacctInfoDTOS);
        log.info("帐号信息查询：{}", TacctInfoDTOS);
        return page;
    }


	@Override
	public String selectByAcctNo(String accto) {
        QueryWrapper<TacctInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(accto), "ACCTNO", accto);
        TacctInfoDO tacctInfoDO = this.tacctInfoDao.selectOne(queryWrapper);
		if(null == tacctInfoDO) {
			return null;
		}
		//原业务逻辑根据acctno查SRC_KNA_ACCT表如果查不到返回 "不存在此账号，请检查账号是否输入正确！"
		return "该账号已经存在！";
	}

    @Override
    public Boolean updateTacctInfo(TacctInfoDTO tacctInfoDTO) {
        try {
            TacctInfoDO tacctInfoDO = TacctInfoDO.builder()
                    .id(tacctInfoDTO.getId())
                    .acctno(tacctInfoDTO.getAcctno())
                    .tellerUpdate(tacctInfoDTO.getTellerUpdate())
                    .flagBind(tacctInfoDTO.getFlagBind())
                    .flagKeyacct(tacctInfoDTO.getFlagKeyacct())
                    .flagNotaccounting(tacctInfoDTO.getFlagNotaccounting())
                    .flagPaymengt(tacctInfoDTO.getFlagPaymengt())
                    .branchAccounting(tacctInfoDTO.getBranchAccounting())
                    .timeUpdate(LocalDateTime.now())
                    .model(tacctInfoDTO.getModel())
                    .acctst(tacctInfoDTO.getAcctst())
                    .build();
            log.info("修改账号信息：{}", tacctInfoDO);
            this.tacctInfoDao.updateById(tacctInfoDO);
        }
        catch (Exception e) {
            log.error("修改账号信息失败！{}", e.getMessage());
            throw new BizException("修改账号信息失败！" + e.getMessage());
        }
        return true;
    }
}

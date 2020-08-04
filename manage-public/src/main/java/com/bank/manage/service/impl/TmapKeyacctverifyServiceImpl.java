package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.TmapKeyacctverifyDao;
import com.bank.manage.dos.TmapKeyacctverifyDO;
import com.bank.manage.dto.TmapKeyacctverifyDTO;
import com.bank.manage.service.TmapKeyacctverifyService;
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
 * 指定重点账号信息
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TmapKeyacctverifyServiceImpl extends ServiceImpl<TmapKeyacctverifyDao, TmapKeyacctverifyDO> implements TmapKeyacctverifyService {

    @Autowired
    private TmapKeyacctverifyDao tmapKeyacctverifyDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(TmapKeyacctverifyDTO tmapKeyacctverifyDTO) {
        try {
            //原业务逻辑判断是否是本机构 否返回"只能增加本机构账号/借据号，请重新输入！"
            LocalDateTime now = LocalDateTime.now();
            TmapKeyacctverifyDO tmapKeyacctverifyDO = TmapKeyacctverifyDO.builder()
                    .acctno(tmapKeyacctverifyDTO.getAcctno())
                    .tellerInsert(tmapKeyacctverifyDTO.getTellerInsert())
                    .timeInsert(now)
                    .branch(tmapKeyacctverifyDTO.getBranch())
                    .tellerUpdate(tmapKeyacctverifyDTO.getTellerInsert())
                    .timeUpdate(now)
                    .status("0")
                    .build();
            log.info("插入指定重点账号信息：{}", tmapKeyacctverifyDO);
            this.tmapKeyacctverifyDao.insert(tmapKeyacctverifyDO);
                
        }
        catch (Exception e) {
            log.error("插入指定重点账号信息失败！{}", e.getMessage());
            throw new BizException("插入指定重点账号信息失败！" + e.getMessage());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> list) {
        log.info("删除指定重点账号信息，帐号ID：{}", list);
        try {
            this.tmapKeyacctverifyDao.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("删除指定重点账号信息失败！{}", e.getMessage());
            throw new BizException("删除指定重点账号信息失败！");
        }
        return true;
    }

    @Override
    public IPage<TmapKeyacctverifyDO> queryList(PageQueryModel pageQueryModel) {
        Page<TmapKeyacctverifyDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String acctno = (String) queryParam.get("acctno");
        String teller = (String) queryParam.get("tellerInsert");
        String branch = (String) queryParam.get("branch");
        String status = (String) queryParam.get("status");

        if(StringUtils.isBlank(branch)){
        	branch = "99010101";
		}

        QueryWrapper<TmapKeyacctverifyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(acctno), "ACCTNO", acctno)
        .eq(StringUtils.isNotBlank(teller), "TELLER_INSERT", teller)
        .eq("BRANCH", branch)
        .eq(StringUtils.isNotBlank(status), "STATUS", status);
        
        IPage<TmapKeyacctverifyDO> selectPage = this.tmapKeyacctverifyDao.selectPage(page, queryWrapper);
        log.info("指定重点账号查询：{}", selectPage);
        return selectPage;
    }


	@Override
	public String selectByAcctno(String acctno) {
        TmapKeyacctverifyDO tmapKeyacctverifyDO = this.tmapKeyacctverifyDao.selectById(acctno);
		if(null == tmapKeyacctverifyDO) {
			return null;
		}
		return "该账号已经存在！";
	}

    @Override
    public Boolean switchByAcctno(TmapKeyacctverifyDTO tmapKeyacctverifyDTO) {
        try {
            TmapKeyacctverifyDO tmapKeyacctverifyDO = TmapKeyacctverifyDO.builder()
                    .id(tmapKeyacctverifyDTO.getId())
                    .acctno(tmapKeyacctverifyDTO.getAcctno())
                    .tellerUpdate(tmapKeyacctverifyDTO.getTellerUpdate())
                    .timeUpdate(LocalDateTime.now())
                    .status(tmapKeyacctverifyDTO.getStatus())
                    .build();
            log.info("修改指定重点账号信息：{}", tmapKeyacctverifyDO);
            this.tmapKeyacctverifyDao.updateById(tmapKeyacctverifyDO);
        }
        catch (Exception e) {
            log.error("修改指定重点账号信息失败！{}", e.getMessage());
            throw new BizException("修改指定重点账号信息失败！" + e.getMessage());
        }
        return true;
    }


}

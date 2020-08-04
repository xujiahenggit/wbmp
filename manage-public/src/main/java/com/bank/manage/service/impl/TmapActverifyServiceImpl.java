package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.TmapActverifyDao;
import com.bank.manage.dos.TmapActverifyDO;
import com.bank.manage.dto.TmapActverifyDTO;
import com.bank.manage.service.TmapActverifyService;
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
 * 帐号指定信息
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TmapActverifyServiceImpl extends ServiceImpl<TmapActverifyDao, TmapActverifyDO> implements TmapActverifyService {

    @Autowired
    private TmapActverifyDao tmapActverifyDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(TmapActverifyDTO tmapActverifyDTO) {
        try {
            LocalDateTime now = LocalDateTime.now();
            TmapActverifyDO tmapActverifyDO = TmapActverifyDO.builder()
                    .acctno(tmapActverifyDTO.getAcctno())
                    .tellerInsert(tmapActverifyDTO.getTellerInsert())
                    .timeInsert(now)
                    .branch(tmapActverifyDTO.getBranch())
                    .tellerUpdate(tmapActverifyDTO.getTellerInsert())
                    .timeUpdate(now)
                    .status("0")
                    .build();
            log.info("插入帐号指定信息：{}", tmapActverifyDO);
            this.tmapActverifyDao.insert(tmapActverifyDO);
                
        }
        catch (Exception e) {
            log.error("插入帐号指定信息失败！{}", e.getMessage());
            throw new BizException("插入帐号指定信息失败！" + e.getMessage());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> list) {
        log.info("删除帐号指定信息，帐号ID：{}", list);
        try {
            this.tmapActverifyDao.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("删除帐号指定信息失败！{}", e.getMessage());
            throw new BizException("删除帐号指定信息失败！");
        }
        return true;
    }

    @Override
    public IPage<TmapActverifyDO> queryList(PageQueryModel pageQueryModel) {
        Page<TmapActverifyDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

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
        
        if(StringUtils.isBlank(branch)){
        	branch = "99010101";
		}
        
        QueryWrapper<TmapActverifyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(acctno), "ACCTNO", acctno)
        .eq(StringUtils.isNotBlank(teller), "TELLER_INSERT", teller)
        .eq("BRANCH", branch);
        
        IPage<TmapActverifyDO> selectPage = this.tmapActverifyDao.selectPage(page, queryWrapper);
        log.info("帐号指定信息查询：{}", selectPage);
        return selectPage;
    }


	@Override
	public String selectByAcctno(String acctno) {
        QueryWrapper<TmapActverifyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(acctno), "ACCTNO", acctno);
        TmapActverifyDO tmapActverifyDO = this.tmapActverifyDao.selectOne(queryWrapper);
		if(null == tmapActverifyDO) {
			return null;
		}
		//原业务逻辑根据acctno查SRC_KNA_ACCT表如果查不到返回 "不存在此账号，请检查账号是否输入正确！"
		return "该账号已经存在！";
	}


}

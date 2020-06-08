package com.bank.manage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.manage.dao.HappyDao;
import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.bank.manage.dto.DeductDTO;
import com.bank.manage.dto.StatisticsDTO;
import com.bank.manage.service.HappyService;
import com.bank.manage.vo.HappyParam;
import com.bank.role.dos.RoleDO;
import com.bank.role.dos.UserRoleDO;
import com.bank.role.service.RoleService;
import com.bank.role.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HappyServiceImpl implements HappyService {

    @Resource
    RoleService roleService;

    @Resource
    UserRoleService userRoleService;

    @Resource
    HappyDao happyDao;


    @Override
    public boolean hasAdminPermission(String userId) {
        RoleDO roleDO = roleService.getOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getRoleCode, "importAdmin"));
        UserRoleDO userRoleDO = userRoleService.getOne(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, userId).eq(UserRoleDO::getRoleId, roleDO.getRoleId()));
        return userRoleDO == null ? false : true;
    }

    @Override
    public List<DeductDTO> deductStatus(HappyParam param) {
        List<DeductDTO> data = new ArrayList<>();
        List<ExamineDataTempAdminDO> list = happyDao.deductStatus(param);
        long total = list.stream().map(ExamineDataTempAdminDO::getDeduction).reduce(0, (a, b) -> a + b);
        list.stream().limit(3).forEach(e ->data.add(DeductDTO.builder().name(e.getTwoModule()).total(total).deduct(e.getDeduction()).build()));
        return data;
    }

    @Override
    public List<Map<String, Integer>> starStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        return happyDao.starStatus(param);
    }

    @Override
    public List<Map<String, Integer>> serviceLevelStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        return happyDao.serviceLevelStatus(param);
    }

    private void checkAndSetDefaultParam(HappyParam param) {
        switch (param.getQuarter()) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            default:
                throw new BizException("季度值不合法，只能在[1,2.3,4]中选择");
        }
        param.setHasAdmin(hasAdminPermission(param.getUserId()));
        String orgids = param.getOrgids();
        if (!StrUtil.isBlankIfStr(orgids)) {
            param.setOrgids(Arrays.stream(orgids.split(",")).collect(Collectors.joining("','")));
        }
    }

    /**
     * 获取上一个季度年
     *
     * @param year
     * @param quarter
     * @return
     */
    public static String getLastYearQuarter(Integer year, Integer quarter) {
        return quarter == 1 ? year - 1 + "" + 4 : year + "" + (quarter - 1);
    }

    @Override
    public Map<String, Object> checkStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        HashMap<String, Object> map = new HashMap<>();
        String yearAndQuarter = getLastYearQuarter(param.getYear(), param.getQuarter());
        Integer paramYear = param.getYear();
        int queryYear = paramYear;
        map.put("detail", happyDao.checkStatusDetails(param));

        boolean isAdmin = param.isHasAdmin();
        List<StatisticsDTO> year = happyDao.checkStatusStatisticsYear(isAdmin);

        int YearScore = 0;
        if (year.size() > 0) {
            YearScore = getYearScore(year, queryYear);
        }
        map.put("year", YearScore);

        //上一季度的值
        int lastQuarterYear = Integer.valueOf(yearAndQuarter.substring(0, 4));
        int lastQuarter = Integer.valueOf(yearAndQuarter.substring(4));
        List<StatisticsDTO> quarterData = happyDao.checkStatusStatisticsQuarter(isAdmin);
        year.clear();
        YearScore = 0;
        quarterData.stream().filter(vo -> {
            int voYear = vo.getYear();
            int voQuarter = vo.getQuarter();
            if (voYear == paramYear && voQuarter == param.getQuarter()) {
                year.add(new StatisticsDTO(paramYear, vo.getScore()));
            } else if (voYear == lastQuarterYear && voQuarter == lastQuarter) {
                year.add(new StatisticsDTO(queryYear - 1, vo.getScore()));
            } else {
                return false;
            }
            return true;
        }).forEach(System.out::println);
        if (year.size() > 0) {
            YearScore = getYearScore(year, queryYear);
        }
        map.put("quarter", YearScore);
        return map;
    }

    @Override
    public List<Map<String, Integer>> HeadStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        return happyDao.HeadStatus(param);
    }

    @SuppressWarnings("all")
    private int getYearScore(List<StatisticsDTO> year, int thisYear) {
        int lastYear = thisYear - 1;
        int YearScore = 0;
        Optional<Integer> currentYearScore = year.stream().filter(vo -> vo.getYear() == thisYear).map(StatisticsDTO::getScore).findFirst();
        Optional<Integer> lastYearScore = year.stream().filter(vo -> vo.getYear() == lastYear).map(StatisticsDTO::getScore).findFirst();
        boolean currentYearScorePresent = currentYearScore.isPresent();
        boolean lastYearScorePresent = lastYearScore.isPresent();

        if (currentYearScorePresent && lastYearScorePresent) {
            YearScore = currentYearScore.get() - lastYearScore.get();
        } else if (currentYearScorePresent && !lastYearScorePresent) {
            YearScore = currentYearScore.get();
        } else if (!currentYearScorePresent && lastYearScorePresent) {
            YearScore = -lastYearScore.get();
        } else if (!currentYearScorePresent && !lastYearScorePresent) {
            YearScore = 0;
        }
        return YearScore;
    }
}

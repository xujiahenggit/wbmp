package com.bank.manage.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.manage.dao.HappyDao;
import com.bank.manage.dos.ExamineDataAdminDO;
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
        list.stream().limit(3).forEach(e -> data.add(DeductDTO.builder().name(e.getTwoModule()).total(total).deduct(e.getDeduction()).build()));
        return data;
    }

    @Override
    public List<Map> starStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        List<HashMap<Object, Object>> dict = ListUtil.toList(MapUtil.of(new Object[][]{
                {"star", "一星级"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"star", "二星级"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"star", "三星级"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"star", "四星级"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"star", "五星级"},
                {"count", 0}
        }));
        List<Map<String, Integer>> data = happyDao.starStatus(param);
        return (List<Map>) fillDictData(dict, data, "star", "count");
    }

    private List<? extends Map> fillDictData(List<? extends Map> dict, List<? extends Map> data, String type, String count) {
        HashMap<Object, Object> temMap = MapUtil.newHashMap();
        for (Map<Object, Object> dataList : data) {
            temMap.put(dataList.get(type), dataList.get(count));
        }
        for (Map<Object, Object> dictList : dict) {
            Object value = dictList.get(type);
            if (temMap.containsKey(value)) {
                dictList.put(count, temMap.get(value));
            }
        }
        return dict;
    }

    @Override
    public List<Map> serviceLevelStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        List<HashMap<Object, Object>> dict = ListUtil.toList(MapUtil.of(new Object[][]{
                {"level", "卓越"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"level", "优秀"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"level", "一般"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"level", "关注"},
                {"count", 0}
        }), MapUtil.of(new Object[][]{
                {"level", "重点关注"},
                {"count", 0}
        }));
        List<Map<String, Integer>> data = happyDao.serviceLevelStatus(param);
        return (List<Map>) fillDictData(dict, data, "level", "count");
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
        String networks = param.getNetworks();
        if (StrUtil.isBlankIfStr(networks)) {
            if (!StrUtil.isBlankIfStr(orgids)) {
                List<String> orgIds = happyDao.getOrgIds(orgids);
                param.setOrgs(orgIds);
//                param.setOrgids(orgIds.stream().collect(Collectors.joining("','")));
            }
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
        Integer quarter = param.getQuarter();
        String yearAndQuarter = getLastYearQuarter(param.getYear(), quarter);
        Integer paramYear = param.getYear();
        int queryYear = paramYear;
        List<ExamineDataAdminDO> list = happyDao.checkStatusDetails(param);
        map.put("detail", list);

        Integer count = list.size();
        boolean isAdmin = param.isHasAdmin();
        //上一季度的值
        int lastQuarterYear = Integer.valueOf(yearAndQuarter.substring(0, 4));
        int lastQuarter = Integer.valueOf(yearAndQuarter.substring(4));
        List<StatisticsDTO> quarterData = happyDao.checkStatusStatisticsQuarter(isAdmin,param);

        int YearScore = 0;
        if (quarterData.size() > 0) {
            YearScore = getYearScore(quarterData, queryYear, queryYear - 1, quarter);
        }
        map.put("year", getAverage(YearScore, count));

        YearScore = 0;
        YearScore = getYearScore(quarterData, queryYear, lastQuarterYear, lastQuarter);
        map.put("quarter", getAverage(YearScore, count));
        return map;
    }

    private Object getAverage(int yearScore, Integer count) {
        if (count == null || count == 0) {
            return 0;
        } else {
            double div = NumberUtil.div((float) yearScore, (float) count, 2);
            if (Math.abs(div - 0d) == 0) {
                return 0;
            }
            return div;
        }

    }

    @Override
    public List<Map<String, Integer>> HeadStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        return happyDao.HeadStatus(param);
    }

    @SuppressWarnings("all")
    private int getYearScore(List<StatisticsDTO> year, int thisYear, int lastYear, Integer quarter) {
        int YearScore = 0;
        Optional<Integer> currentYearScore = year.stream().filter(vo -> vo.getYear() == thisYear && vo.getQuarter() == quarter).map(StatisticsDTO::getScore).findFirst();
        Optional<Integer> lastYearScore = year.stream().filter(vo -> vo.getYear() == lastYear && vo.getQuarter() == quarter).map(StatisticsDTO::getScore).findFirst();
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

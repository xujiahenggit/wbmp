package com.bank.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

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
        List<Map> dictList = ListUtil.toList(
                MapUtil.of(
                        new Object[][] {
                                { "star", "无星" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "star", "三星级" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "star", "四星级" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "star", "五星级" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }));

        List<Map<String, Object>> dataList = happyDao.starStatus(param);
        //根据星级进行分组
        Map<String, List<Map<String, Object>>> groupData = dataList.stream().collect(Collectors.groupingBy(e -> e.get("star").toString()));
        List<Map<String, Object>> oneTwo = new ArrayList<>();
        //一星级和二星级整合为无星
        oneTwo.addAll(groupData.containsKey("一星级") ? groupData.get("一星级") : new ArrayList<>());
        oneTwo.addAll(groupData.containsKey("二星级") ? groupData.get("二星级") : new ArrayList<>());
        groupData.remove("一星级");
        groupData.remove("二星级");
        groupData.put("无星", oneTwo);

        for (int i = 0; i < dictList.size(); i++) {
            Map dict = dictList.get(i);
            String star = dict.get("star").toString();

            if (groupData.containsKey(star)) {
                dict.put("count", groupData.get(star).size());
                dict.put("orgList", groupData.get(star));
            }
        }
        return dictList;
    }

    private void combineData(List<Map<String, Object>> data) {
        Integer one = getStar(data, "一星级");
        Integer two = getStar(data, "二星级");
        Map<String, Object> map = new HashMap();
        map.put("star", "无星");
        map.put("count", one + two);
        data.add(map);
    }

    private Integer getStar(List<Map<String, Object>> data, String star) {
        for (Map<String, Object> datum : data) {
            if (datum.get("star").equals(star)) {
                data.remove(star);
                return Integer.valueOf(datum.get("count").toString());
            }
        }
        return 0;

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
        List<Map> dictList = ListUtil.toList(
                MapUtil.of(
                        new Object[][] {
                                { "level", "卓越" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "level", "优秀" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "level", "一般" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "level", "关注" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }),
                MapUtil.of(
                        new Object[][] {
                                { "level", "重点关注" },
                                { "count", 0 },
                                { "orgList", new ArrayList<>() }
                        }));
        List<Map<String, Object>> dataList = happyDao.serviceLevelStatus(param);
        //根据服务等级进行分组
        Map<String, List<Map<String, Object>>> groupData = dataList.stream().collect(Collectors.groupingBy(e -> e.get("level").toString()));

        for (int i = 0; i < dictList.size(); i++) {
            Map dict = dictList.get(i);
            String level = dict.get("level").toString();

            if (groupData.containsKey(level)) {
                dict.put("count", groupData.get(level).size());
                dict.put("orgList", groupData.get(level));
            }
        }
        return dictList;
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
        List<StatisticsDTO> quarterData = happyDao.checkStatusStatisticsQuarter(isAdmin, param);

        int YearScore = 0;
        if (quarterData.size() > 0) {
            YearScore = getYearScore(quarterData, queryYear, quarter, queryYear - 1, quarter);
        }
        map.put("year", getAverage(YearScore, count));

        YearScore = 0;
        YearScore = getYearScore(quarterData, queryYear, quarter, lastQuarterYear, lastQuarter);
        map.put("quarter", getAverage(YearScore, count));
        return map;
    }

    private Object getAverage(int yearScore, Integer count) {
        if (count == null || count == 0) {
            return 0;
        }
        else {
            double div = NumberUtil.div((float) yearScore, (float) count, 2);
            if (Math.abs(div - 0d) == 0) {
                return 0;
            }
            return div;
        }

    }

    @Override
    public List<Map<String, Object>> HeadStatus(HappyParam param) {
        checkAndSetDefaultParam(param);
        List<Map<String, Object>> dataList = happyDao.HeadStatus(param);

        //根据类型进行分组
        Map<String, List<Map<String, Object>>> groupData = dataList.stream().collect(Collectors.groupingBy(e -> e.get("type").toString()));
        List<Map<String, Object>> resultList = new ArrayList<>();

        //Map迭代
        groupData.forEach((type, orgList) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("type", type);
            data.put("count", orgList.size());
            data.put("orgList", orgList);

            resultList.add(data);
        });

        return resultList;
    }

    @SuppressWarnings("all")
    private int getYearScore(List<StatisticsDTO> year, int thisYear, Integer thisQuarter, int lastYear, Integer lastQuarter) {
        int YearScore = 0;
        Optional<Integer> currentYearScore = year.stream().filter(vo -> vo.getYear() == thisYear && vo.getQuarter() == thisQuarter).map(StatisticsDTO::getScore).findFirst();
        Optional<Integer> lastYearScore = year.stream().filter(vo -> vo.getYear() == lastYear && vo.getQuarter() == lastQuarter).map(StatisticsDTO::getScore).findFirst();
        boolean currentYearScorePresent = currentYearScore.isPresent();
        boolean lastYearScorePresent = lastYearScore.isPresent();

        if (currentYearScorePresent && lastYearScorePresent) {
            YearScore = currentYearScore.get() - lastYearScore.get();
        }
        else if (currentYearScorePresent && !lastYearScorePresent) {
            YearScore = currentYearScore.get();
        }
        else if (!currentYearScorePresent && lastYearScorePresent) {
            YearScore = -lastYearScore.get();
        }
        else if (!currentYearScorePresent && !lastYearScorePresent) {
            YearScore = 0;
        }
        return YearScore;
    }
}

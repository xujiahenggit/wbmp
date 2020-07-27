package com.bank.manage.service.impl;

import com.bank.core.sysConst.WbmpConstFile;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.WbmpOperateCustDao;
import com.bank.manage.dos.WbmpOperateCustDO;
import com.bank.manage.dto.CustomerAvgChartsDto;
import com.bank.manage.dto.CustomerAvgDto;
import com.bank.manage.service.WbmpOperateCustService;
import com.bank.manage.vo.CustomerAvgVo;
import com.bank.manage.vo.WbmpOperateCustVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:56
 */
@Service
public class WbmpOperateCustServiceImpl extends ServiceImpl<WbmpOperateCustDao, WbmpOperateCustDO> implements WbmpOperateCustService {

    @Resource
    private WbmpOperateCustDao wbmpOperateCustDao;

    /**
     * 查询客群增长
     * @param customerAvgVo 查询参数
     * @return
     */
    @Override
    public CustomerAvgDto getCustomerImg(CustomerAvgVo customerAvgVo) {
        CustomerAvgDto customerAvgDto=new CustomerAvgDto();
        if(WbmpConstFile.DATE_TYPE_DAY.equals(customerAvgVo.getCycleCode())){
            //按日统计
            CustomerAvgChartsDto customerAvgChartsDto=new CustomerAvgChartsDto();
            List<String> days= DateUtils.getDateBefor15();
            List<String> formatDays=new ArrayList<>();
            List<Integer> data=new ArrayList<>();

            //获取最近15天的客群指标数据
            List<WbmpOperateCustVo> custList = wbmpOperateCustDao.findDaysCust(customerAvgVo.getOrgId(),customerAvgVo.getCustomerTypeCode());
            for (String item:days){
                formatDays.add(DateUtils.formartToMonthDay(item));
                int custValue = 0;
                    for(WbmpOperateCustVo cust:custList){
                    if(item.equals(cust.getDataDt())){
                        custValue = cust.getCompLastd();
                    }
                }
                data.add(custValue);
            }
            customerAvgDto.setCycleCode(WbmpConstFile.DATE_TYPE_DAY);
            customerAvgDto.setCycleName("日");
            customerAvgDto.setCustomerTypeCode(customerAvgVo.getCustomerTypeCode());
            customerAvgDto.setCustomerTypeName(getCustomerType(customerAvgVo.getCustomerTypeCode()));
            customerAvgChartsDto.setData(data);
            customerAvgChartsDto.setXAxis(formatDays);
            customerAvgChartsDto.setXAxisName("时间");
            customerAvgChartsDto.setYAxisName("人");
            customerAvgDto.setCharts(customerAvgChartsDto);
        }else if(WbmpConstFile.DATE_TYPE_MONTH.equals(customerAvgVo.getCycleCode())){
            //按月统计
            CustomerAvgChartsDto customerAvgChartsDto=new CustomerAvgChartsDto();
            List<String> months= DateUtils.getLatest12Month();
            List<Integer> data=new ArrayList<>();

            //获取最近12个月的数据
            List<WbmpOperateCustVo> custList = wbmpOperateCustDao.findMouthCust(customerAvgVo.getOrgId(),customerAvgVo.getCustomerTypeCode());
            for (String item:months){
                int custValue = 0;
                for(WbmpOperateCustVo cust:custList){
                    if(item.equals(cust.getDataDt())){
                        custValue = cust.getCompLastm();
                    }
                }
                data.add(custValue);
            }
            customerAvgDto.setCycleCode(WbmpConstFile.DATE_TYPE_MONTH);
            customerAvgDto.setCycleName("月");
            customerAvgDto.setCustomerTypeCode(customerAvgVo.getCustomerTypeCode());
            customerAvgDto.setCustomerTypeName(getCustomerType(customerAvgVo.getCustomerTypeCode()));
            customerAvgChartsDto.setData(data);
            customerAvgChartsDto.setXAxis(months);
            customerAvgChartsDto.setXAxisName("时间");
            customerAvgChartsDto.setYAxisName("人");
            customerAvgDto.setCharts(customerAvgChartsDto);

        }else if(WbmpConstFile.DATE_TYPE_YEAR.equals(customerAvgVo.getCycleCode())){
            //按年统计
            CustomerAvgChartsDto customerAvgChartsDto=new CustomerAvgChartsDto();
            List<String> years= DateUtils.getLatest3Year();
            List<Integer> data=new ArrayList<>();
            //获取最近3年的数据
            List<WbmpOperateCustVo> custList = wbmpOperateCustDao.findYearCust(customerAvgVo.getOrgId(),customerAvgVo.getCustomerTypeCode());
            for (String item:years){
                int custValue = 0;
                for(WbmpOperateCustVo cust:custList){
                    if(item.equals(cust.getDataDt())){
                        custValue = cust.getCompLasty();
                    }
                }
                data.add(custValue);
            }
            customerAvgDto.setCycleCode(WbmpConstFile.DATE_TYPE_YEAR);
            customerAvgDto.setCycleName("年");
            customerAvgDto.setCustomerTypeCode(customerAvgVo.getCustomerTypeCode());
            customerAvgDto.setCustomerTypeName(getCustomerType(customerAvgVo.getCustomerTypeCode()));
            customerAvgChartsDto.setData(data);
            customerAvgChartsDto.setXAxis(years);
            customerAvgChartsDto.setXAxisName("时间");
            customerAvgChartsDto.setYAxisName("人");
            customerAvgDto.setCharts(customerAvgChartsDto);
        }
        return customerAvgDto;
    }


    private String  getCustomerType(String code){
        switch (code){
            case WbmpConstFile.CUSTOMER_TYPE_CUST_001:
                return "普通客户";
            case WbmpConstFile.CUSTOMER_TYPE_CUST_002:
                return "金卡客户";
            case WbmpConstFile.CUSTOMER_TYPE_CUST_003:
                return "白金客户";
            case WbmpConstFile.CUSTOMER_TYPE_CUST_004:
                return "钻石客户";
            default:
                return "未知";
        }
    }
}

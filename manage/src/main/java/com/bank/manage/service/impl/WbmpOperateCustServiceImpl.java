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
            List<String> days= DateUtils.getDateBefor15();
            List<String> formatDays=new ArrayList<>();
            List<Integer> data=new ArrayList<>();
            //获取最近15天的客群指标数据
            List<WbmpOperateCustVo> custList = new ArrayList<>();
            //查询所有客户
            if(WbmpConstFile.CUSTOMER_TYPE_CUST_000.equals(customerAvgVo.getCustomerTypeCode())){
                for (String item:days){
                    formatDays.add(DateUtils.formartToMonthDay(item));
                    int custValue =  wbmpOperateCustDao.findDaysCustAll(customerAvgVo.getOrgId(),item);
                    data.add(custValue);
                }
                customerAvgDto=getCunstomerDtoModel(WbmpConstFile.DATE_TYPE_DAY,WbmpConstFile.DATE_TYPE_DAY_TXT,customerAvgVo.getCustomerTypeCode(),formatDays,data);
            }else{
                custList =wbmpOperateCustDao.findDaysCust(customerAvgVo.getOrgId(),customerAvgVo.getCustomerTypeCode());
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
                customerAvgDto=getCunstomerDtoModel(WbmpConstFile.DATE_TYPE_DAY,WbmpConstFile.DATE_TYPE_DAY_TXT,customerAvgVo.getCustomerTypeCode(),formatDays,data);
            }

        }else if(WbmpConstFile.DATE_TYPE_MONTH.equals(customerAvgVo.getCycleCode())){
            //按月统计
            List<String> months= DateUtils.getLatest12Month();
            List<Integer> data=new ArrayList<>();
            if(WbmpConstFile.CUSTOMER_TYPE_CUST_000.equals(customerAvgVo.getCustomerTypeCode())){
                for (String item:months){
                    int custValue =  wbmpOperateCustDao.findMouthsCustAll(customerAvgVo.getOrgId(),item);
                    data.add(custValue);
                }
                customerAvgDto=getCunstomerDtoModel(WbmpConstFile.DATE_TYPE_MONTH,WbmpConstFile.DATE_TYPE_MONTH_TXT,customerAvgVo.getCustomerTypeCode(),months,data);
            }else{
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
                customerAvgDto=getCunstomerDtoModel(WbmpConstFile.DATE_TYPE_MONTH,WbmpConstFile.DATE_TYPE_MONTH_TXT,customerAvgVo.getCustomerTypeCode(),months,data);
            }
        }else if(WbmpConstFile.DATE_TYPE_YEAR.equals(customerAvgVo.getCycleCode())){
            //按年统计
            List<String> years= DateUtils.getLatest3Year();
            List<Integer> data=new ArrayList<>();
            if(WbmpConstFile.CUSTOMER_TYPE_CUST_000.equals(customerAvgVo.getCustomerTypeCode())){
                for (String item:years){
                    int custValue =wbmpOperateCustDao.findYearCustAll(customerAvgVo.getOrgId(),item);
                    data.add(custValue);
                }
                customerAvgDto=getCunstomerDtoModel(WbmpConstFile.DATE_TYPE_YEAR,WbmpConstFile.DATE_TYPE_YEAR_TXT,customerAvgVo.getCustomerTypeCode(),years,data);
            }else{
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
                customerAvgDto=getCunstomerDtoModel(WbmpConstFile.DATE_TYPE_YEAR,WbmpConstFile.DATE_TYPE_YEAR_TXT,customerAvgVo.getCustomerTypeCode(),years,data);
            }
        }
        return customerAvgDto;
    }

    /**
     * 构建模型
     * @param cyleCode 查询周期
     * @param cyleName 周期文本
     * @param customerTypeCode 客户类型
     * @param times 时间列表
     * @param data 值列表
     * @return
     */
    private CustomerAvgDto getCunstomerDtoModel(String cyleCode,String cyleName,String customerTypeCode, List<String> times,List<Integer> data){
        CustomerAvgDto customerAvgDto=new CustomerAvgDto();
        customerAvgDto.setCycleCode(cyleCode);
        customerAvgDto.setCycleName(cyleName);
        customerAvgDto.setCustomerTypeCode(customerTypeCode);
        customerAvgDto.setCustomerTypeName(getCustomerType(customerTypeCode));
        CustomerAvgChartsDto customerAvgChartsDto=new CustomerAvgChartsDto();
        customerAvgChartsDto.setData(data);
        customerAvgChartsDto.setXAxis(times);
        customerAvgChartsDto.setXAxisName("时间");
        customerAvgChartsDto.setYAxisName("人");
        customerAvgDto.setCharts(customerAvgChartsDto);
        return customerAvgDto;
    }

    private String  getCustomerType(String code){
        switch (code){
            case WbmpConstFile.CUSTOMER_TYPE_CUST_000:
                return "全部客户";
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

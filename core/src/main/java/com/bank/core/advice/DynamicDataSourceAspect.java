package com.bank.core.advice;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 创建aop切面
 * @author zzy
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class DynamicDataSourceAspect {
    //切入点只对@Service注解的类上的@DataSource方法生效
    @Pointcut(value="@within(org.springframework.stereotype.Service) && @annotation(dataSource)" )
    public void dynamicDataSourcePointCut(DataSource dataSource){}

    @Before(value = "dynamicDataSourcePointCut(dataSource)")
    public void switchDataSource(DataSource dataSource){
        log.info("##############数据源 ：{}###############",dataSource.value());
        DynamicDataSourceSwitcher.setDataSource(dataSource.value());
    }

    @After(value="dynamicDataSourcePointCut(dataSource)")
    public void after(DataSource dataSource){
        DynamicDataSourceSwitcher.cleanDataSource();
    }
}


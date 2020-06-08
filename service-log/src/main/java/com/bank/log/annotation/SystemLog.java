package com.bank.log.annotation;

import java.lang.annotation.*;

/**
 * @Author: Andy
 * @Date: 2020/4/1 9:22
 * 日志注解
 */
//注解放置的目标位置,METHOD是可注解在方法级别上
@Target(ElementType.METHOD)
//注解在哪个阶段执行
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 操作模块
     * @return
     */
    String logModul() default "";

    /**
     * 操作类型
     * @return
     */
    String logType() default "";

    /**
     * 操作说明
     * @return
     */
    String logDesc() default "";
}

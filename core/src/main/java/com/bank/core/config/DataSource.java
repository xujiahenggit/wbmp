package com.bank.core.config;

import java.lang.annotation.*;

/**
 * 自定义的数据源的注解
 * @author zzy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface DataSource {
    String value() default "master";
}


package com.bank.core.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 对象source私有属性复制给target对象的同名私有属性工具
 * 注意：只支持属性名完全相同的属性复制，支持忽略一些属性，其他需要手动get set
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public class PropertyUtil {

    /**
     * 将source对象的属性填充到target对象对应属性中
     *
     * @param source 原始对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, (String[]) null);
    }

    /**
     * 将source对象的属性填充到target对象对应属性中
     *
     * @param source           原始对象
     * @param target           目标对象
     * @param ignoreProperties 不转换的属性
     */
    public static void copyProperties(Object source, Object target, @Nullable String... ignoreProperties) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        try {
            Class<?> clsTarget = Class.forName(target.getClass().getName());
            Class<?> clsSource = Class.forName(source.getClass().getName());

            Field[] declaredFields = clsTarget.getDeclaredFields();
            Set<String> ignoreList = new HashSet<>();
            if (ignoreProperties != null) {
                ignoreList = Stream.of(ignoreProperties).collect(Collectors.toSet());
                ignoreList.add("serialVersionUID");
            } else {
                ignoreList.add("serialVersionUID");
            }
            //跳过serialVersionUID
            ignoreList.add("serialVersionUID");

            for (Field field : declaredFields) {

                String fieldName = field.getName();

                if (ignoreList.contains(fieldName)) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    Field sourceField = clsSource.getDeclaredField(fieldName);
                    sourceField.setAccessible(true);
                    field.set(target, sourceField.get(source));

                } catch (NoSuchFieldException e) {
                    // 没有对应属性跳过;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("PropertyUtils 属性转换错误");
        }
    }
}
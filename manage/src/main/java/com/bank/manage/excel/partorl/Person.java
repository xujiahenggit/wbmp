package com.bank.manage.excel.partorl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Andy
 * @Date: 2020/5/14 23:29
 */
@Data
public class Person {
    @Excel(name = "姓名", orderNum = "0")
    private String name;

    @Excel(name = "性别", replace = { "男_1", "女_2" }, orderNum = "1")
    private String sex;

    @Excel(name = "生日", format = "yyyy-MM-dd", orderNum = "2")
    private Date birthday;
}

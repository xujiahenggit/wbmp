package com.bank.manage.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamineDataVo implements Serializable {
    private static final long serialVersionUID = -6972903216337118420L;
    @ExcelProperty("分行名称(相同请合并)")
    private String branchOrgName;//分行机构名称
    @ExcelProperty("分支行机构号(相同请合并)")
    private String branchOrgId;//分行机构号
    @ExcelProperty("网点名称(相同请合并)")
    private String outOrgName;//网点名称
    @ExcelProperty("网点机构号(相同请合并)")
    private String outOrgId;//网点机构号
    @ExcelProperty("支行得分(相同请合并)")
    private String outExamineScore;//网点得分
    @ExcelProperty("一级模块")
    private String oneModule;//一级描述
    @ExcelProperty("二级模块")
    private String twoModule;//二级描述
    @ExcelProperty("指标名称")
    private String indicatorName;//指标名称
    @ExcelProperty("扣分值")
    private String deduction;//扣分值
    @ExcelProperty("扣分描述")
    private String deductionSpec;//扣分描述
    @ExcelProperty("分行得分(相同请合并)")
    private String examineScore;//考核得分



}

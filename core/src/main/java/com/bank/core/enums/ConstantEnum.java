package com.bank.core.enums;

public enum ConstantEnum {

    TRADE_TYPE_SCWJ("T_MATERIAL", "素材管理表"), TRADE_MODULE_SCWJ("素材管理", "模块名"),

    EXAMPLE_FAIUL("0", "永久有效"), EXAMPLE_DICT("EXAMPLE_DICT", "全国标杆网点字典"),

    CATALOG("1", "素材所有分类"),

    GROUP_DEFAULT("2", "设备默认分组"),

    MATERIAL_TYPE_IMAGE("00", "图片素材"), MATERIAL_TYPE_VIDEO("01", "视频素材"), MATERIAL_TYPE_PDF("02", "PDF素材"), MATERIAL_TYPE_TEXT("03", "文字素材"),

    STAFF_TYPE_MODULE("T_STAFF", "行员管理"),

    STAFF_ADD("新增行员", "新增行员"), STAFF_UPDATE("更新行员", "更新行员"), STAFF_DELETE("删除行员", "删除行员"), STAFF_MODULE("行员管理", "行员管理"),

    DEPOSIT_TYPE_00("00", "储蓄存款"), DEPOSIT_TYPE_01("01", "一般性存款"), DEPOSIT_TYPE_02("02", "对公存款"),

    AREA_TYPE_TEXT("marquee", "文字滚动区"), AREA_TYPE_PLAY("play", "播放区");

    /**
     * 状态码
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    ConstantEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

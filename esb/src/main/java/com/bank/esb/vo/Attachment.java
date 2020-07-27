package com.bank.esb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    private String pictureUrl;//附件影像id
    private String pictureUploadDate;//附件影像时间
}

package com.bank.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransInfoDTO implements Serializable {
    private Integer transH;
    private Integer transHnum;
}

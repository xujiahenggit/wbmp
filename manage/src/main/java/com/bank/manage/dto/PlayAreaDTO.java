package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "节目发布数据包装")
public class PlayAreaDTO implements Serializable {

    private static final long serialVersionUID = 6644651180183667135L;

    private List<DeviceDTO> deviceList;

    private List<PlayAreaMaterialDTO> playAreaMateriaList;

    private ProgramDTO programDTO;

}

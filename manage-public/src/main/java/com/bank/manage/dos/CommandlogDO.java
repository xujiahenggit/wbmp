package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("z_vw_command_log")
public class CommandlogDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String type;
    private String name;
    private String termIp;
    private String termNum;
    private Integer pendStatus;
    private Integer responseStatus;
    private String request;
    private String response;
    private String operatorNum;
    private String operatorName;
    private LocalDateTime createdAt;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    private String strBankNum;
    private String strBranchNum;
    private String SubBranchNum;
    private String SsbNum;

    /**
     * 权限号码
     */
    private String powerNum;
//    private LocalDateTime updatedAt;
    /*
    * "id": 1434,
                "type": "GetVersion",
                "name": "版本信息",
                "request": "{"TermNum":"80010038","MsgType":"GetVersion","OperatorId":"10010038","MsgTypeDesc":"版本信息","NetAddr":"162.16.129.16"}",
                "pendStatus": 2,
                "termNum": "80010038",
                "termIp": "162.16.129.16",
                "operatorNum": "10010038",
                "responseStatus": 1,
                "response": "{"TermNum":"80010038","VersionFile15":"C:\\SSA\\DeviceController\\KDMexp.dll","VersionFile16":"C:\\SSA\\DeviceController\\Utils.ocx","Version8":"DCKeyLoader_Keyou","VersionFile13":"C:\\SSA\\DeviceController\\DCTrace.dll","Version9":"DCOperatorPanel","DCPrinter":"2.0.1.1","VersionFile14":"C:\\SSA\\DeviceController\\DCVendorMode.ocx","VersionFile11":"C:\\SSA\\DeviceController\\DCPrinter.ocx","VersionFile12":"C:\\SSA\\DeviceController\\DCSensorsAndIndicators.ocx","VersionFile10":"C:\\SSA\\DeviceController\\DCPinPad.ocx","DCPinPad":"2.0.2.2","VersionFile2":"C:\\SSA\\SSApp\\Bin\\SSAJournal.exe","VersionFile3":"C:\\SSA\\SSApp\\Bin\\SSAppTool.exe","RetCode":"0000","VersionFile0":"C:\\SSA\\SSApp\\Bin\\SSApp.exe","VersionFile1":"C:\\SSA\\SSApp\\Bin\\SSAView.exe","SSApp":"2.0.2.3","SSAView":"2.0.2.2","VersionFile6":"C:\\SSA\\DeviceController\\DCCashAcceptor.ocx","VersionFile7":"C:\\SSA\\DeviceController\\DCCashDispenser.ocx","VersionFile4":"C:\\SSA\\DeviceController\\DCCardReader.ocx","VersionFile5":"C:\\SSA\\DeviceController\\DCCardRetainerBin.ocx","VersionFile8":"C:\\SSA\\DeviceController\\DCKeyLoader_Keyou.ocx","VersionFile9":"C:\\SSA\\DeviceController\\DCOperatorPanel.ocx","DCCardReader":"2.0.1.1","Version10":"DCPinPad","Version14":"DCVendorMode","Version13":"DCTrace","Version12":"DCSensorsAndIndicators","Version11":"DCPrinter","SSAppTool":"2.0.1.4","Version16":"Utils","Version15":"KDMexp","DCOperatorPanel":"2.0.1.2","DCCashAcceptor":"2.0.1.2","DCCashDispenser":"2.0.1.0","DCVendorMode":"2.0.1.0","DCTrace":"2.0.1.2","KDMexp":"1.0.1.1","DCSensorsAndIndicators":"2.0.1.0","SSAJournal":"2.0.2.2","MsgType":"GetVersion","Version0":"SSApp","DCCardRetainerBin":"2.0.1.0","Version1":"SSAView","Version2":"SSAJournal","Version3":"SSAppTool","Version4":"DCCardReader","Version5":"DCCardRetainerBin","Utils":"2.0.1.7","Version6":"DCCashAcceptor","Version7":"DCCashDispenser","DCKeyLoader_Keyou":"2.0.0.4"}",
                "createdAt": "2020-07-21 17:25:10",
                "updatedAt": "2020-07-21 17:25:11",
                "strBankNum": "1001",
                "strBranchNum": "0000",
                "operatorName": "张衡"*/
}

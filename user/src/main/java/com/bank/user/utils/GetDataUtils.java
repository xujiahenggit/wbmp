package com.bank.user.utils;

import cn.hutool.crypto.SecureUtil;
import com.bank.core.utils.Md5Utils;
import com.bank.user.dos.*;

import java.util.ArrayList;
import java.util.List;

import static com.bank.core.utils.Md5Utils.getEnCode;

/**
 * @Author: Andy
 * @Date: 2020/4/1 17:29
 * 解析大数据文件用
 */
public class GetDataUtils {

    /**
     * 密码加密
     */
    private static final String PASSWORD= Md5Utils.getEnCode("88888888");

    public static List<OrganizationTempDO> getOrgData(List<List<Object>> list){
        List<OrganizationTempDO> listOrg=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Object[] arrayList=list.get(i).toArray();
            OrganizationTempDO organizationDO=new OrganizationTempDO();
            //设置机构ID
            organizationDO.setOrgId(arrayList[0].toString());
            //设置机构名称
            organizationDO.setOrgName(arrayList[1].toString());
            //设置父机构ID
            organizationDO.setParentId(arrayList[2].toString());
            //设置单位ID
            organizationDO.setOrgUnitId(arrayList[3].toString());
            //设置单位名称
            organizationDO.setOrgUnitName(arrayList[4].toString());
            //设置部门ID
            organizationDO.setOrgDepartId(arrayList[5].toString());
            //设置部门名称
            organizationDO.setOrgDepartName(arrayList[6].toString());
            //设置核心机构号
            organizationDO.setOrgCode(arrayList[7].toString());
            //设置机构性质
            organizationDO.setOrgType(arrayList[8].toString());
            //设置营业状态
            organizationDO.setOrgStatus(arrayList[14].toString());
            //设置委托机构编号
            organizationDO.setOrgTrustId("100");
            //设置末级标识
            organizationDO.setOrgLastflag(arrayList[11].toString());
            //设置机构等级
            organizationDO.setOrgLevel(arrayList[17].toString());
            //设置父机构名称为空
            organizationDO.setParentName(null);
            listOrg.add(organizationDO);
        }
        return listOrg;
    }

    /**
     * 获取用户数据
     * @param list 大数据文件读出的原始数据
     * @return UserDO 集合
     */
    public static List<UserTempDO> getUserData(List<List<Object>> list){
        List<UserTempDO> listuser=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Object[] arrayList=list.get(i).toArray();
            UserTempDO userDO=new UserTempDO();
            //设置ID
            userDO.setUserId(arrayList[0].toString());
            //设置密码
            userDO.setPassword(PASSWORD);
            //设置用户名
            userDO.setUserName(arrayList[1].toString());
            //设置机构编号
            userDO.setOrgId(arrayList[3].toString());
            //设置机构名称
            userDO.setOrgName(arrayList[4].toString());
            //设置岗位ID
            userDO.setPositionId(arrayList[19].toString());
            // 设置岗位名称
            userDO.setPositionName(arrayList[20].toString());
            //设置部门编号
            userDO.setDepartId(arrayList[5].toString());
            //设置部门名称
            userDO.setDepartName(arrayList[6].toString());
            //设置是否在岗
            userDO.setUserStatus("1");
            //设置手机号码
            userDO.setUserPhone(arrayList[42].toString());
            //设置性别
            userDO.setUserGender(arrayList[51].toString());
            //设置证件号码
            userDO.setUserIdentiyno(arrayList[29].toString());
            listuser.add(userDO);
        }
        return listuser;
    }

    /**
     * 获取核心机构号集合
     * @param list 大数据文件已读内容
     * @return
     */
    public static List<NfrtOrgTempDO> getNfrtOrgList(List<List<Object>> list){
        List<NfrtOrgTempDO> listOrg=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Object[] arrayList=list.get(i).toArray();
            NfrtOrgTempDO nfrtOrgDO=new NfrtOrgTempDO();
            //设置6位机构号
            nfrtOrgDO.setNfrtOrgId(arrayList[0].toString());
            //设置柜组号
            nfrtOrgDO.setNfrtOrgNo(arrayList[1].toString());
            //设置中文机构名
            nfrtOrgDO.setNfrtOrgName(arrayList[2].toString());
            //设置分行代码
            nfrtOrgDO.setNfrtOrgBranchNo(arrayList[3].toString());
            //设置分行中文名
            nfrtOrgDO.setNfrtOrgBranchName(arrayList[4].toString());
            //设置机构状态
            nfrtOrgDO.setNfrtOrgState(arrayList[5].toString());
            //设置法人机构号
            nfrtOrgDO.setNfrtOrgLegalId(arrayList[6].toString());
            //设置业务种类
            nfrtOrgDO.setNfrtOrgBusType(arrayList[7].toString());
            //设置业务关系机构
            nfrtOrgDO.setNfrtOrgBusOrgid(arrayList[8].toString());
            //设置机构级别
            nfrtOrgDO.setNfrtOrgLevel(arrayList[9].toString());
            //设置机构类型
            nfrtOrgDO.setNfrtOrgType(arrayList[10].toString());
            listOrg.add(nfrtOrgDO);
        }
        return listOrg;
    }
}

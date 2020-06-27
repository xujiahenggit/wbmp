package com.bank.manage.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.bank.core.entity.BizException;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.HttpUtil;
import com.bank.core.utils.NetUtil;
import com.bank.core.utils.SignUtil;
import com.bank.manage.dos.GameDO;
import com.bank.manage.dto.DeviceDTO;
import com.bank.manage.service.DeviceService;
import com.bank.manage.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: GameController
 * <p>
 * 点点乐、星球大战、产品公告板、员工风采
 *
 * @author Yanwen D. Ding
 * <p>
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 * <p>
 * All Rights Reserved
 * <p>
 * http://www.yusys.com.cn
 * <p>
 * Create Time: 2020/04/09 10:10:18
 */
@Api(tags = "安卓端活动游戏接口")
@RestController
@RequestMapping("/grame")
@Slf4j
public class GameController {

    @Autowired
    private DeviceService deviceService;

    @Resource
    private NetUtil netUtil;

    @Resource
    private ConfigFileReader configFileReader;

    @ApiOperation(value = "获取游戏二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "macaddress", value = "mac地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1:星球大战;2:点点乐", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "String", paramType = "query") })
    @PostMapping("/queryGameQr")
    public Map<String, Object> queryGameQr(@RequestParam("macaddress") String macaddress, @RequestParam("type") String type, @RequestParam("timestamp") String timestamp, @RequestParam("sign") String sign, HttpServletRequest request) {
        log.info("获取游戏二维码，Mac地址【{}】游戏类型【{}】", macaddress, type);

        SignUtil.addString("macaddress", macaddress);
        SignUtil.addString("type", type);
        SignUtil.addString("timestamp", timestamp);
        String sin = SignUtil.spellString();
        log.info("获取游戏二维码开始，签名：{}", sin);

        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.equals(sin, sign)) {
            String fileName = new Date().getTime() + ".jpg";
            String localImgPath = this.configFileReader.getUPLOAD_FILE_PATH() + this.configFileReader.getQrImages() + "/" + DateUtil.today() + "/" + fileName;
            log.info("当前本地二维码图片存放地址：{}", localImgPath);
            FileUtil.mkParentDirs(localImgPath);
            String baseUrl = netUtil.getUrlSuffix("");
            String content = baseUrl;
            if (StringUtils.equals(type, "1")) {
                content += this.configFileReader.getHTTP_XQDZ() + macaddress;
            }
            else {
                content += this.configFileReader.getHTTP_DDL() + macaddress;
            }
            try {
                // 生成指定url对应的二维码到文件，宽和高都是300像素
                QrCodeUtil.generate(content, 300, 300, FileUtil.file(localImgPath));
                resultMap.put("success", true);
                resultMap.put("qrcodepath", baseUrl + localImgPath);
            }
            catch (Exception e) {
                log.info("生成游戏二维码图片失败");
                resultMap.put("success", false);
            }
        }
        else {
            resultMap.put("success", false);
        }

        return resultMap;
    }

    @ApiOperation(value = "设备入网校验-返回机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "macaddress", value = "mac地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "String", paramType = "query") })
    @PostMapping("/queryOrgcode")
    public DeviceDTO queryOrgcode(@RequestParam("macaddress") String macaddress, @RequestParam("timestamp") String timestamp, @RequestParam("sign") String sign) {
        log.info("设备入网校验开始，Mac地址【{}】", macaddress);

        SignUtil.addString("macaddress", macaddress);
        SignUtil.addString("timestamp", timestamp);
        String sin = SignUtil.spellString();
        log.info("设备入网校验开始，签名：{}", sin);

        DeviceDTO deviceDTO = new DeviceDTO();
        if (StringUtils.equals(sin, sign)) {
            deviceDTO = this.deviceService.queryDeviceByMac(macaddress.toUpperCase());
            deviceDTO.setServerTimeStamp(new Date().getTime());
        }
        else {
            throw new BizException("设备入网校验，签名失败", new HashMap<String, String>());
        }
        return deviceDTO;
    }

    @ApiOperation(value = "获取员工风采信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orgcode", value = "机构号", dataType = "string", paramType = "query") })
    @PostMapping("/queryStaff")
    public Map<String, Object> queryStaff(@RequestParam("timestamp") String timestamp, @RequestParam("sign") String sign, @RequestParam("orgcode") String orgcode) {
        log.info("获取员工风采信息，机构号【{}】", orgcode);

        SignUtil.addString("orgcode", orgcode);
        SignUtil.addString("timestamp", timestamp);
        String sin = SignUtil.spellString();
        log.info("获取员工风采信息开始，签名：{}", sin);

        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.equals(sin, sign)) {
        }
        else {
            throw new BizException("获取员工风采信息，签名失败");
        }
        return resultMap;
    }

    @ApiOperation(value = "获取产品公告信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "terminalnum", value = "设备号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orgcode", value = "机构号", dataType = "string", paramType = "query") })
    @PostMapping("/getProduct")
    public Map<String, Object> getProduct(@RequestParam("terminalnum") String terminalnum, @RequestParam("orgcode") String orgcode) {
        log.info("获取产品公告信息，设备号【{}】机构号【{}】", terminalnum, orgcode);

        Map<String, Object> resultMap = new HashMap<>();
        return resultMap;
    }

    @Resource
    private GameService gameService;

    /**
     * 保存游戏结果
     */
    @ApiOperation("保存游戏结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1，星球大战，2点点乐", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gameresult", value = "保存的游戏列表", dataType = "string", paramType = "query") })
    @PostMapping("/saveGame")
    public Object saveGame(@RequestParam("type") String type, @RequestParam("timestamp") String timestamp, @RequestParam("sign") String sign, @RequestParam("gameresult") String gameresult) {
        log.info("开始保存游戏结果功能type:" + type + ",timestamp:" + timestamp + ",sign:" + sign);
        Map<String, Object> data = new HashMap<>();
        try {
            SignUtil.addString("type", type);
            SignUtil.addString("timestamp", timestamp);
            String sin = SignUtil.spellString();
            if (sin.equals(sign)) {
                if (StringUtils.isNotBlank(gameresult)) {
                    JSONArray arrayList = JSONUtil.parseArray(gameresult);
                    //传递过来需要保存的信息
                    List<GameDO> list = JSONUtil.toList(arrayList, GameDO.class);
                    //批量保存
                    this.gameService.saveBatch(list);
                    String ids = list.stream().map(GameDO::getUsernum).collect(Collectors.joining("','"));
                    //返回这几个人的排名信息
                    List<GameDO> ranks = this.gameService.getGameRankInIds(type, ids);
                    data.put("ranking", ranks);
                }
                //前六名的信息
                data.put("resultlist", this.gameService.getGameRankByType(type, 6));
            }
            else {
                throw new BizException("签名错误");
            }
        }
        catch (Exception e) {
            log.error("保存游戏结果功能异常,游戏类型：[" + type + "]" + e.getMessage(), e);
            throw new BizException(e.getMessage());
        }
        log.info("保存游戏结果功能完成");
        return data;
    }

    /**
     * 查询用户游戏排名
     */
    @ApiOperation("查询用户游戏排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1，星球大战，2点点乐", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "usernum", value = "用户num", dataType = "string", paramType = "query") })
    @PostMapping("/queryRankByUsernum")
    public GameDO queryRankById(@RequestParam("type") String type, @RequestParam("usernum") String usernum) {
        //返回这几个人的排名信息
        List<GameDO> ranks = this.gameService.getGameRankInIds(type, usernum);
        if (ranks.size() < 1) {
            throw new BizException("没有查询到该用户的游戏信息");
        }
        return ranks.get(0);
    }

    @ApiOperation("切换游戏")
    @PostMapping("/change/{code}/{deviceNo}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "000010,星球大战；000020，点点乐", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "orgId", value = "机构号", required = true, dataType = "String") })
    public boolean changeGame(@PathVariable String code, @PathVariable String deviceNo, String orgId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", code);
        paramMap.put("deviceNo", deviceNo);
        paramMap.put("orgId", orgId);
        paramMap.put("msg", code.equals("000010") ? "星球大战" : "点点乐");
        String jmsPath =  netUtil.getUrlSuffix()+"/jms/topic";
        //        jmsPath = "http://localhost:8088/jms/topic";
        String post = HttpUtil.sendPost(jmsPath, paramMap);
        log.info("切换游戏，远程调用{}的返回值为{}", jmsPath, post);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = null;
        try {
            map = objectMapper.readValue(post, Map.class);
        }
        catch (IOException e) {
            log.info("{}转map失败", post);
            return false;
        }
        return Boolean.valueOf(map.get("data").toString());
    }

}

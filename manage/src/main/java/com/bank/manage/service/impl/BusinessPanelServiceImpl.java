package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.bank.core.sysConst.WbmpConstFile;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.BusinessPanelDao;
import com.bank.manage.dos.WbmpAtmpBasicInfoDO;
import com.bank.manage.service.BusinessPanelService;
import com.bank.manage.vo.*;
import org.bouncycastle.crypto.engines.AESLightEngine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 运营看板统计 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
@Service
public class BusinessPanelServiceImpl implements BusinessPanelService {

    @Resource
    BusinessPanelDao businessPanelDao;

    @Override
    public List<TellerOnlineInfo> getTellerOnlineInfo() {
        return businessPanelDao.getTellerOnlineInfo(null);
    }

    @Override
    public TellerOnlineInfo getTellerOnlineInfo(String tellerId) {
        List<TellerOnlineInfo> tellerOnlineInfo = businessPanelDao.getTellerOnlineInfo(tellerId);
        if (tellerOnlineInfo.size() == 1) {
            return tellerOnlineInfo.get(0);
        }
        return null;
    }

    @Override
    public Map<String, Integer> deviceTotal(String orgId) {
        return businessPanelDao.deviceTotal(orgId);
    }

    @Override
    public List<Map<String, Integer>> deviceStatus(String orgId) {
        return businessPanelDao.deviceStatus(orgId);
    }

    @Override
    public List<TransCntInfo> devicetransCnfTop5(String orgId) {
        List<TransCntInfo> list = businessPanelDao.devicetransCnfTop5(orgId);
        Number sum = businessPanelDao.devicetransCnfSum(orgId);
        list.stream().forEach(e -> e.setPercent(NumberUtil.div(e.getSum(), sum, 4)));
        return list;
    }

    @Override
    public List<TransCntInfo> tradeVolumeTop5(String orgId) {
         List<TransCntInfo> list = businessPanelDao.tradeVolumeTop5(orgId);
        Integer sum = businessPanelDao.tradeVolumeSum(orgId);
        if (sum!=null&&sum!=0){
            list.stream().forEach(e -> e.setPercent(NumberUtil.div(e.getSum(), sum, 4)));
        }
        return list;
    }

    @Override
    public List<AbsTellerInfo> tellertPageList(String orgId) {
//        Page<AbsTellerInfo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
//        page.setRecords(businessPanelDao.tellertPageList(page, orgId));
        return businessPanelDao.tellertPageList(orgId)
                .stream()
                .map(e -> {
                    e.setOnLineTimeStr(DateUtils.formatSeconds(e.getOnLineTime()));
                    return e;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> queryOperation(String branch) {

        String nowStr = DateUtil.format(LocalDateTime.now(), "yyyyMMdd");
        List<Map<String,Object>> operationMap =  businessPanelDao.queryOperation(branch,nowStr);
        Map<String, Object> map = new HashMap<>();
        if (CollectionUtil.isNotEmpty(operationMap)) {
            for (Map<String, Object> objectMap : operationMap) {
                long queue_cnt = (Long) objectMap.get("queue_cnt");//排队总数
                long already_handle_cnt = (Long) objectMap.get("already_handle_cnt");//已办理总数
                long progress_handle_cnt = (Long) objectMap.get("progress_handle_cnt");//办理中总数
                long abandoned_cnt = (Long) objectMap.get("abandoned_cnt");//弃号总数
                long num = (Long) objectMap.get("num");//窗口数
                Number abandoned_lv =  (Number)objectMap.get("abandoned_lv");//弃号率
                Number index_cnt =  (Number)objectMap.get("index_cnt");//平均等待时长-秒
                Number avg_abandoned_lv =  (Number)objectMap.get("avg_abandoned_lv");//平均弃号率
                //排队预计等待时长=（等待人数/窗口数）*历史平均等待时长*历史1个月平均弃号率
                BigDecimal a = NumberUtil.div(String.valueOf(queue_cnt), String.valueOf(num));
                BigDecimal wait_time = NumberUtil.mul(a, index_cnt, avg_abandoned_lv);

                Map<String, Object> panelMap = new HashMap<>();
                panelMap.put("queue_cnt", queue_cnt);
                panelMap.put("wait_time", wait_time);
                panelMap.put("abandoned_lv", abandoned_lv);
                map.put("panelMap", panelMap);

                Map<String, Object> bargraphMap = new HashMap<>();
                long take_cnt = queue_cnt + already_handle_cnt + progress_handle_cnt + abandoned_cnt;
                bargraphMap.put("take_cnt", take_cnt);
                bargraphMap.put("queue_cnt", queue_cnt);
                bargraphMap.put("progress_handle_cnt", progress_handle_cnt);
                bargraphMap.put("already_handle_cnt", already_handle_cnt);
                bargraphMap.put("abandoned_cnt", abandoned_cnt);
                map.put("bargraphMap", bargraphMap);
            }
        }
        return map;
    }

    @Override
    public RankInfo rankInfo(String orgId, String tellerId) {
        Integer onlineSum = businessPanelDao.onlineSum(orgId);//在线人数
        Integer tradeSum = businessPanelDao.tradeSum(orgId);
        Float onlineTimeSum = businessPanelDao.onlineTimeSum(orgId);//总在线时长,秒钟
        Number onlineTimeSumMinute = NumberUtil.div(onlineTimeSum==null? 0L :onlineTimeSum.floatValue(), 60, 0);//总在线时长,转为分钟
        Integer tradeNumRank = businessPanelDao.tradeNumRank(orgId, tellerId);//交易量排名
        Integer onlineTimeRank = businessPanelDao.onlineTimeRank(orgId, tellerId);//在线时长排名
        return RankInfo
                .builder()
                .tellerId(tellerId)
                .onlineSum(onlineSum)
                .tradeNumAverage(valueContainNull(tradeSum,onlineSum)?null:NumberUtil.div(tradeSum,onlineSum, 0))
                .tradeNumRank(tradeNumRank)
                .onlineTimeAverage(valueContainNull(onlineTimeSumMinute, onlineSum)?null:NumberUtil.div(onlineTimeSumMinute, onlineSum, 0))//计算均值
                .onlineTimeRank(onlineTimeRank)
                .build();
    }

    @Override
    public List<DeviceInfoVo> getOrgDeviceList(String orgId) {
        return businessPanelDao.getOrgDeviceList(orgId);
    }

    @Override
    public DeviceDetailInfoVo findByDeviceId(String deviceId) {
            WbmpAtmpBasicInfoDO wbmpAtmpBasicInfoDO = businessPanelDao.findByDeviceId(deviceId);
            DeviceDetailInfoVo deviceDetailInfo =   transferDeviceType(wbmpAtmpBasicInfoDO);
            return deviceDetailInfo;
    }

    @Override
    public List<DeviceTradeTrendVo> deviceTradeList(String orgId,String termNo,String queryType) {
        List<DeviceTradeTrendVo> list = new ArrayList<>();
        if(WbmpConstFile.DATE_TYPE_MONTH .equals(queryType)){
            //查询条件是按月查询
             list = businessPanelDao.deviceMonthTradeList(orgId,termNo);
        }else if(WbmpConstFile.DATE_TYPE_YEAR .equals(queryType)) {
            list = businessPanelDao.deviceYearTradeList(orgId,termNo);
        }
        return list;
    }

    private  boolean valueContainNull(Number num1,Integer num2){
        return num1==null | num2 == null?true:false;
    }

    private  boolean valueContainNull(Integer num1,Integer num2){
        return num1==null | num2 == null?true:false;
    }


    /**
     * 设备详细状态转换
     * @param wbmpAtmpBasicInfoDO
     * @return
     */
  public  DeviceDetailInfoVo transferDeviceType( WbmpAtmpBasicInfoDO wbmpAtmpBasicInfoDO){
      DeviceDetailInfoVo deviceDetailInfo  = new DeviceDetailInfoVo();
      if(wbmpAtmpBasicInfoDO != null){
          deviceDetailInfo.setDeviceRunStatus(wbmpAtmpBasicInfoDO.getDeviceRunStatus());
          deviceDetailInfo.setDeviceVendor(wbmpAtmpBasicInfoDO.getDeviceVendor());
          deviceDetailInfo.setDeviceModel(wbmpAtmpBasicInfoDO.getDeviceModel());
          //读卡器状态+射频读卡器状态 = 读卡器状态
            String cardReader = WbmpConstFile.DEVICE_NO;
          if(wbmpAtmpBasicInfoDO.getCardReader() !=null &&  !"".equals(wbmpAtmpBasicInfoDO.getCardReader())){
                if(wbmpAtmpBasicInfoDO.getCardReader().equals("-1")){
                    //-1表示无效
                    cardReader = WbmpConstFile.DEVICE_NO;
                }else if(wbmpAtmpBasicInfoDO.getCardReader().equals(WbmpConstFile.DEVICE_RUN_OK)){
                    //等于ok表示模块正常
                    cardReader = WbmpConstFile.DEVICE_RUN_SUCESS;
                }else{
                    //不为空并且不等于-1.不等于ok，则为故障
                    cardReader = WbmpConstFile.DEVICE_RUN_ERROR;
                }
             }
          //射频读卡器状态
          if(wbmpAtmpBasicInfoDO.getRfcardReader()!=null && !"".equals(wbmpAtmpBasicInfoDO.getRfcardReader())){
              if(wbmpAtmpBasicInfoDO.getRfcardReader().equals(WbmpConstFile.DEVICE_RUN_OK)){ //OK
                    if(cardReader.equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                        cardReader = WbmpConstFile.DEVICE_RUN_ERROR;  //ok+error= error
                    }else{
                        cardReader = WbmpConstFile.DEVICE_RUN_SUCESS; //ok+无效=ok,ok+ok=ok
                    }
              }else if(wbmpAtmpBasicInfoDO.getRfcardReader().equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                  cardReader = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          deviceDetailInfo.setCardReader(cardReader);
          //密码键盘状态
          String pinPad = WbmpConstFile.DEVICE_NO;
          if(wbmpAtmpBasicInfoDO.getPinPad()!=null && !"".equals(wbmpAtmpBasicInfoDO.getPinPad())){
                if(wbmpAtmpBasicInfoDO.getPinPad().equals(WbmpConstFile.DEVICE_RUN_OK)){
                    pinPad = WbmpConstFile.DEVICE_RUN_SUCESS;
                }else if(wbmpAtmpBasicInfoDO.getPinPad().equals("-1")){
                    pinPad = WbmpConstFile.DEVICE_NO;
                }else{
                    pinPad = WbmpConstFile.DEVICE_RUN_ERROR;
                }
          }
          deviceDetailInfo.setPinPad(pinPad);
          //流水打印机状态
          String printer = WbmpConstFile.DEVICE_NO;
          if(wbmpAtmpBasicInfoDO.getJournalPrinter()!=null && !"".equals(wbmpAtmpBasicInfoDO.getJournalPrinter())){
              if(wbmpAtmpBasicInfoDO.getJournalPrinter().equals("-1")){
                  //-1表示无效
                  printer = WbmpConstFile.DEVICE_NO;
              }else if(wbmpAtmpBasicInfoDO.getJournalPrinter().equals(WbmpConstFile.DEVICE_RUN_OK)){
                  //等于ok表示模块正常
                  printer = WbmpConstFile.DEVICE_RUN_SUCESS;
              }else{
                  //不为空并且不等于-1.不等于ok，则为故障
                  printer = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          //凭条打印机状态
          if(wbmpAtmpBasicInfoDO.getReceiptPrinter()!=null && !"".equals(wbmpAtmpBasicInfoDO.getReceiptPrinter())){
              if(wbmpAtmpBasicInfoDO.getReceiptPrinter().equals(WbmpConstFile.DEVICE_RUN_OK)){ //OK
                  if(printer.equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                      printer = WbmpConstFile.DEVICE_RUN_ERROR;  //ok+error= error
                  }else{
                      printer = WbmpConstFile.DEVICE_RUN_SUCESS; //ok+无效=ok,ok+ok=ok
                  }
              }else if(wbmpAtmpBasicInfoDO.getRfcardReader().equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                  printer = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          //对帐单打印机状态
          if(wbmpAtmpBasicInfoDO.getStatementPrinter()!=null && !"".equals(wbmpAtmpBasicInfoDO.getStatementPrinter())){
              if(wbmpAtmpBasicInfoDO.getStatementPrinter().equals(WbmpConstFile.DEVICE_RUN_OK)){ //OK
                  if(printer.equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                      printer = WbmpConstFile.DEVICE_RUN_ERROR;  //ok+error= error
                  }else{
                      printer = WbmpConstFile.DEVICE_RUN_SUCESS; //ok+无效=ok,ok+ok=ok
                  }
              }else if(wbmpAtmpBasicInfoDO.getStatementPrinter().equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                  printer = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          //存折打印机状态
          if(wbmpAtmpBasicInfoDO.getPassbookPrinter()!=null && !"".equals(wbmpAtmpBasicInfoDO.getPassbookPrinter())){
              if(wbmpAtmpBasicInfoDO.getPassbookPrinter().equals(WbmpConstFile.DEVICE_RUN_OK)){ //OK
                  if(printer.equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                      printer = WbmpConstFile.DEVICE_RUN_ERROR;  //ok+error= error
                  }else{
                      printer = WbmpConstFile.DEVICE_RUN_SUCESS; //ok+无效=ok,ok+ok=ok
                  }
              }else if(wbmpAtmpBasicInfoDO.getPassbookPrinter().equals(WbmpConstFile.DEVICE_RUN_ERROR)){
                  printer = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          deviceDetailInfo.setPrinter(printer);

          //取款模块状态
            String cashDispenser = WbmpConstFile.DEVICE_NO;
          if(wbmpAtmpBasicInfoDO.getCashDispenser()!=null && !"".equals(wbmpAtmpBasicInfoDO.getCashDispenser())){
              if(wbmpAtmpBasicInfoDO.getCashDispenser().equals("-1")){
                  //-1表示无效
                  cashDispenser = WbmpConstFile.DEVICE_NO;
              }else if(wbmpAtmpBasicInfoDO.getCashDispenser().equals(WbmpConstFile.DEVICE_RUN_OK)){
                  //等于ok表示模块正常
                  cashDispenser = WbmpConstFile.DEVICE_RUN_SUCESS;
              }else if(wbmpAtmpBasicInfoDO.getCashDispenser().equals(WbmpConstFile.CASH_DISPENSER_CASSMORE)){
                  cashDispenser = WbmpConstFile.CASH_DISPENSER_CASSMORE_NAME;
              }else if(wbmpAtmpBasicInfoDO.getCashDispenser().equals(WbmpConstFile.CASH_DISPENSER_CASSEPT)){
                  cashDispenser = WbmpConstFile.CASH_DISPENSER_CASSEPT_NAME;
              }else{
                  //不为空并且不等于-1.不等于ok，则为故障
                  cashDispenser = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          deviceDetailInfo.setCashDispenser(cashDispenser);
          //存款模块状态
          String cashAcceptor = WbmpConstFile.DEVICE_NO;
          if(wbmpAtmpBasicInfoDO.getCashAcceptor()!=null && !"".equals(wbmpAtmpBasicInfoDO.getCashAcceptor())){
              if(wbmpAtmpBasicInfoDO.getCashAcceptor().equals("-1")){
                  //-1表示无效
                  cashAcceptor = WbmpConstFile.DEVICE_NO;
              }else if(wbmpAtmpBasicInfoDO.getCashAcceptor().equals(WbmpConstFile.DEVICE_RUN_OK)){
                  //等于ok表示模块正常
                  cashAcceptor = WbmpConstFile.DEVICE_RUN_SUCESS;
              }else if(wbmpAtmpBasicInfoDO.getCashAcceptor().equals(WbmpConstFile.CASH_ACCEPTOR_CASSFULL)){
                  cashAcceptor = WbmpConstFile.CASH_ACCEPTOR_CASSFULL_NAME;
              }else if(wbmpAtmpBasicInfoDO.getCashAcceptor().equals(WbmpConstFile.CASH_ACCEPTOR_CASSLESS)){
                  cashAcceptor = WbmpConstFile.CASH_ACCEPTOR_CASSLESS_NAME;
              }else{
                  //不为空并且不等于-1.不等于ok，则为故障
                  cashAcceptor = WbmpConstFile.DEVICE_RUN_ERROR;
              }
          }
          deviceDetailInfo.setCashAcceptor(cashAcceptor);
      }
      return deviceDetailInfo;
  }

}

package com.wotrd.dubboprovider.pay.wechatpay.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;

public interface WechatPayService {

    SortedMap<Object, Object> wechatPay(BigDecimal totalAmount, String orderNumber, String timeoutExpress, String ip);

    Map wechatRefund(BigDecimal refundMoney, String orderNumber, String ticketId, BigDecimal totalMoney);

    Map wechatRefundStatus(String orderNumber, String ticketId);
}

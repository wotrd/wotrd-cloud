package com.wotrd.dubboprovider.pay.alipay.service;

import java.math.BigDecimal;
import java.util.Map;

public interface AliPayService {

    Map payOrder(BigDecimal tradeMoney, String orderNumber, String timeoutExpress);

    Map refundOrder(String refundMoney, String orderNumber, String ticketId);

    Map refundStatus(String orderNumber, String ticketId);
}

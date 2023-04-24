package com.zhou.demo.config;

import com.zhou.demo.persist.po.OrderStatuePo;
import com.zhou.demo.persist.po.StatuePo;
import com.zhou.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisConfig
 * @Author zz
 * @Date 2023/4/24 14:16
 * @Version
 * @Description
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private OrderService orderService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件处理,进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //获取失效的key
        String expirationKey = message.toString();
        //对开头是ordertimeout的键进行处理
        if (expirationKey.startsWith("ordertimeout:")) {
            String[] split = expirationKey.split(":");
            String orderId = split[1];
            //处理订单
            endOrder(orderId);
        }
    }

    private void endOrder(String orderId) {
        String status = orderService.selectorderStatusByOrderId(orderId);
        if("0".equals(status)){
            OrderStatuePo statuePo = new OrderStatuePo();
            statuePo.setId(orderId);
            statuePo.setStatus("2");
            orderService.updateStatue(statuePo);
        }
    }
}

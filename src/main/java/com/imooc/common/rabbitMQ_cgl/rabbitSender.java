package com.imooc.common.rabbitMQ_cgl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class rabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         *
         * @param correlationData 作爲一個唯一標識
         * @param ack 是否落盤成功
         * @param cause 失敗的一些異常信息
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        }
    };
    public void send(Object message, Map<String,Object> properties){
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessagePostProcessor mpp = new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
                System.out.println("msg:"+message);
                return message;
            }
        };
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend("exchange-1",
                                        "springboot.rabbitmq",
                                        msg,
                                        mpp,
                                        correlationData
        );
    }
}

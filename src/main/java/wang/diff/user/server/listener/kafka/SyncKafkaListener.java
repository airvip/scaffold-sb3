package wang.diff.user.server.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SyncKafkaListener {
    @KafkaListener(topics = "KafkaSyncMessageRequest")
    @SendTo
    public String consumerSync(String message){
        log.warn("Kafka 消费者：Sync 接收到发送消息请求,消息内容:{}", message);
        return "PING PONG " + message;
    }
}

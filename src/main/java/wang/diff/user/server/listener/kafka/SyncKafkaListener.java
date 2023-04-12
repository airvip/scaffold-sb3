package wang.diff.user.server.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import wang.diff.user.server.constants.KafkaTopicConfig;
import wang.diff.user.server.dto.response.KafkaSyncHelloRespDTO;
import wang.diff.user.server.dto.response.KafkaSyncSayRespDTO;
import wang.diff.user.server.util.JacksonUtils;

@Slf4j
@Component
public class SyncKafkaListener {
    @KafkaListener(topics = KafkaTopicConfig.KafkaSyncMessage.REQUEST_TOPIC_NAME)
    @SendTo
    public String consumerSync(String message){
        log.warn("Kafka 消费者：Sync 接收到发送消息请求,消息内容:{}", message);

        val kafkaSyncHelloRespDTO = new KafkaSyncHelloRespDTO();
        kafkaSyncHelloRespDTO.setMsg("this hello");
        return JacksonUtils.toJson(kafkaSyncHelloRespDTO);
    }


    @KafkaListener(topics = KafkaTopicConfig.KafkaSyncMessageSay.REQUEST_TOPIC_NAME)
    @SendTo
    public String consumerSyncSay(String message){
        log.warn("Kafka 消费者：Sync 接收到发送消息请求,消息内容:{}", message);
        val kafkaSyncSayRespDTO = new KafkaSyncSayRespDTO();
        kafkaSyncSayRespDTO.setMsg("this say");
        return JacksonUtils.toJson(kafkaSyncSayRespDTO);
    }


}

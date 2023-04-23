package wang.diff.user.server.producer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import wang.diff.user.server.common.constants.KafkaTopicConfig;
import wang.diff.user.server.dto.SendShortMsgDTO;
import wang.diff.user.server.dto.SendVerificationCodeDTO;
import wang.diff.user.server.common.util.JacksonUtils;

@Slf4j
@Component
public class CommonKafkaProducer {

    // 没有使用 @Resource 是因为直接copy的文档
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CommonKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(SendVerificationCodeDTO sendVerificationCodeDTO) {
        String json = JacksonUtils.toJson(sendVerificationCodeDTO);
        send(KafkaTopicConfig.SEND_VERIFICATION_CODE, json);
    }


    public void send(SendShortMsgDTO sendShortMsgDTODTO) {
        String json = JacksonUtils.toJson(sendShortMsgDTODTO);
        send(KafkaTopicConfig.SEND_SHORT_MSG, json);
    }


    private void send(String kafkaTopic, String message) {
        log.info("Kafka 生产者：接收到 topic:{} 的消息:{}", kafkaTopic,message);
        kafkaTemplate.send(kafkaTopic, message);
    }

}

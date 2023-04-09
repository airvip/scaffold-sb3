package wang.diff.user.server.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import wang.diff.user.server.constants.KafkaTopicConfig;
import wang.diff.user.server.dto.SendVerificationCodeDTO;
import wang.diff.user.server.util.JacksonUtils;

@Slf4j
@Component
public class CommonKafkaListener {
    private static final String GROUP_ID = "spring-kafka";

    @KafkaListener(topics = KafkaTopicConfig.SEND_VERIFICATION_CODE, groupId = GROUP_ID)
//    @KafkaListener(topics = KafkaTopicConfig.SEND_VERIFICATION_CODE)
    public void processMessageSms(String message) {
        log.warn("receive sms topic message:{}", message);
        SendVerificationCodeDTO parse = JacksonUtils.parse(message, SendVerificationCodeDTO.class);
        log.info("接收到发送短信的数据,mobile:{},code:{}",parse.getMobile(),parse.getCode());
    }


    /**
     * 哈哈，感觉过度封装了，直接用也是一句话
     * @param message
     * @param clazz
     * @return
     * @param <T>
     */
    private <T> T parse(String message, Class<T> clazz) {
        return JacksonUtils.parse(message,clazz);
    }

}

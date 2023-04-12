package wang.diff.user.server.producer.kafka;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import wang.diff.user.server.constants.KafkaTopicConfig;
import wang.diff.user.server.dto.request.KafkaSyncHelloReqDTO;
import wang.diff.user.server.dto.request.KafkaSyncSayReqDTO;
import wang.diff.user.server.dto.response.KafkaSyncHelloRespDTO;
import wang.diff.user.server.dto.response.KafkaSyncSayRespDTO;
import wang.diff.user.server.util.JacksonUtils;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class SyncKafkaProducer {

    @Resource
    private ReplyingKafkaTemplate<String,String,String> replyingKafkaTemplate;

    public KafkaSyncHelloRespDTO send(KafkaSyncHelloReqDTO kafkaSyncHelloReqDTO) {
        val json = JacksonUtils.toJson(kafkaSyncHelloReqDTO);
        val send = send(json, KafkaTopicConfig.KafkaSyncMessage.REQUEST_TOPIC_NAME,KafkaTopicConfig.KafkaSyncMessage.RESPONSE_TOPIC_NAME);
        if(send == null){
            throw new RuntimeException("未等待到想要的结果");
        }
        val parse = JacksonUtils.parse(send, KafkaSyncHelloRespDTO.class);
        if(parse == null) {
            throw new RuntimeException("未解析到想要的结果");
        }
        return parse;
    }

    public KafkaSyncSayRespDTO send(KafkaSyncSayReqDTO kafkaSyncSayReqDTO) {
        val json = JacksonUtils.toJson(kafkaSyncSayReqDTO);
        val send = send(json,KafkaTopicConfig.KafkaSyncMessageSay.REQUEST_TOPIC_NAME,KafkaTopicConfig.KafkaSyncMessageSay.RESPONSE_TOPIC_NAME);
        if(send == null){
            throw new RuntimeException("未等待到想要的结果");
        }
        val parse = JacksonUtils.parse(send, KafkaSyncSayRespDTO.class);
        if(parse == null) {
            throw new RuntimeException("未解析到想要的结果");
        }
        return parse;
    }


    /**
     * 对内调用
     * @param message 请求串
     * @param requestTopic 请求topic
     * @param replyTopic 相应topic
     * @return 返回串
     */
    private String send(String message,String requestTopic, String replyTopic) {
        String returnMsg = null;
        try {
            log.info("开始同步发送kafka消息, 请求：{}", message);
            // 发送 topic
            ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, message);
            // 响应 topic
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
            RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
            SendResult<String, String> sendResult = replyFuture.getSendFuture().get();
            log.info("发送成功,{}", sendResult.getRecordMetadata());
            ConsumerRecord<String, String> consumerRecord = replyFuture.get();
            returnMsg = consumerRecord.value();
            log.info("结束同步发送kafka消息, 响应：{}", returnMsg);
        } catch (Exception e) {
            log.error("同步发送消息失败, {}",e.getMessage());
            throw new RuntimeException(e);
        }
        return returnMsg;
    }



}

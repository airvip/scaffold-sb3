package wang.diff.user.server.producer.kafka;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class SyncKafkaProducer {

    @Resource
    private ReplyingKafkaTemplate<String,String,String> replyingKafkaTemplate;


    public String send(String message) {
        String returnMsg = null;
        try {
            log.info("开始同步发送kafka消息, 请求：{}", message);
            // 发送 topic
            ProducerRecord<String, String> record = new ProducerRecord<>("KafkaSyncMessageRequest", message);
            // 响应 topic
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "KafkaSyncMessageResponse".getBytes()));
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

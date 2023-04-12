package wang.diff.user.server.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import diff.wang.user.server.controller.PingApi;
import wang.diff.user.server.dto.request.KafkaSyncHelloReqDTO;
import wang.diff.user.server.dto.request.KafkaSyncSayReqDTO;
import wang.diff.user.server.producer.kafka.SyncKafkaProducer;

@Slf4j
@RestController
@Tag(name = "ping")
public class PingController implements PingApi {

    @Resource
    private SyncKafkaProducer syncKafkaProducer;

    @Override
    public ResponseEntity<String> ping(@Valid String name) {
        val kafkaSyncHelloReqDTO = new KafkaSyncHelloReqDTO();
        kafkaSyncHelloReqDTO.setMsg("hello");
        kafkaSyncHelloReqDTO.setCode(1);
        val send = syncKafkaProducer.send(kafkaSyncHelloReqDTO);
        log.info("send:{}",send);

        val kafkaSyncSayReqDTO = new KafkaSyncSayReqDTO();
        kafkaSyncSayReqDTO.setMsg("say");
        kafkaSyncSayReqDTO.setCode(1);
        val send1 = syncKafkaProducer.send(kafkaSyncSayReqDTO);
        log.info("send1:{}",send1);

        return ResponseEntity.ok("ping pong");
    }
}

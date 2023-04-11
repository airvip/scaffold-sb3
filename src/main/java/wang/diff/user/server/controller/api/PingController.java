package wang.diff.user.server.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import diff.wang.user.server.controller.PingApi;
import wang.diff.user.server.producer.kafka.SyncKafkaProducer;

@RestController
@Tag(name = "ping")
public class PingController implements PingApi {

    @Resource
    private SyncKafkaProducer syncKafkaProducer;

    @Override
    public ResponseEntity<String> ping(@Valid String name) {
        val send = syncKafkaProducer.send(name);
        return ResponseEntity.ok(send);
    }
}

package wang.diff.user.server.controller.common;

import diff.wang.user.server.controller.SendMsgApi;
import diff.wang.user.server.controller.model.SendMsgOfMobileCodeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.user.server.dto.SendVerificationCodeDTO;
import wang.diff.user.server.producer.kafka.CommonKafkaProducer;
import wang.diff.user.server.common.util.MiscUtils;

@Tag(name = "send-msg")
@Slf4j
@RestController
public class SendMsgController implements SendMsgApi {

    @Resource
    private CommonKafkaProducer commonKafkaProducer;

    @Override
    public ResponseEntity<Void> sendMsgCode(SendMsgOfMobileCodeDTO sendMsgOfMobileCodeDTO) {
        log.info("to send msg >>> {}", sendMsgOfMobileCodeDTO.getMobile());
        SendVerificationCodeDTO sendVerificationCodeDTO = new SendVerificationCodeDTO();
        sendVerificationCodeDTO.setMobile(sendMsgOfMobileCodeDTO.getMobile());
        sendVerificationCodeDTO.setCode(MiscUtils.generateRandomString());
        commonKafkaProducer.send(sendVerificationCodeDTO);
        return ResponseEntity.ok().build();
    }


}

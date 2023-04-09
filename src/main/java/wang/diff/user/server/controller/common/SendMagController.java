package wang.diff.user.server.controller.common;

import diff.wang.user.server.controller.SendMsgApi;
import diff.wang.user.server.controller.model.SendMsgOfMobileCodeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.user.server.dto.SendVerificationCodeDTO;
import wang.diff.user.server.producer.kafka.CommonKafkaProducer;
import wang.diff.user.server.util.MiscUtils;

@Tag(name = "send-msg")
@RestController
public class SendMagController implements SendMsgApi {

    @Resource
    private CommonKafkaProducer commonKafkaProducer;

    @Override
    public ResponseEntity<Void> sendMobileCodePost(SendMsgOfMobileCodeDTO sendMsgOfMobileCodeDTO) {
        SendVerificationCodeDTO sendVerificationCodeDTO = new SendVerificationCodeDTO();
        sendVerificationCodeDTO.setMobile(sendMsgOfMobileCodeDTO.getMobile());
        sendVerificationCodeDTO.setCode(MiscUtils.generateRandomString());
        commonKafkaProducer.send(sendVerificationCodeDTO);
        return ResponseEntity.ok().build();
    }


}

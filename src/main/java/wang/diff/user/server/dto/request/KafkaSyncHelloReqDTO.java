package wang.diff.user.server.dto.request;

import lombok.Data;

@Data
public class KafkaSyncHelloReqDTO {
    private int code;
    private String msg;
}

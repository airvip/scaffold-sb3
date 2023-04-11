package wang.diff.user.server.dto;

import lombok.Data;

@Data
public class SendShortMsgDTO {
    private String mobile;
    private String msg;
}

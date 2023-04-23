package wang.diff.user.server.controller.api;

import cn.hutool.core.util.StrUtil;
import diff.wang.user.server.controller.SessionApi;
import diff.wang.user.server.controller.model.BaseResp;
import diff.wang.user.server.controller.model.SessionLoginDTO;
import diff.wang.user.server.controller.model.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.user.server.common.util.JwtUtils;
import wang.diff.user.server.common.util.MiscUtils;
import wang.diff.user.server.service.UserService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Tag(name = "session")
public class SessionController implements SessionApi {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private UserService userService;

    @Override
    public ResponseEntity<BaseResp> login(SessionLoginDTO sessionLoginDTO) {
        log.info("this is a info information");

        UserDTO user = userService.getByMobile(sessionLoginDTO.getMobile());
        List<String> role = new ArrayList<>();
        role.add("ADMIN");
        String token = JwtUtils.createToken(user.getId().toString(), role);
        return ResponseEntity.ok(new BaseResp().message(token));
    }
}

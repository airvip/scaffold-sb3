package wang.diff.user.server.controller.api;

import cn.hutool.core.util.StrUtil;
import diff.wang.user.server.controller.SessionApi;
import diff.wang.user.server.controller.model.BaseResp;
import diff.wang.user.server.controller.model.SessionLoginDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.user.server.util.MiscUtils;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Tag(name = "session")
public class SessionController implements SessionApi {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResponseEntity<BaseResp> login(SessionLoginDTO sessionLoginDTO) {
        log.info("this is a info information");
        // 了解
        val valueOps = redisTemplate.boundValueOps("session." + sessionLoginDTO.getMobile());
        String randomString = MiscUtils.generateRandomString();
        valueOps.set(randomString, Duration.ofSeconds(10L));

        // 推荐
        redisTemplate.opsForValue().set("now-mobile", randomString, 20L, TimeUnit.SECONDS);

        String idsKey = "order.ids";

        for (Integer i = 0; i<10; i++) {
            redisTemplate.opsForZSet().add(idsKey, i.toString(), MiscUtils.generateRandomDouble());
        }

        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().rangeWithScores(idsKey, 0, -1);
        typedTuples.stream().forEach(x-> {
            log.info("........score:{},value:{}.....",x.getScore(),x.getValue());
            if( x.getValue().equals("3")){
                redisTemplate.opsForZSet().remove(idsKey, x.getValue());
            }
        });

        log.info(StrUtil.repeat("=====", 10));

        Set<ZSetOperations.TypedTuple<String>> typedTuplesNext = redisTemplate.opsForZSet().rangeWithScores(idsKey, 0, -1);
        typedTuplesNext.stream().forEach(x-> {
            log.info("........score:{},value:{}.....",x.getScore(),x.getValue());
        });



        return ResponseEntity.ok(new BaseResp().message(randomString));
    }
}

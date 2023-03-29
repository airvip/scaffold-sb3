package wang.diff.user.server.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import wang.diff.user.server.UserServerApplication;
import wang.diff.user.server.util.MiscUtils;
import org.springframework.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserServerApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class MiscUtilsTest {


    @Test
    public void genOrderNoTest() {
        String prefix = "ORD";
        String orderNo = MiscUtils.genOrderNo(prefix);
        log.info(orderNo);
        Assert.notNull(orderNo, "未生成订单号");
        Assert.isTrue(orderNo.startsWith(prefix), "订单号前缀不正确");
    }
}

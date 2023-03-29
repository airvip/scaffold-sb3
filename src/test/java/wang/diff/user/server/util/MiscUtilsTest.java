package wang.diff.user.server.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MiscUtilsTest {


    @Test
    public void genOrderNoTest() {
        String prefix = "ORD";
        String orderNo = MiscUtils.genOrderNo(prefix);
        log.info("生成的订单号为:{}", orderNo);
        Assert.notNull(orderNo, "未生成订单号");
        Assert.isTrue(orderNo.startsWith(prefix), "订单号前缀不正确");
    }
}

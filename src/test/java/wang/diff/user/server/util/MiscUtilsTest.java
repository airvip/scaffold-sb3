package wang.diff.user.server.util;

import lombok.extern.slf4j.Slf4j;
import wang.diff.user.server.UserServerApplicationTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserServerApplicationTest.class})
class MiscUtilsTest {


    @Test
    public void genOrderNoTest() {
        String prefix = "ORD";
        String orderNo = MiscUtils.genOrderNo(prefix);
        log.info("生成的订单号为:{}", orderNo);
        Assert.notNull(orderNo, "未生成订单号");
        Assert.isTrue(orderNo.startsWith(prefix), "订单号前缀不正确");
    }


    @Test
    public void timeStr2timeUnixTest() {
        Long timeStr2timeUnix = MiscUtils.timeStr2timeUnix("12:00", "2023-03-30");
        log.info("2023-03-30 12:00 的时间戳是:{}", timeStr2timeUnix);
        Assert.isInstanceOf(Long.class, timeStr2timeUnix);
    }

}

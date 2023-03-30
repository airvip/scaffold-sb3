package wang.diff.user.server.util;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MiscUtils {

    /**
     * 生成订单
     * @param prefix
     * @return
     */
    public static String genOrderNo(String prefix) {
        String yyyyMMddHHmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int i = ThreadLocalRandom.current().nextInt(1000);
        String s = String.valueOf(i);
        long l = System.currentTimeMillis();
        String s1 = String.valueOf(l);
        String sub = s1.substring(0, s1.length() - 4);
        return prefix + yyyyMMddHHmmss + s + sub;
    }

    /**
     * 转时间 unix
     * 
     * @param timeStr 12:00 | 12:00:00
     * @param schDate 2022-01-01
     * @return
     */
    public static Long timeStr2timeUnix(String timeStr, String schDate) {
        List<String> split = StrUtil.split(timeStr, ':');
        int size = split.size();
        String datetimeString = null;
        if (size == 3) {
            datetimeString = String.format("%s %s", schDate, timeStr);
        } else if (size == 2) {
            datetimeString = String.format("%s %s:00", schDate, timeStr);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(datetimeString, formatter);
        // return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 主要用于人民币元转分
     * 
     * @return
     */
    public static int bigDecimal2Int(BigDecimal bigDecimal) {
        // 四舍五入
        // return bigDecimal.multiply(new
        // BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
        // 直接省略厘
        return bigDecimal.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 价格格式化
     * @param fee
     * @return
     */
    public static String feeFormat(Integer fee) {
        return new BigDecimal(fee).divide(new BigDecimal(100), 2, RoundingMode.UP).toString();
    }

    /**
     * 价格格式化
     * @param fee
     * @return
     */
    public static String feeFormat(Long fee) {
        return new BigDecimal(fee).divide(new BigDecimal(100), 2, RoundingMode.UP).toString();
    }

}

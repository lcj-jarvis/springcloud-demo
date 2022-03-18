import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author 简单de快乐
 * @date 2021-10-19 23:00
 */
public class ZonedDateTimeDemo {

    @Test
    public void testDate() {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zny);
    }
}

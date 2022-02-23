package app.mly.mdb.dodo.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 消息处理工具类
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:55 PM
 */
@Slf4j
@UtilityClass
public class MessageUtil {

    /**
     * 类型转换
     *
     * @param byteBuffer
     * @return
     */
    public String byteBuffToString(ByteBuffer byteBuffer) {
        try {
            return Charset.forName("UTF-8")
                    .newDecoder()
                    .decode(byteBuffer.asReadOnlyBuffer())
                    .toString();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
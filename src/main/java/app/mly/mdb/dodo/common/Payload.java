package app.mly.mdb.dodo.common;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * Dodo Socket Message Payload
 *
 * @author xggz <yyimba@qq.com>
 * @since 2022/2/23 15:11
 */
@Data
public class Payload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据类型
     */
    private Integer type;

    /**
     * 数据内容
     */
    private Body data;

    @Data
    public static class Body implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 事件ID
         */
        private String eventId;

        /**
         * 事件类型
         */
        private Integer eventType;

        /**
         * 事件内容
         */
        private JSONObject eventBody;

        /**
         * 发送时间戳
         */
        private Long timestamp;
    }
}
package app.mly.mdb.dodo.event;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息事件
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:47 PM
 */
@Data
public class MessageEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 来源群号
     */
    private String islandId;

    /**
     * 来源频道号
     */
    private String channelId;

    /**
     * 来源DoDo号
     */
    private String dodoId;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息类型
     *
     * @see app.mly.mdb.dodo.enums.MessageType
     */
    private Integer messageType;

    /**
     * 消息内容
     */
    private JSONObject messageBody;

    /**
     * 引用消息ID
     */
    private String referencedMessageId;
}
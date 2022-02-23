package app.mly.mdb.dodo.message;

import cn.hutool.json.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息发送包
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:51 PM
 */
@Data
public class MessageSendBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 频道号
     */
    private String channelId;

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

    @Builder
    public MessageSendBody(String channelId, Integer messageType, JSONObject messageBody, String referencedMessageId) {
        this.channelId = channelId;
        this.messageType = messageType;
        this.messageBody = messageBody;
        this.referencedMessageId = referencedMessageId;
    }

    /**
     * 转换为json字符串
     *
     * @return
     */
    public String toJsonString() {
        return new JSONObject(this).toString();
    }
}
package app.mly.mdb.molly.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 茉莉包
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/12 6:25 PM
 */
@Data
public class MollyMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息发送者标识
     */
    private String from;

    /**
     * 消息发送者昵称
     */
    private String fromName;

    /**
     * 消息接收方标识
     */
    private String to;

    /**
     * 消息接收方名字
     */
    private String toName;

    /**
     * 消息内容
     */
    private String content;

    @Builder
    public MollyMsg(String from, String fromName, String to, String toName, String content) {
        this.from = from;
        this.fromName = fromName;
        this.to = to;
        this.toName = toName;
        this.content = content;
    }
}
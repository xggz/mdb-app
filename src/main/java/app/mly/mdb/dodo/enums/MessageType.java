package app.mly.mdb.dodo.enums;

/**
 * 消息类型
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:40 PM
 */
public enum MessageType {

    /**
     * 文本消息
     */
    TEXT(1),

    /**
     * 图片消息
     */
    IMAGE(2),

    /**
     * 视频消息
     */
    VIDEO(3);

    private Integer value;

    MessageType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
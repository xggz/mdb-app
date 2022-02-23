package app.mly.mdb.dodo.message;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 文本消息
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:53 PM
 */
@Data
public class TextMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 内容
     */
    private String content;

    public TextMessage(String content) {
        this.content = content;
    }

    /**
     * 转换为json字符串
     *
     * @return
     */
    public JSONObject toJsonObject() {
        return new JSONObject(this);
    }
}
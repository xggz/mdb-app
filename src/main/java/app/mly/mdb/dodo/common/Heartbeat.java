package app.mly.mdb.dodo.common;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 心跳包
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:35 PM
 */
@Data
public class Heartbeat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer type;

    public Heartbeat(Integer type) {
        this.type = type;
    }

    /**
     * 转为json字符串
     *
     * @return
     */
    public String toJsonString() {
        return new JSONObject(this).toString();
    }

    /**
     * 构建常规心跳json数据
     *
     * @return
     */
    public static String buildNormal() {
        return new Heartbeat(1).toJsonString();
    }
}
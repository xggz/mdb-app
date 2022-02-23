package app.mly.mdb.dodo.request;

import app.mly.mdb.dodo.message.MessageSendBody;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dodo开放接口
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:54 PM
 */
@Component
public class DodoOpenApi {

    @Autowired
    private DodoRestTemplate dodoRestTemplate;

    /**
     * 获取Socket连接地址
     *
     * @return
     */
    public String getSocketConnectionUrl() {
        JSONObject jsonObject = dodoRestTemplate.postForObject("/api/v1/websocket/connection", null);
        return jsonObject.getJSONObject("data").getStr("endpoint");
    }

    /**
     * 发送消息
     *
     * @param messageSendBody
     */
    public void sendMessage(MessageSendBody messageSendBody) {
        dodoRestTemplate.exchange("/api/v1/channel/message/send", messageSendBody.toJsonString());
    }
}
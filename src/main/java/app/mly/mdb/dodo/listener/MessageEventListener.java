package app.mly.mdb.dodo.listener;

import app.mly.mdb.dodo.enums.MessageType;
import app.mly.mdb.dodo.event.MessageEvent;
import app.mly.mdb.dodo.message.MessageSendBody;
import app.mly.mdb.dodo.message.TextMessage;
import app.mly.mdb.dodo.request.DodoOpenApi;
import app.mly.mdb.molly.MollyOpenApi;
import app.mly.mdb.molly.common.MollyMsg;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听消息事件
 *
 * @author xggz <yyimba@qq.com>
 * @since 2022/2/23 17:02
 */
@Component
public class MessageEventListener {

    @Autowired
    private DodoOpenApi dodoOpenApi;

    @Autowired
    private MollyOpenApi mollyOpenApi;

    @Async
    @EventListener(MessageEvent.class)
    public void handlerResult(MessageEvent messageEvent) {
        // todo 暂时只处理和回复文本消息
        if (!MessageType.TEXT.getValue().equals(messageEvent.getMessageType())) {
            return;
        }

        String message = messageEvent.getMessageBody().getStr("content");

        Integer length = message.length();
        message = message.replaceAll("<@!\\d+> ", "");

        // 判断前后字符串长度是否相同（是否At机器人，当然这个判断不是很严谨，先这样）
        if (length == message.length()) {
            return;
        }

        MollyMsg mollyMsg = MollyMsg.builder()
                .from(messageEvent.getDodoId())
                .fromName("")
                .to(messageEvent.getIslandId())
                .toName("")
                .content(message)
                .build();

        JSONObject mollyReply = mollyOpenApi.mollyReply(mollyMsg);
        if ("00000".equals(mollyReply.getStr("code"))) {
            JSONArray dataArray = mollyReply.getJSONArray("data");
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject data = (JSONObject) dataArray.get(i);
                Integer typed = data.getInt("typed");
                if (typed == 1) {
                    MessageSendBody messageSendBody = MessageSendBody.builder()
                            .channelId(messageEvent.getChannelId())
                            .messageType(MessageType.TEXT.getValue())
                            .referencedMessageId(messageEvent.getReferencedMessageId())
                            .messageBody(new TextMessage(data.getStr("content")).toJsonObject())
                            .build();

                    dodoOpenApi.sendMessage(messageSendBody);
                }
            }
        }
    }
}
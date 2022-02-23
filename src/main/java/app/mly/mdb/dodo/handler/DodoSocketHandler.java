package app.mly.mdb.dodo.handler;

import app.mly.mdb.dodo.common.Heartbeat;
import app.mly.mdb.dodo.common.Payload;
import app.mly.mdb.dodo.event.MessageEvent;
import app.mly.mdb.dodo.util.MessageUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.nio.charset.StandardCharsets;

/**
 * Dodo WebSocket处理程序
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:36 PM
 */
@Slf4j
@Component
public class DodoSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private WebSocketSession session;

    private static final long HEARTBEAT_INTERVAL = 15000L;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("连接成功");
        this.session = session;
        this.handlerHeartbeatInterval();
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        this.session = session;
        String messageString = MessageUtil.byteBuffToString(message.getPayload());
        log.info("收到Socket消息：" + messageString);
        Payload payload = new JSONObject(messageString).toBean(Payload.class);
        if (payload.getType().equals(0)) {
            if (payload.getData().getEventType().equals(2001)) {
                eventPublisher.publishEvent(payload.getData().getEventBody().toBean(MessageEvent.class));
            }
        }
    }

    /**
     * 处理周期心跳
     */
    private void handlerHeartbeatInterval() {
        Thread heartbeatThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(HEARTBEAT_INTERVAL);
                    log.info("send heartbeat:" + Heartbeat.buildNormal());
                    this.session.sendMessage(new BinaryMessage(Heartbeat.buildNormal().getBytes(StandardCharsets.UTF_8)));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        });
        heartbeatThread.setDaemon(true);
        heartbeatThread.setName("HeartbeatThread");
        heartbeatThread.start();
    }
}
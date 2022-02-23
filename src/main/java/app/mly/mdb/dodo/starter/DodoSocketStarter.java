package app.mly.mdb.dodo.starter;

import app.mly.mdb.dodo.handler.DodoSocketHandler;
import app.mly.mdb.dodo.request.DodoOpenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * dodo websocket 启动程序
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/12 4:08 PM
 */
@Slf4j
@Component
public class DodoSocketStarter implements SmartInitializingSingleton {

    @Autowired
    private DodoOpenApi dodoOpenApi;

    @Autowired
    private DodoSocketHandler dodoSocketHandler;

    private WebSocketConnectionManager webSocketConnectionManager;

    @Override
    public void afterSingletonsInstantiated() {
        log.info("init");
        this.initSocketConnection();
    }

    private void initSocketConnection() {
        String connectionUrl = dodoOpenApi.getSocketConnectionUrl();
        log.info("socket url:" + connectionUrl);

        StandardWebSocketClient socketClient = new StandardWebSocketClient();
        webSocketConnectionManager = new WebSocketConnectionManager(socketClient, dodoSocketHandler, connectionUrl);
        webSocketConnectionManager.start();
    }
}
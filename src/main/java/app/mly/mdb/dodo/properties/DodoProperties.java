package app.mly.mdb.dodo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * dodo机器人参数配置
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:53 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "dodo")
public class DodoProperties {

    /**
     * 接口地址
     */
    private String baseUrl;

    /**
     * 机器人clientId
     */
    private String clientId;

    /**
     * 机器人token
     */
    private String token;
}
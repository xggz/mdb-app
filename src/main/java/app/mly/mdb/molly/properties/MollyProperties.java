package app.mly.mdb.molly.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 茉莉参数配置
 *
 * Api信息：
 * https://mly.app
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:57 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "molly")
public class MollyProperties {

    /**
     * 接口地址
     */
    private String baseUrl;

    /**
     * api-key
     */
    private String apiKey;

    /**
     * api-secret
     */
    private String apiSecret;
}
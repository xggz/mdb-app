package app.mly.mdb.molly;

import app.mly.mdb.molly.common.MollyMsg;
import app.mly.mdb.molly.properties.MollyProperties;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 茉莉云开放接口
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:58 PM
 */
@Component
public class MollyOpenApi {

    @Autowired
    private MollyProperties mollyProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用茉莉回复
     *
     * @param mollyMsg
     * @return
     */
    public JSONObject mollyReply(MollyMsg mollyMsg) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Api-Key", mollyProperties.getApiKey());
        headers.add("Api-Secret", mollyProperties.getApiSecret());

        JSONObject body = new JSONObject();
        body.set("content", mollyMsg.getContent());
        body.set("type", 2);
        body.set("from", mollyMsg.getFrom());
        body.set("fromName", mollyMsg.getFromName());
        body.set("to", mollyMsg.getTo());
        body.set("toName", mollyMsg.getToName());

        HttpEntity<String> formEntity = new HttpEntity<String>(body.toString(), headers);
        return restTemplate.postForEntity(mollyProperties.getBaseUrl() + "/reply", formEntity, JSONObject.class).getBody();
    }
}
package app.mly.mdb.dodo.request;

import app.mly.mdb.dodo.properties.DodoProperties;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * dodo 请求模板
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:54 PM
 */
@Slf4j
@Component
public class DodoRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DodoProperties dodoProperties;

    public String exchange(String uri, HttpMethod httpMethod, MultiValueMap<String, Object> params) {
        log.info("request uri:" + uri);
        ResponseEntity<String> responseEntity = restTemplate.exchange(dodoProperties.getBaseUrl() + uri,
                httpMethod,
                httpEntity(params),
                String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }

        return null;
    }

    public String exchange(String uri, String bodyJson) {
        log.info("request uri:" + uri);
        ResponseEntity<String> responseEntity = restTemplate.exchange(dodoProperties.getBaseUrl() + uri,
                HttpMethod.POST,
                jsonHttpEntity(bodyJson),
                String.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }

        return null;
    }

    public JSONObject postForObject(String uri, MultiValueMap<String, Object> params) {
        String result = exchange(uri, HttpMethod.POST, params);
        if (StrUtil.isBlank(result)) {
            return null;
        }
        return new JSONObject(result);
    }

    public JSONArray postForArray(String uri, MultiValueMap<String, Object> params) {
        String result = exchange(uri, HttpMethod.POST, params);
        if (StrUtil.isBlank(result)) {
            return null;
        }
        return new JSONArray(result);
    }

    public JSONObject getForObject(String uri, MultiValueMap<String, Object> params) {
        String result = exchange(uri, HttpMethod.GET, params);
        if (StrUtil.isBlank(result)) {
            return null;
        }
        return new JSONObject(result);
    }

    public <T> T getForObject(String uri, MultiValueMap<String, Object> params, Class<T> clazz) {
        String result = exchange(uri, HttpMethod.GET, params);
        if (StrUtil.isBlank(result)) {
            return null;
        }
        return new JSONObject(result).toBean(clazz);
    }

    public JSONArray getForArray(String uri, MultiValueMap<String, Object> params) {
        String result = exchange(uri, HttpMethod.GET, params);
        if (StrUtil.isBlank(result)) {
            return null;
        }
        return new JSONArray(result);
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bot " + dodoProperties.getClientId() + "." + dodoProperties.getToken());
        return headers;
    }

    private HttpEntity httpEntity(MultiValueMap<String,Object> params) {
        if (params == null) {
            params = new LinkedMultiValueMap<>();
        }
        return new HttpEntity(params, httpHeaders());
    }

    private HttpEntity jsonHttpEntity(String bodyJson) {
        return new HttpEntity(bodyJson, httpHeaders());
    }
}
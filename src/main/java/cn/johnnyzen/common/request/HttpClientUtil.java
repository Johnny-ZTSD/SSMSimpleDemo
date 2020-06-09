package cn.johnnyzen.common.request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: 千千寰宇
 * @date: 2020/5/21  15:33:13
 * @description: 
 *  OriginAuthor: CSDN博主「qzqanlhy1314」
    CopyRight: CC 4.0 BY-SA
 *  Link：https://blog.csdn.net/qzqanzc/java/article/details/84344655
 */

public class HttpClientUtil {
    public static String RESPONESE_STATUS_CODE="RESPONESE_STATUS_CODE";//HTTP响应的状态码
    public static String RESPONESE_CONTENT="RESPONESE_CONTENT";// HTTP响应的内容

    private final static PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager();  //连接池管理器
    private final static HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {  //retry handler
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount >= 5) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();

            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        }
    };

    static {   //类加载的时候 设置最大连接数 和 每个路由的最大连接数
        poolConnManager.setMaxTotal(100);
        poolConnManager.setDefaultMaxPerRoute(100);
    }

    /**
     * 获取 HttpClient实例 By 连接线程池
     *      可避免如下方法实例化时可能会导致的"Address already in use"的异常
     *           HttpClient client = new HttpClients.createDefault();
     * @date 2020/05/21 15:40
     * @return httpClient
     */
    private static CloseableHttpClient getCloseableHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolConnManager)
                .setRetryHandler(httpRequestRetryHandler)
                .build();
        return httpClient;
    }

    /**
     * buildResultMap
     * @description eg: buildResultMap(response, response.getEntity());
     * @param response
     * @param entity
     * @return
     * @throws IOException
     */
    private static Map<String, Object> buildResultMap(CloseableHttpResponse response, HttpEntity entity) throws
            IOException {
        Map<String, Object> result;
        result = new HashMap<>(2);
        result.put(RESPONESE_STATUS_CODE, response.getStatusLine().getStatusCode());  //status code
        if (entity != null) {
            result.put(RESPONESE_CONTENT, EntityUtils.toString(entity, "UTF-8")); //message content
        }
        return result;
    }

    /**
     * send json by get method
     *
     * @param url [REQUIRED] HTTP的请求地址
     * @param message [OPTIONAL] HTTP请求的文本内容
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getJson(String url, String message) {
        Map<String, Object> result = null;
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            httpGet.setHeader("Accept", "application/json;charset=UTF-8");
            httpGet.setHeader("Content-Type", "application/json");

            if(message!=null){
                StringEntity stringEntity = new StringEntity(message);
                stringEntity.setContentType("application/json;charset=UTF-8");
            }

            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = buildResultMap(response, entity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * send json by post method
     *
     * @param url [REQUIRED] HTTP的请求地址
     * @param message [OPTIONAL] HTTP请求的文本内容
     * @return
     * @throws Exception
     */
    public static Map<String, Object> postJson(String url, String message) {
        Map<String, Object> result = null;
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setHeader("Accept", "application/json;charset=UTF-8");
            httpPost.setHeader("Content-Type", "application/json");

            StringEntity stringEntity = new StringEntity(message);
            stringEntity.setContentType("application/json;charset=UTF-8");

            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = buildResultMap(response, entity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}

package com.ranag.common.restClient;

import com.ranag.common.JsonConverter;
import com.ranag.exception.InternalException;
import com.ranag.rest.bean.request.OrgRequestData;
import com.ranag.rest.bean.response.OrgResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.net.ssl.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.security.cert.X509Certificate;
import java.util.Map;

public class OrgRestClient {
    private String host;
    private ClientConfig config;
    private WebTarget webTarget;
    private Client client = null;
    public static final int DEFAULT_TIMEOUT = 60000;
    Map<String,Object> commonReqHeaders;
    private String authBearerToken = null;

    public OrgRestClient(String host) throws Exception {
        initRestClient(host);
    }

    public OrgRestClient(String host,Map<String,Object> commonReqHeaders) throws Exception{
        initRestClient(host);
        this.commonReqHeaders = commonReqHeaders;
    }

    public OrgRestClient(String host,Map<String,Object> commonReqHeaders,String authBearerToken) throws Exception{
        initRestClient(host);
        this.commonReqHeaders = commonReqHeaders;
        this.authBearerToken = authBearerToken;
    }

    private void initRestClient(String host) throws Exception{
        this.host = host;
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        config = new ClientConfig();
        client = ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .hostnameVerifier(allHostsValid)
                .build();
        webTarget = client.target(host);
    }

    public<T extends Object> T post(String api, Object request, Class<? extends T> responseType) throws InternalException {
        log.debug("request:{}",request);
        T response = null;
        try {
            WebTarget resourceWebTarget = webTarget.path(api);
            String reqJson = JsonConverter.getJson(request);
            log.debug("reqJson:{}",reqJson);

            if(responseType!=null) {
                response = resourceWebTarget
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(reqJson, MediaType.APPLICATION_JSON), responseType);
            }
        } catch (Exception e) {
            log.error(e);
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    public<T extends OrgResponseData> T post(String api, OrgRequestData request,
                                                Class<? extends T> responseType, int timeout,
                                                Map<String, Object> qParams) throws InternalException {
        log.debug("request:{}",request);
        T response = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            config = new ClientConfig();
            client = ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .hostnameVerifier(allHostsValid)
                    .build();
            log.debug("client:{}",client);
            client.property(ClientProperties.CONNECT_TIMEOUT, DEFAULT_TIMEOUT);
            client.property(ClientProperties.READ_TIMEOUT, timeout);

            webTarget = client.target(host);
            log.debug("host:{}",host);
            log.debug("***************> webTarget:{}",webTarget);

            WebTarget resourceWebTarget = webTarget.path(api);
            String reqJson = JsonConverter.getJson(request);
            log.debug("reqJson:{}",reqJson);

            if(qParams!=null) {
                log.debug("Adding params:"+qParams);
                for(String paramKey : qParams.keySet()) {
                    resourceWebTarget = resourceWebTarget.queryParam(paramKey,qParams.get(paramKey));
                    log.debug("paramKey:{}",paramKey);
                    log.debug("********> resourceWebTarget:{}",resourceWebTarget);
                }
            }

            Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
            log.debug("invocationBuilder:{}",invocationBuilder);

            if(this.commonReqHeaders!=null && !commonReqHeaders.isEmpty()) {
                for(String key : commonReqHeaders.keySet()) {
                    invocationBuilder.header(key,commonReqHeaders.get(key));
                }
            }

            if(responseType!=null) {
                response = invocationBuilder.post(Entity.entity(reqJson, MediaType.APPLICATION_JSON), responseType);
                log.debug("response:{}",response);
            } else {
                log.debug("Calling ... api:{} on host:{}", api, host);
                invocationBuilder.post(Entity.entity(reqJson, MediaType.APPLICATION_JSON));
            }
        } catch (Exception e) {
            log.debug("************** STILL FAILING *******************");
            log.error("Error for api:{};host:{}",api,host,e);
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    public<T extends Object> T get(String api,Class<? extends T> responseType) throws InternalException {
        return get(api, responseType, DEFAULT_TIMEOUT, null);
    }

    public<T extends Object> T get(String api,Class<? extends T> responseType,Map<String,Object> qParams) throws InternalException {
        return get(api, responseType, DEFAULT_TIMEOUT, qParams);
    }

    public<T extends Object> T get(String api,
                                   Class<? extends T> responseType,
                                   int timeout,Map<String,
            Object> qParams) throws InternalException {
        T response = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            config = new ClientConfig();
            client = ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .hostnameVerifier(allHostsValid)
                    .build();
            client.property(ClientProperties.CONNECT_TIMEOUT, DEFAULT_TIMEOUT);
            client.property(ClientProperties.READ_TIMEOUT, timeout);
            webTarget = client.target(host);

            WebTarget resourceWebTarget = webTarget.path(api);
            log.debug("resourceWebTarget1:{}",resourceWebTarget);
            if(responseType!=null) {
                log.debug("Calling ... api:{} on host:{}",api,host);
                if(qParams!=null && !qParams.isEmpty()) {
                    log.debug("Adding params:"+qParams);
                    for(String paramKey : qParams.keySet()) {
                        resourceWebTarget = resourceWebTarget.queryParam(paramKey,qParams.get(paramKey));
                    }
                    log.debug("resourceWebTarget:{}",resourceWebTarget);
                    Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
                    if(commonReqHeaders!=null && !commonReqHeaders.isEmpty()) {
                        for(String key : commonReqHeaders.keySet()) {
                            invocationBuilder.header(key,commonReqHeaders.get(key));
                        }
                    }
                    log.debug("commonReqHeaders:{}",commonReqHeaders);
                    if(authBearerToken!=null && !authBearerToken.isEmpty()) {
                        invocationBuilder.header(HttpHeaders.AUTHORIZATION, "Bearer " + authBearerToken);
                    }
                    log.debug("authBearerToken:{}",authBearerToken);
                    log.debug("responseType:{}",responseType);
                    log.debug("invocationBuilder.get(responseType):{}",invocationBuilder.get(responseType));
                    response = invocationBuilder.get(responseType);
                    log.debug("response Auth+++++++",response);

                } else {
                    Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
                    if(commonReqHeaders!=null && !commonReqHeaders.isEmpty()) {
                        for(String key : commonReqHeaders.keySet()) {
                            invocationBuilder.header(key,commonReqHeaders.get(key));
                        }
                    }
                    response = invocationBuilder.get(responseType);
                    log.debug("response Common+++++++");
                }
            }
        } catch (Exception e) {
            log.debug("response:{}",response);
            log.error("Get API Failed, api;{}",api,e);
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    public<T extends Object> T post(String api, Object request, Class<? extends T> responseType, int timeout,Map<String, Object> qParams) throws InternalException {
        log.debug("request:{}",request);
        T response = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            config = new ClientConfig();
            client = ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .hostnameVerifier(allHostsValid)
                    .build();
            client.property(ClientProperties.CONNECT_TIMEOUT, DEFAULT_TIMEOUT);
            client.property(ClientProperties.READ_TIMEOUT, timeout);
            webTarget = client.target(host);

            WebTarget resourceWebTarget = webTarget.path(api);
            String reqJson = JsonConverter.getJson(request);
            log.info("reqJson:{}",reqJson);

            if(qParams!=null) {
                log.debug("Adding params:"+qParams);
                for(String paramKey : qParams.keySet()) {
                    resourceWebTarget = resourceWebTarget.queryParam(paramKey,qParams.get(paramKey));
                }
            }

            Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
            if(commonReqHeaders!=null && !commonReqHeaders.isEmpty()) {
                for(String key : commonReqHeaders.keySet()) {
                    invocationBuilder.header(key,commonReqHeaders.get(key));
                }
            }

            if(authBearerToken!=null && !authBearerToken.isEmpty()) {
                invocationBuilder.header(HttpHeaders.AUTHORIZATION, "Bearer " + authBearerToken);
            }

            if(responseType!=null) {
                log.debug("Calling ... api:{} on host:{}",api,host);
                response = invocationBuilder.post(Entity.entity(reqJson, MediaType.APPLICATION_JSON), responseType);
            }
        } catch (Exception e) {
            log.error("Error while POST.api:{}",api,e);
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    public static final Logger log = LogManager.getLogger(OrgRestClient.class);
    public<T extends OrgResponseData> T post(String api, OrgRequestData request, Class<? extends T> responseType, int timeOut) throws InternalException {
        return post(api,request,responseType,timeOut,null);
    }

    public<T extends OrgResponseData> T post(String api, OrgRequestData request, Class<? extends T> responseType) throws InternalException {
        return post(api,request,responseType,DEFAULT_TIMEOUT,null);
    }

    public<T extends OrgResponseData> T post(String api, OrgRequestData request, Class<? extends T> responseType, Map<String, Object> qParams) throws InternalException {
        return post(api,request,responseType,DEFAULT_TIMEOUT,qParams);
    }

    public<T extends OrgResponseData> T postQueryParam(String api, OrgRequestData request, Class<? extends T> responseType, Map<String, Object> qParams) throws InternalException {
        return post(api,request,responseType,DEFAULT_TIMEOUT,qParams);
    }

    public<T extends Object> T postQueryParam(String api, Object request, Class<? extends T> responseType, Map<String, Object> qParams) throws InternalException {
        return post(api,request,responseType,DEFAULT_TIMEOUT,qParams);
    }

    public<T extends Object> T post(String api, Object request, Class<? extends T> responseType, Map<String,Object> reqHeaders) throws InternalException {
        log.debug("request:{}",request);
        T response = null;
        try {
            WebTarget resourceWebTarget = webTarget.path(api);
            String reqJson = JsonConverter.getJson(request);
            log.debug("reqJson:{}",reqJson);
            Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);

            if(reqHeaders!=null && !reqHeaders.isEmpty()) {
                for(String key : reqHeaders.keySet()) {
                    invocationBuilder.header(key,reqHeaders.get(key));
                }
            }
            if(responseType!=null) {
                response = invocationBuilder.post(Entity.entity(reqJson, MediaType.APPLICATION_JSON), responseType);
            }
        } catch (Exception e) {
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    public<T extends OrgResponseData> T put(String api, OrgRequestData request,
                                               Class<? extends T> responseType) throws InternalException {
        log.debug("request:{}",request);
        T response = null;
        try {

            WebTarget resourceWebTarget = webTarget.path(api);
            String reqJson = JsonConverter.getJson(request);
            log.debug("reqJson:{}",reqJson);

            Invocation.Builder invocationBuilder =  resourceWebTarget.request(MediaType.APPLICATION_JSON);

            if(this.commonReqHeaders!=null) {
                if(!commonReqHeaders.isEmpty()) {
                    for(String key : commonReqHeaders.keySet()) {
                        log.debug("Adding key:{} and value:{}",key,commonReqHeaders.get(key));
                        invocationBuilder.header(key,commonReqHeaders.get(key));
                    }
                }
            }

            log.info("responseType:{}",responseType);
            if(responseType!=null) {
//                if(this.commonReqHeaders!=null) {
//                    if(!commonReqHeaders.isEmpty()) {
//                        for(String key : commonReqHeaders.keySet()) {
//                            invocationBuilder.header(key,commonReqHeaders.get(key));
//                        }
//                    }
//                }
                log.info("reqJson:{}",reqJson);
                if(reqJson==null || reqJson.isEmpty()) {
                    log.info("reqJson is null");
                    Entity<?> empty = Entity.json("");
                    response = invocationBuilder.put(Entity.entity(empty, MediaType.APPLICATION_JSON), responseType);
                } else {
                    log.info("reqJson is not null");
                    response = invocationBuilder.put(Entity.entity(reqJson, MediaType.APPLICATION_JSON), responseType);
                }
            }
        } catch (Exception e) {
            log.info("api:{} failed. host:{}",api,host,e);
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    /*
     if(qParams!=null) {
                log.debug("Adding params:"+qParams);
                for(String paramKey : qParams.keySet()) {
                    resourceWebTarget = resourceWebTarget.queryParam(paramKey,qParams.get(paramKey));
                }
            }
     */
    public<T extends Object> T delete(String api, OrgRequestData request, Class<? extends T> responseType) throws InternalException {
        return delete(api,request,responseType,null);
    }

    public<T extends Object> T delete(String api, OrgRequestData request, Class<? extends T> responseType,Map<String, Object> qParams) throws InternalException {
        log.debug("request:{}",request);
        T response = null;
        try {

            WebTarget resourceWebTarget = webTarget.path(api);

            if(qParams!=null) {
                log.debug("Adding params:"+qParams);
                for(String paramKey : qParams.keySet()) {
                    resourceWebTarget = resourceWebTarget.queryParam(paramKey,qParams.get(paramKey));
                }
            }

            Invocation.Builder invocationBuilder =  resourceWebTarget.request(MediaType.APPLICATION_JSON);

            if(this.commonReqHeaders!=null) {
                if(!commonReqHeaders.isEmpty()) {
                    for(String key : commonReqHeaders.keySet()) {
                        invocationBuilder.header(key,commonReqHeaders.get(key));
                    }
                }
            }

            if(authBearerToken!=null && !authBearerToken.isEmpty()) {
                invocationBuilder.header(HttpHeaders.AUTHORIZATION, "Bearer " + authBearerToken);
            }

            if(responseType==null) {
                invocationBuilder.delete();
            } else {
                response = invocationBuilder.delete(responseType);
            }
        } catch (Exception e) {
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }

    public<T extends Object> T postForm(String api,
                                        MultivaluedMap<String,String> reqParams,
                                        Class<? extends T> responseType, int timeout) throws InternalException {
        log.debug("request:{}",reqParams);
        if(reqParams==null) {
            throw new InternalException("Invalid form parameters");
        }
        T response = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            config = new ClientConfig();
            client = ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .hostnameVerifier(allHostsValid)
                    .build();
            client.property(ClientProperties.CONNECT_TIMEOUT, DEFAULT_TIMEOUT);
            client.property(ClientProperties.READ_TIMEOUT, timeout);
            webTarget = client.target(host);

            Form form = new Form();
            for(String formKey : reqParams.keySet()) {
                String formVal = reqParams.get(formKey).get(0);
                form.param(formKey,formVal);
            }

            WebTarget resourceWebTarget = webTarget.path(api);
            response = resourceWebTarget
                    .request(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_FORM_URLENCODED)
                    .post(Entity.form(form), responseType);
        } catch (Exception e) {
            throw new InternalException(e);
        }
        log.debug("response:{}",response);
        return response;
    }
}

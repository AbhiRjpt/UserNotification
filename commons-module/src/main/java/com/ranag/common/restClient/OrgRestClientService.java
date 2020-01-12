package com.ranag.common.restClient;

import com.ranag.common.serverEndPoint.EndPointService;
import security.CookieParams;

import java.util.HashMap;
import java.util.Map;

public class OrgRestClientService {

    public OrgRestClient getRestClient() throws Exception{

        Map<String, Object> reqHeaders = new HashMap<>();
        reqHeaders.put(CookieParams.API_KEY, "Test");
        String endPoint = new EndPointService().getNotificationEndPoint();
        System.out.println("EndPoint: "+endPoint);

        return new OrgRestClient(endPoint,reqHeaders);
    }

}

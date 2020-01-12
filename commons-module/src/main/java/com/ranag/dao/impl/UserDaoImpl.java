package com.ranag.dao.impl;

import com.ranag.dao.template.impl.QueryParameter;
import com.ranag.dao.template.impl.SingleRowQueryTemplateImpl;

public class UserDaoImpl {

    public int getUserKey(String userId) {
        final int[] userKey = {0};
        String sql = "Select userkey from UserData where userId = ?";
        QueryParameter queryParameter = new QueryParameter().setString(userId);
        new SingleRowQueryTemplateImpl(sql, queryParameter) {
            @Override
            public void processResult() throws Exception {
                userKey[0] = this.resultSet.getInt("userkey");
            }
        };
        return userKey[0];
    }
}

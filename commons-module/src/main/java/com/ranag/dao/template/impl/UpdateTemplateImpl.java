package com.ranag.dao.template.impl;

import com.ranag.dao.template.UpdateTemplate;

import java.util.Optional;

public class UpdateTemplateImpl extends DaoTemplateImpl implements UpdateTemplate {

    private int noOfRowsUpdated;

    public UpdateTemplateImpl(String sql) {
        this(sql,null);
    }

    public UpdateTemplateImpl(String sql, QueryParameter queryParameter) {
        super(sql, queryParameter);
    }

    protected void preProcess(){
        this.noOfRowsUpdated = 0;
    }

    @Override
    protected void execute() throws Exception {
        System.out.println(this.preparedStatement);
        this.noOfRowsUpdated =   this.preparedStatement.executeUpdate();
        processResult();
    }

    @Override
    public void processResult() throws Exception { }

    public int getNoOfRowsUpdated() {
        return noOfRowsUpdated;
    }
}

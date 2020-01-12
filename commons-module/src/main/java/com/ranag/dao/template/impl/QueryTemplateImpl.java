package com.ranag.dao.template.impl;

import com.ranag.dao.template.QueryTemplate;

import java.util.Optional;

public abstract class QueryTemplateImpl extends DaoTemplateImpl implements QueryTemplate {
    protected int numberOfRecords;

    public QueryTemplateImpl(String sql, QueryParameter queryParameter) {
        super(sql, queryParameter);
    }

    public QueryTemplateImpl(String sql) {
        this(sql,null);
    }

    protected void preProcess(){this.numberOfRecords = 0;}

    protected void execute() throws Exception{
        System.out.println("preparedStatement: "+preparedStatement);
        this.resultSet = this.preparedStatement.executeQuery();
        while(this.resultSet.next()){
            this.numberOfRecords++;
            processResult();

        }
    }

    public int getNumberOfRecords(){
        return this.numberOfRecords;
    }
}

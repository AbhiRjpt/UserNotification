package com.ranag.dao.template.impl;

import java.util.Optional;

public abstract class SingleRowQueryTemplateImpl extends QueryTemplateImpl {

    public SingleRowQueryTemplateImpl(String sql) {
        super(sql);
    }

    public SingleRowQueryTemplateImpl(String sql, QueryParameter queryParameter) {
        super(sql, queryParameter);
    }

    protected void execute() throws Exception{
        System.out.println("preparedStatement: "+preparedStatement);
        this.resultSet = this.preparedStatement.executeQuery();
        if(this.resultSet.next()){
            this.numberOfRecords++;
            processResult();

        }
    }
}

package com.ranag.dao.template.impl;

import com.ranag.dao.template.InsertTemplate;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public abstract class InsertTemplateImpl extends DaoTemplateImpl implements InsertTemplate {

    public InsertTemplateImpl(String sql, QueryParameter queryParameter) {
        super(sql, queryParameter);
    }

    public InsertTemplateImpl(String sql) {
        this(sql,null);
    }

    protected void prePreparedQuery() throws SQLException {
        this.preparedStatement = this.connection.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);
    }

    protected void execute() throws Exception {
        System.out.println("PreparedStatement: "+this.preparedStatement);
        this.preparedStatement.executeUpdate();
        this.resultSet = this.preparedStatement.getGeneratedKeys();
        if(this.resultSet.next()){
            processResult();
        }
    }
}

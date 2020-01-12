package com.ranag.dao.template.impl;

import com.ranag.DBConnection.DbConnectionPoolManager;
import org.apache.commons.dbcp2.Utils;

import java.sql.*;
import java.util.Map;
import java.util.Optional;

public abstract class DaoTemplateImpl {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    protected String sql;
    protected Optional<QueryParameter> queryParameter = null;
    private boolean autoCommit;

    public DaoTemplateImpl(String sql, QueryParameter queryParameter) {
        this.sql = sql;
        this.queryParameter = Optional.ofNullable(queryParameter);
        this.autoCommit = true;
        preProcess();
        process();
    }

    protected void preProcess() { }

    protected void process(){
        try {
            connect();
            setAutoCommit(this.autoCommit);
            prePreparedQuery();
            preparedQuery();
            postPrepareQuery();
            execute();
            commitTransaction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            Utils.closeQuietly(this.resultSet);
            Utils.closeQuietly(this.preparedStatement);
            Utils.closeQuietly(this.connection);
        }
    }

    protected void commitTransaction() {
        try {
            if(!this.autoCommit){
                connection.commit();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected abstract void execute() throws Exception;

    protected void postPrepareQuery() throws Exception { }

    protected void preparedQuery() throws SQLException {
        if(this.queryParameter.isPresent()){
            prepareQuery(this.queryParameter.get());
        }
    }

    protected void prePreparedQuery() throws SQLException {
        this.preparedStatement = this.connection.prepareStatement(this.sql);
    }

    protected void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
        try {
           connection.setAutoCommit(this.autoCommit);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected void connect() throws SQLException {
        connection = DbConnectionPoolManager.getInstance().getDBConnection();
    }


    protected void prepareQuery(QueryParameter queryParameter) throws SQLException {
        if (queryParameter != null) {
            for (Map.Entry<Integer, QueryParameter.ParamKV> paramKVEntry : queryParameter.getParamKVMap().entrySet()) {
                if (paramKVEntry.getValue().isInteger()) {
                    this.preparedStatement.setInt(paramKVEntry.getKey(), (Integer) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isString()) {
                    this.preparedStatement.setString(paramKVEntry.getKey(), (String) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isLong()) {
                    this.preparedStatement.setLong(paramKVEntry.getKey(), (Long) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isFloat()) {
                    this.preparedStatement.setFloat(paramKVEntry.getKey(), (Float) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isDouble()) {
                    this.preparedStatement.setDouble(paramKVEntry.getKey(), (Double) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isDate()) {
                    this.preparedStatement.setDate(paramKVEntry.getKey(), (Date) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isTimestamp()) {
                    this.preparedStatement.setTimestamp(paramKVEntry.getKey(), (Timestamp) paramKVEntry.getValue().getVal());
                }
                if (paramKVEntry.getValue().isBoolean()) {
                    this.preparedStatement.setBoolean(paramKVEntry.getKey(), (Boolean) paramKVEntry.getValue().getVal());
                }
            }
        }
    }

    protected ResultSet getResultSet(){
        return this.resultSet;
    }

}

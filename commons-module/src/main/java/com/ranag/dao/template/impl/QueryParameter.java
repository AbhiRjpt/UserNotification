package com.ranag.dao.template.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class QueryParameter {
    private int index;
    private Map<Integer,ParamKV> paramKVMap = null;

    public QueryParameter() {
        this.index = 1;
        this.paramKVMap = new HashMap<>();
    }

    public QueryParameter setInt(int val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.INTEGER,val));
        return this;
    }

    public QueryParameter setString(String val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.STRING,val));
        return this;
    }

    public QueryParameter setFloat(float val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.FLOAT,val));
        return this;
    }

    public QueryParameter setDouble(double val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.DOUBLE,val));
        return this;
    }

    public QueryParameter setLong(long val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.LONG,val));
        return this;
    }

    public QueryParameter setDate(Date val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.DATE,val));
        return this;
    }

    public QueryParameter setTimestamp(Timestamp val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.TIMESTAMP,val));
        return this;
    }

    public QueryParameter setBoolean(boolean val){
        paramKVMap.put(this.index++,new ParamKV(ParamTypes.BOOLEAN,val));
        return this;
    }

    public Map<Integer, ParamKV> getParamKVMap() {
        return paramKVMap;
    }

    class ParamKV{
        ParamTypes paramTypeKey;
        Object val;

        public ParamKV( ParamTypes paramTypeKey,Object val) {
            this.paramTypeKey = paramTypeKey;
            this.val = val;
        }

        public ParamTypes getParamTypeKey() {
            return paramTypeKey;
        }

        public Object getVal() {
            return val;
        }

        public boolean isInteger(){
            return this.paramTypeKey==ParamTypes.INTEGER;
        }
        public boolean isString(){
            return this.paramTypeKey==ParamTypes.STRING;
        }
        public boolean isLong(){
            return this.paramTypeKey==ParamTypes.LONG;
        }
        public boolean isDouble(){
            return this.paramTypeKey==ParamTypes.DOUBLE;
        }
        public boolean isFloat(){
            return this.paramTypeKey==ParamTypes.FLOAT;
        }
        public boolean isDate(){
            return this.paramTypeKey==ParamTypes.DATE;
        }

        public boolean isTimestamp(){
            return this.paramTypeKey==ParamTypes.TIMESTAMP;
        }
        public boolean isBoolean() { return this.paramTypeKey==ParamTypes.BOOLEAN;}


    }

    enum ParamTypes {
        INTEGER,STRING,DOUBLE,FLOAT,DATE,TIMESTAMP,LONG,BOOLEAN
    }

}

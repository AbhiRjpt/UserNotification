package com.ranag.DBConnection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionPool {
    private BasicDataSource connectionPool = null;
    private static DbConnectionPool instance = null;
//    private static final Logger log = LogManager.getLogger(DbConnectionPool.class);

    public DbConnectionPool() {}

    public static DbConnectionPool newInstance(){return new DbConnectionPool();}

    public static DbConnectionPool getInstance(){
        if(instance == null){
            synchronized (DbConnectionPool.class){
                if(instance == null){
                    instance = new DbConnectionPool();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void init(String connectionString, String driver, int poolSize, String userName, String password) throws URISyntaxException {
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(userName);
        connectionPool.setPassword(password);
        connectionPool.setDriverClassName(driver);
        connectionPool.setUrl(connectionString);
        connectionPool.setInitialSize(poolSize);
        connectionPool.setMaxTotal(20);
        connectionPool.setRemoveAbandonedTimeout(60);
        connectionPool.setRemoveAbandonedOnBorrow(true);
        connectionPool.setRemoveAbandonedOnMaintenance(true);
    }

    public void closePool() throws SQLException {
        connectionPool.close();
    }

    public BasicDataSource getConnectionPool() {
        return connectionPool;
    }

    @Override
    public String toString() {
        return "DbConnectionPool{" +
                "url=" +this.getConnectionPool().getUrl()+ "}";
    }
}

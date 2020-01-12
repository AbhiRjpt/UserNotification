package com.ranag.DBConnection;

import com.ranag.rest.constant.SystemConstants;
import org.apache.commons.dbcp2.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DbConnectionPoolManager {
//    private static final Logger log = LogManager.getLogger(DbConnectionPoolManager.class);
    private static DbConnectionPoolManager instance = null;
    private DbConnectionPool dbConnectionPool = null;

    public DbConnectionPoolManager() {
        System.out.println("Initializing connection pool");
        String driver = System.getProperty(SystemConstants.DB_DRIVER,"com.mysql.jdbc.Driver");
        String connString = System.getProperty(SystemConstants.DB_CONN_URL,"jdbc:mysql://localhost:3306/TestDB");
        String userName = System.getProperty(SystemConstants.DB_USER_NAME,"root");
        String password = System.getProperty(SystemConstants.DB_PASSWORD,"roottest");
        int poolSize = Integer.parseInt(System.getProperty(SystemConstants.DB_POOL_SIZE,"2"));
        try {
            dbConnectionPool = DbConnectionPool.newInstance();
            dbConnectionPool.init(connString,driver,poolSize,userName,password);
        } catch (Exception e) {
            String errorMsg = " ********** Failed to create the Database connection with driver:"
                    + driver + " and connection string: " + connString + " **********";
            System.out.println(errorMsg+" "+e);
            throw new RuntimeException(errorMsg);
        }
    }

    public static DbConnectionPoolManager getInstance() {
        if(instance==null) {
            synchronized (DbConnectionPoolManager.class) {
                if(instance == null) {
                    instance = new DbConnectionPoolManager();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getDBConnection() throws SQLException {
        System.out.println(dbConnectionPool.getConnectionPool().getUrl());
        return dbConnectionPool.getConnection();
    }

//    public static void getData(){
//        List<CommonData> commonDataList = new LinkedList<>();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        String sql;
//        try{
//            connection = DbConnectionPoolManager.getInstance().getDBConnection();
//            sql = "Select * from CricketMatch";
//            preparedStatement =connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                CommonData commonData = new CommonData();
//                commonData.setCkMId(resultSet.getInt("ckMId"));
//                commonData.setBname(resultSet.getString("bname"));
//                commonData.setRunsMade(resultSet.getInt("RunsMade"));
//                commonData.setMatchDate(resultSet.getString("MatchDate"));
//                commonDataList.add(commonData);
//            }
//            System.out.println(commonDataList);
//        }catch (Exception e){
//
//        }finally {
//            Utils.closeQuietly(resultSet);
//            Utils.closeQuietly(preparedStatement);
//            Utils.closeQuietly(connection);
//        }
//    }

    public static void main(String[] args) {
        System.out.println("Printing DataList");
//        getData();
    }

}

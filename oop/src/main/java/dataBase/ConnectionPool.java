package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import static dataBase.DataBaseInfo.*;

public class ConnectionPool {

    private BlockingQueue<Connection> queue;
    public ConnectionPool(int numConnections){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        queue = new ArrayBlockingQueue<>(numConnections);
        for(int i = 0; i < numConnections; i++){
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT, USERNAME, PASSWORD);
                con.createStatement().execute("USE " +  DATABASE_NAME +";");
                queue.add(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ConnectionPool(int numConnections, String db_name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        queue = new ArrayBlockingQueue<>(numConnections);
        for(int i = 0; i < numConnections; i++){
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT, USERNAME, PASSWORD);
                con.createStatement().execute("USE " +  db_name +";");
                queue.add(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConnection(){
        try {
            Connection conn = queue.take();
            return conn;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection conn){
        try {
            queue.put(conn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        while(!queue.isEmpty()){
            try {
                queue.poll().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
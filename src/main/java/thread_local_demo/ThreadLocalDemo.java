package thread_local_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLocalDemo {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        private String DB_URL = "127.0.0.1:9001";
        @Override
        protected Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
        public Connection getConnection() {
            return connectionHolder.get();
        }
    };

    public static void main(String[] args) {
        System.out.println(connectionHolder.get());
    }

    private static class PcInfo {
        private String info = "Laptop Lenovo ideapad i520-151IKB";
        public String getInfo() {
            return info;
        }
    }

}

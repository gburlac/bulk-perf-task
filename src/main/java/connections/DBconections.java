package connections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconections {
    private String userName;
    private String password;
    private String dbUrl;

    private Connection conn;
//    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(DBconections.class);

    public DBconections() {
        driverInitializing();
    }

    private void driverInitializing() {
        try {
            readPropValues();
            Class.forName("sqlserver.jdbc.driver.SqlServerDriver");
        } catch (Exception e) {
        }
    }

    public Connection activeConnection() {
        try {
            readPropValues();
            conn = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("DB connection opened...\n");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void dbClose() {
        try {
            conn.close();
            System.out.println("DB connection closed... \n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readPropValues() throws IOException {

        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "src/main/resources/db_conection.properties";

            if (false) {
                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            } else {
                inputStream = new FileInputStream(propFileName);
            }

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            userName = prop.getProperty("DB_USERNAME");
            password = prop.getProperty("DB_PASSWORD");
            dbUrl = prop.getProperty("DB_URL");


        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

}

package runners;

import connections.DBconections;
import connections.QueryFactory;

import java.sql.Connection;

public class RunDeleteQueries {

    public static void main(String[] args) {

        DBconections dBconections = new DBconections();
        Connection conn = dBconections.activeConnection();
        QueryFactory factory = new QueryFactory();

        String sqlUpdate = "DELETE FROM production.stocks WHERE quantity = 0 ";
        factory.updateTable(conn, sqlUpdate);
        dBconections.dbClose();
    }
}

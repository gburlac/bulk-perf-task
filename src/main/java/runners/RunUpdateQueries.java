package runners;

import connections.DBconections;
import connections.QueryFactory;

import java.sql.Connection;

public class RunUpdateQueries {

    public static void main(String[] args) {

        DBconections dBconections = new DBconections();
        Connection conn = dBconections.activeConnection();
        QueryFactory factory = new QueryFactory();

        String sqlupdate = "UPDATE production.products SET list_price = '111.11' WHERE product_id = '1'";
        factory.updateTable(conn, sqlupdate);
        dBconections.dbClose();

    }
}
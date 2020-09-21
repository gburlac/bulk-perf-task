package runners;

import connections.DBconections;
import connections.QueryFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RunReadQueries {

    public static void main(String[] args) {

        DBconections dBconections = new DBconections();
        Connection conn = dBconections.activeConnection();

        int rowNum = 100;
        System.out.println("Customers: " + getSelectFromDB(conn, rowNum));

    }

    public static List<Map<String, String>> getSelectFromDB(Connection conn, int rowNum) {
        QueryFactory factory = new QueryFactory();
        List<Map<String, String>> querryResults;
        String sqlSelect = "SELECT first_name, email FROM BikeStores.sales.customers WHERE customer_id <= "+ (rowNum) ;
        querryResults = factory.queryExecutor(conn, sqlSelect, "first_name", "email");
        return querryResults;

    }

}
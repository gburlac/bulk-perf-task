package connections;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryFactory {

    private static final Logger log = Logger.getLogger(QueryFactory.class);

    public List<Map<String, String>> queryExecutor(Connection conn, String query, String... columns) {
        System.out.println("Executed query [" + query + "]");
        List<Map<String, String>> list = new ArrayList<>();
        try (Statement statement = conn.createStatement(); ResultSet resultOfQuery = statement.executeQuery(query)) {

            while (resultOfQuery.next()) {
                Map<String, String> values = new HashMap();
                for (String column : columns) {
                    String value = resultOfQuery.getString(column);
                    values.put(column, value);
                }
                list.add(values);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public void updateTable(Connection conn, String queryStatement) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(queryStatement);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        System.out.println("Changed table, executing statement [ " + queryStatement + " ] \n");
    }
}

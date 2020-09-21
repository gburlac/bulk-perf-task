package testdata;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import connections.DBconections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class InsertBulkInProducts {

    public void insertBulk(int rows) {
        DBconections dBconections = new DBconections();
        Connection conn = dBconections.activeConnection();
        String destinationTable = "BikeStores.production.products";
        int countBefore, countAfter;

        try (
                Statement stmt = conn.createStatement();
                SQLServerBulkCSVFileRecord fileRecord = new SQLServerBulkCSVFileRecord("csvWithData.csv", true)) {
            SQLServerBulkCopy bulkCopy = new SQLServerBulkCopy(conn);
            bulkCopy.addColumnMapping("product_name", "product_name");
            bulkCopy.addColumnMapping("brand_id", "brand_id");
            bulkCopy.addColumnMapping("category_id", "category_id");
            bulkCopy.addColumnMapping("model_year", "model_year");
            bulkCopy.addColumnMapping("list_price", "list_price");

            // Set the metadata for each column to be copied.
            fileRecord.addColumnMetadata(1, null, java.sql.Types.NVARCHAR, 0, 0);
            fileRecord.addColumnMetadata(2, null, java.sql.Types.INTEGER, 0, 0);
            fileRecord.addColumnMetadata(3, null, java.sql.Types.INTEGER, 0, 0);
            fileRecord.addColumnMetadata(4, null, java.sql.Types.INTEGER, 0, 0);
            fileRecord.addColumnMetadata(5, null, java.sql.Types.DOUBLE, 0, 0);

            // Empty the destination table.
//            System.out.println("Cleaning table... " + destinationTable);
//            stmt.executeUpdate("DELETE FROM " + destinationTable);

            // Perform an initial count on the destination table.
            System.out.println("Count before insert...= " + getRowCount(stmt, destinationTable));
            countBefore = getRowCount(stmt, destinationTable);

            long lStartTime = System.currentTimeMillis();
            long startTime = TimeUnit.MILLISECONDS.toSeconds(lStartTime);

            bulkCopy.setDestinationTableName(destinationTable);
            // Write from the source to the destination.
            bulkCopy.writeToServer(fileRecord);

            long lEndTime = System.currentTimeMillis();
            long endTime = TimeUnit.MILLISECONDS.toSeconds(lEndTime);
//            long totalTimeMills = lEndTime - lStartTime;
            long totalTime = endTime - startTime;
            double tpsCalc = rows / totalTime;

            System.out.println("Count after insert...= " + getRowCount(stmt, destinationTable));
            // Perform a final count on the destination
            // table to see how many rows were added.
            countAfter = getRowCount(stmt, destinationTable);
            System.out.println("Rows were added...= " + (countAfter - countBefore));
            System.out.println("Bulk file have been inserted ");
//            System.out.println("Execution time in milliseconds: " + totalTimeMills);
            System.out.println("Execution time in seconds: " + totalTime);
            System.out.println("Average TPS: " + tpsCalc + "\n");

            dBconections.dbClose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getRowCount(Statement stmt, String tableName) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        return count;
    }


}


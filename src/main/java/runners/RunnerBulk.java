package runners;

import testdata.InsertBulkInProducts;
import testdata.TestDataCSV;

public class RunnerBulk {

    public static void main(String[] args) {


        int rows = 1000;
        TestDataCSV testDataCSV = new TestDataCSV();
        testDataCSV.createCSV(rows);

        InsertBulkInProducts insertBulkInProducts = new InsertBulkInProducts();
        insertBulkInProducts.insertBulk(rows);
    }


}

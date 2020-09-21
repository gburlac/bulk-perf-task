package testdata;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestDataCSV {

    public void createCSV(int rows) {
        try {
            File csvWithData = new File("csvWithData.csv");
            FileWriter fileWriterWithData = new FileWriter(csvWithData);
            BufferedWriter writerData = new BufferedWriter(fileWriterWithData);
            System.out.println("Creating CSV file with test data ...");

            String contentHeader;
            String contentData;
            contentHeader = "product_name,brand_id,category_id,model_year,list_price" + "\n";
            writerData.write(contentHeader);
            for (int i = 0; i < rows; i++) {
                contentData = randomString(6) + "," + getRandomNumber(1, 9) + "," + getRandomNumber(1, 9) + "," + getRandomNumber(2000, 2020) + "," + getRandomNumber(200, 900) + "\n";
                writerData.write(contentData);
            }
            System.out.println("CSV file have been created");
            writerData.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomNumber(int min, int max) {
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        return randomNum;
    }

    private String randomString(int length) {

        return RandomStringUtils.randomAlphabetic(length);
    }
}




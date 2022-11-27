import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductLoader implements ImportData {


    public int getNumberOfRows() {
        BufferedReader reader;
        int linesCount = 0;
        try {
            reader = new BufferedReader(new FileReader("salesproducts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                linesCount++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return linesCount;
    }

    @Override
    public Object[] importDataFromFile() {
        int lineIndex = 0;
        Object[] arr = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("salesproducts.txt"));
            arr = new Object[getNumberOfRows()];
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split("#", 10);
                if (lineSplit[0].equals("1")) {
                    int inventoryNumber = Integer.parseInt(lineSplit[3]);
                    double price = Double.parseDouble(lineSplit[4]);
                    int quantity = Integer.parseInt(lineSplit[5]);
                    Provider provider = new Provider(lineSplit[1], lineSplit[2]);
                    String manufacturer = lineSplit[5];
                    String model = lineSplit[7];
                    arr[lineIndex] = new Electronics(inventoryNumber, price, quantity, provider, manufacturer, model);
                } else {
                    int inventoryNumber = Integer.parseInt(lineSplit[3]);
                    double price = Double.parseDouble(lineSplit[4]);
                    int quantity = Integer.parseInt(lineSplit[5]);
                    Provider provider = new Provider(lineSplit[1], lineSplit[2]);
                    String author = lineSplit[5];
                    String title = lineSplit[7];
                    arr[lineIndex] = new Book(inventoryNumber, price, quantity, provider, author, title);
                }
                lineIndex++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongPhoneNumberException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductLoader implements ImportData {

    private int numberOfBooks;
    private int numberOfEl;

    public int getNumberOfBooks() {
        return this.numberOfBooks;
    }

    public int getNumberOfEl() {
        return this.numberOfEl;
    }

    public int getNumberOfRows() {
        BufferedReader reader;
        int linesCount = 0;
        try {
            reader = new BufferedReader(new FileReader("salesproducts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                linesCount++;
                if(line.startsWith("1")){
                    numberOfEl++;
                }
                else if(line.startsWith("2")){
                    numberOfBooks++;
                }
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
                int inventoryNumber = Integer.parseInt(lineSplit[3]);
                double price = Double.parseDouble(lineSplit[4]);
                int quantity = Integer.parseInt(lineSplit[5]);
                Provider provider = new Provider(lineSplit[1], lineSplit[2]);
                if (lineSplit[0].equals("1")) {
                    String manufacturer = lineSplit[5];
                    String model = lineSplit[7];
                    arr[lineIndex] = new Electronics(inventoryNumber, price, quantity, provider, manufacturer, model);
                } else {
                    String author = lineSplit[5];
                    String title = lineSplit[7];
                    arr[lineIndex] = new Book(inventoryNumber, price, quantity, provider, author, title);
                }
                lineIndex++;
            }
            reader.close();
        } catch (IOException | WrongPhoneNumberException e) {
            e.printStackTrace();
        }
//        catch (WrongPhoneNumberException e) {
//            System.out.println(e);
//        }
        return arr;
    }

}

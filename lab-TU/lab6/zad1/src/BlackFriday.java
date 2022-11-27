import java.util.Arrays;

public class BlackFriday {
    public Electronics[] electronics;
    public Book[] books;

    void processSales() {
        ProductLoader pl = new ProductLoader();
        Object[] objs = pl.importDataFromFile();
        books = new Book[pl.getNumberOfRows()];
        electronics = new Electronics[pl.getNumberOfRows()];

        int index = 0;
        for (Object object : objs) {
            if (object instanceof Book) {
                books[index] = (Book) object;
            }
            if (object instanceof Electronics) {
                electronics[index] = (Electronics) object;
            }
            index++;
        }
    }
}
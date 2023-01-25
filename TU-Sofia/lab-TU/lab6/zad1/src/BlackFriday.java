import java.util.Arrays;

public class BlackFriday {
    public Electronics[] electronics;
    public Book[] books;

    void processSales() {
        ProductLoader pl = new ProductLoader();
        Object[] objs = pl.importDataFromFile();
        books = new Book[pl.getNumberOfBooks()];
        electronics = new Electronics[pl.getNumberOfEl()];

        int indexBook = 0;
        int indexEl = 0;
        for (Object object : objs) {
            if (object instanceof Book) {
                books[indexBook] = (Book) object;
                indexBook++;
            }
            if (object instanceof Electronics) {
                electronics[indexEl] = (Electronics) object;
                indexEl++;
            }
        }
    }
}
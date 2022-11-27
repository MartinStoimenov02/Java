import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static Electronics[] arrayOfConcerts = null;
    public static Book[] arrayOfVolleyball = null;
    private final static int SIZE_OF_OBJECT_BUFFER = 100;

    public static void main(String[] args) {
//        Provider provider=new Provider("Ivan", "0898765432");
//        Provider provider1=new Provider("Boris", "0987654321");
//        Electronics electronics=new Electronics("1", 200, 20, provider, "LG", "deik-32r");
//        Book book = new Book("1", 200, 20, provider1, "Dimitar Dimov", "Tutun");
//        System.out.println(electronics.toString());
//        System.out.println(book.toString());

        BlackFriday bf = new BlackFriday();
        bf.processSales();
        System.out.println(Arrays.toString(bf.books));
        System.out.println(Arrays.toString(bf.electronics));
        for (Electronics electronic : bf.electronics) {
            if (electronic != null) {
                System.out.println(electronic.getModel());
            }
        }
        for (Book book : bf.books) {
            if (book != null) {
                System.out.println(book.getTitle());
            }
        }
    }
}
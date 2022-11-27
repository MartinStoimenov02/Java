public class Book extends Product{
    String author;
    String title;

    public Book(int inventoryNumber, double price, int quantity, Provider provider, String author, String title) {
        super(inventoryNumber, price, quantity, provider);
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public double getPromotionalPrice() {
        return (this.price*0.5+price);
    }

    @Override
    public boolean sellProduct(int piece) throws NoMoreProductsException {
        this.quantity=this.quantity-piece;
        if(this.quantity>0){
            return true;
        }
        else{
            throw new NoMoreProductsException();
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", provider=" + provider.phoneNumber + " - " + provider.name+
                '}';
    }
}

public abstract class Product {
    int inventoryNumber;
    double price;
    int quantity;
    Provider provider;

    public Product(int inventoryNumber, double price, int quantity, Provider provider) {
        this.inventoryNumber = inventoryNumber;
        this.price = price;
        this.quantity = quantity;
        this.provider = provider;
    }

    public abstract double getPromotionalPrice();

    public abstract boolean sellProduct(int piece) throws NoMoreProductsException;

    @Override
    public String toString() {
        return "Product{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", provider=" + provider +
                '}';
    }
}

public class Electronics extends Product{

    String manufacturer;
    String model;

    public Electronics(int inventoryNumber, double price, int quantity, Provider provider,
                       String manufacturer, String model) {
        super(inventoryNumber, price, quantity, provider);
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public double getPromotionalPrice() {
        return (this.price*0.1+price);
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
        return "Electronics{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", provider=" + provider.phoneNumber + " - " + provider.name+
                '}';
    }
}

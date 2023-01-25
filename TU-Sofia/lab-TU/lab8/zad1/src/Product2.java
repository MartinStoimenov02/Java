public class Product2 implements GetProductsTo, DeliveryAPI{
    String type;
    int idOfProvider;
    double price;
    int number;

    public Product2(String type, int idOfProvider, double price, int number) {
        this.type = type;
        this.idOfProvider = idOfProvider;
        this.price = price;
        this.number = number;
    }

    public double getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public int getNumber(int idOfProvider)
    {
        if( this.getIdOfProvider() == idOfProvider )
        {
            return this.number;
        }
        return -1;
    }

    public String getType( int idOfProvider )
    {
        if( this.getIdOfProvider() == idOfProvider )
        {
            return this.type;
        }
        return null;
    }

    public int getIdOfProvider() {
        return this.idOfProvider;
    }

    @Override
    public String toString() {
        return "Product2{" +
                "type='" + type + '\'' +
                ", idOfProvider=" + idOfProvider +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product1 product1 = new Product1("Electronic", 1, 200, 1000);
        Product1 product11 = new Product1("Electronic", 1, 400, 1001);
        Product2 product2 = new Product2("Clothes", 2, 40, 2000);
        Product2 product22 = new Product2("Clothes", 2, 250, 2001);
        Product3 product3 = new Product3("Books", 3, 190, 3000);
        Product3 product33 = new Product3("Books", 3, 20, 3001);

        ArrayList<GetProductsTo> products = new ArrayList<>();
        ArrayList<DeliveryAPI> devApi = new ArrayList<>();

        products.add(product1);
        products.add(product11);
        products.add(product2);
        products.add(product22);
        products.add(product3);
        products.add(product33);

        devApi.add(product1);
        devApi.add(product11);
        devApi.add(product2);
        devApi.add(product22);
        devApi.add(product3);
        devApi.add(product33);

        PresentStore presentStore = new PresentStore(products, devApi);

        ArrayList<GetProductsTo> lowerPriceProducts = new ArrayList<>();
        lowerPriceProducts = presentStore.GetLowerPriceProducts(201);

        for(GetProductsTo product : lowerPriceProducts){
            System.out.println(product.toString());
        }

        ArrayList<DeliveryAPI> allProviderProducts = new ArrayList<>();
        allProviderProducts = presentStore.GetAllProviderProducts(1);

        for(DeliveryAPI product : allProviderProducts){
            System.out.println(product.toString());
        }
    }
}
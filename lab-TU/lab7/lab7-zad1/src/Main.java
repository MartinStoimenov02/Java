import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        BrokenDevice brokenDevice=new BrokenDevice("Lenovo", "A1000", 100, "pregrqva", 2);
        BrokenDevice brokenDevice1=new BrokenDevice("Lenovo", "A5000", 500, "broken display", 5);
        Service service=new Service();
        BrokenDevice brokenDevice2 = service.BrokenDeviceInput();
        ArrayList<BrokenDevice> brokenDevicesList = new ArrayList<BrokenDevice>();
        ArrayList<BrokenDevice> repairedDevicesList = new ArrayList<BrokenDevice>();
        brokenDevicesList.add(brokenDevice);
        brokenDevicesList.add(brokenDevice1);
        brokenDevicesList.add(brokenDevice2);
        FileWriter writer = new FileWriter("Broken devices.txt");
        for(BrokenDevice br: brokenDevicesList) {
            writer.write(br + System.lineSeparator());
        }
        writer.close();

        Service service1=new Service(brokenDevicesList, repairedDevicesList);
        service1.printBrokenDevices();
        int index;
        System.out.println("element to move:");
        index=sc.nextInt();

        BrokenDevice brokenDevice3 = brokenDevicesList.get(index-1);
        service1.moveRepairedDevice(brokenDevice3);

        String simptom;
        System.out.println("simptom:");
        simptom=sc.next();
        service1.printBySimptom(simptom);

        service1.totalPrice();

        double pricePrihod;
        System.out.println("price prihod: ");
        pricePrihod=sc.nextInt();
        System.out.println(service1.prihod(pricePrihod));
    }
}
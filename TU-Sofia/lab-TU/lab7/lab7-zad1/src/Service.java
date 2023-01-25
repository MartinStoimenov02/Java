import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Service {
    ArrayList<BrokenDevice> brokenDev;
    ArrayList<BrokenDevice> repairedDev;

    public Service(ArrayList<BrokenDevice> brokenDev, ArrayList<BrokenDevice> repairedDev) {
        this.brokenDev = brokenDev;
        this.repairedDev = repairedDev;
    }

    public Service(){}

    public BrokenDevice BrokenDeviceInput(){
        String mark, model, simptoms;
        double price;
        int days;
        Scanner sc = new Scanner(System.in);
        System.out.println("mark:");
        mark=sc.next();
        System.out.println("model:");
        model=sc.next();
        System.out.println("price:");
        price=sc.nextDouble();
        System.out.println("simptomps:");
        simptoms=sc.next();
        System.out.println("days:");
        days=sc.nextInt();
        BrokenDevice brokenDevice=new BrokenDevice(mark, model, price, simptoms, days);
        return brokenDevice;
    }

    public void printBrokenDevices(){
        for (BrokenDevice p : this.brokenDev) {
            System.out.println(p.toString());
        }
    }

    public void printRepairedDevices(){
        for (BrokenDevice p : this.repairedDev) {
            System.out.println(p.toString());
        }
    }

    public void moveRepairedDevice(BrokenDevice brokenDevice) throws IOException {
        int count = repairedDev.size();
        this.repairedDev.add(count, brokenDevice);
        FileWriter writer = new FileWriter("repairedDevices.txt");
            writer.write(brokenDevice + System.lineSeparator());
        writer.close();
    }

    public void printBySimptom(String simptom){
        for(BrokenDevice br : this.brokenDev){
            if(br.simptoms.equals(simptom)){
                System.out.println(br.toString());
            }
        }
    }

    public double totalPrice(){
        double total=0;
        for(BrokenDevice br : this.brokenDev){
            total+=br.price;
        }
        return total;
    }

    public double prihod(double price){
        int count = this.brokenDev.size();
        return price*count;
    }
}

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class readParamsFromConsole {
    public static ArrayList<String> inputParams(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> searchParams = new ArrayList<>();
        String mark="", model="", engine="", transmission="", state="", colour="", coupeType="", city="", priceFrom="", priceTo="",
                yearFrom="", yearTo="", powerFrom="", powerTo="", kilometres="", euroCategory="", order="";
           System.out.println("mark");
           mark=scanner.nextLine();
           searchParams.add("mark");
           searchParams.add(mark);
           System.out.println("model");
           model=scanner.nextLine();
           searchParams.add("model");
           searchParams.add(model);
           System.out.println("price from: ");
           priceFrom=scanner.nextLine();
           searchParams.add("priceFrom");
           searchParams.add(priceFrom);
           System.out.println("price to:");
           priceTo=scanner.nextLine();
           if(!priceTo.equals("") && !priceFrom.equals("")) {
               if (Integer.parseInt(priceTo) < Integer.parseInt(priceFrom)) {
                   System.out.println("second price must be larger than first!");
               }
           }
            searchParams.add("priceTo");
            searchParams.add(priceTo);
           System.out.println("engine: ");
           engine=scanner.nextLine();
           searchParams.add("engine");
           searchParams.add(engine);
           System.out.println("transmission: ");
           transmission=scanner.nextLine();
           searchParams.add("transmission");
           searchParams.add(transmission);
           System.out.println("state:");
           state=scanner.nextLine();
           searchParams.add("state");
           searchParams.add(state);
           System.out.println("year of Manufacture from: ");
           yearFrom=scanner.nextLine();
           searchParams.add("yearFrom");
           searchParams.add(yearFrom);
           System.out.println("year of Manufacture to:");
           yearTo=scanner.nextLine();
           if(!yearTo.equals("") && !yearFrom.equals("")){
               if(Integer.parseInt(yearTo)<Integer.parseInt(yearFrom)){
                   System.out.println("second year must be larger than first!");
               }
           }
           searchParams.add("yearTo");
           searchParams.add(yearTo);
           System.out.println("power from: ");
           powerFrom=scanner.nextLine();
           searchParams.add("powerFrom");
           searchParams.add(powerFrom);
           System.out.println("power to: ");
           powerTo=scanner.nextLine();
           if(!powerTo.equals("") && !powerFrom.equals("")){
               if(Integer.parseInt(powerTo)<Integer.parseInt(powerFrom)){
                   System.out.println("second power must be larger than first!");
               }
           }
           searchParams.add("powerTo");
            searchParams.add(powerTo);
           System.out.println("kilometres: ");
           kilometres=scanner.nextLine();
           searchParams.add("kilometres");
           searchParams.add(kilometres);
           System.out.println("colour: ");
           colour=scanner.nextLine();
           searchParams.add("colour");
           searchParams.add(colour);
           System.out.println("coupe type: ");
           coupeType=scanner.nextLine();
           searchParams.add("coupeType");
           searchParams.add(coupeType);
           System.out.println("euro");
           euroCategory=scanner.nextLine();
           searchParams.add("euroCategory");
           searchParams.add(euroCategory);
           System.out.println("city");
           city=scanner.nextLine();
           searchParams.add("city");
           searchParams.add(city);
           System.out.println("order by (mark, price, year, power, kilometres, euroCategory, date): ");
           order=scanner.nextLine();
        searchParams.add("order");
        searchParams.add(order);
        return searchParams;
    }

    public static Vehicles inputVehicleOptions(int id){
        Scanner scanner = new Scanner(System.in);
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
        String mark="", model="", engine="", transmission="", state="", colour="", coupeType="", city="", description="";
        double price=0;
        int year=0, power=0, kilometres=0, euroCategory=0;

        do{
            System.out.println("mark");
            mark=scanner.nextLine();
        }while(mark.equals(""));

        do{
            System.out.println("model");
            model=scanner.nextLine();
        }while(model.equals(""));

        do{
            try{
                System.out.println("price: ");
                price=scanner.nextDouble();
            }catch(Exception e){
                System.out.println("input again!");
                scanner.nextLine();
            }
        }while(price==0);

        do{
            scanner.nextLine();
            System.out.println("engine: ");
            engine=scanner.nextLine();
        }while(engine.equals(""));

        do{
            System.out.println("transmission: ");
            transmission=scanner.nextLine();
        }while(transmission.equals(""));

        do{
            System.out.println("state:");
            state=scanner.nextLine();
        }while(state.equals(""));


        do{
            try{
                System.out.println("year of Manufacture: ");
                year=scanner.nextInt();
            }catch(Exception e){
                System.out.println("input again!");
                scanner.nextLine();
            }
        }while(year==0);

        do{
            try{
                System.out.println("power: ");
                power=scanner.nextInt();
            }catch(Exception e){
                System.out.println("input again!");
                scanner.nextLine();
            }
        }while(power==0);

        do{
            try{
                System.out.println("kilometres: ");
                kilometres=scanner.nextInt();
            }catch(Exception e){
                System.out.println("input again!");
                scanner.nextLine();
            }
        }while(kilometres==0);

        do{
            System.out.println("colour: ");
            colour=scanner.next();
        }while(colour.equals(""));

        do{
            System.out.println("coupe type: ");
            coupeType=scanner.next();
        }while(coupeType.equals(""));


        do{
            try{
                System.out.println("euro");
                euroCategory=scanner.nextInt();
            }catch(Exception e){
                System.out.println("input again!");
                scanner.nextLine();
            }
        }while(euroCategory==0);

        do{
            scanner.nextLine();
            System.out.println("city");
            city=scanner.nextLine();
        }while(city.equals(""));

        System.out.println("Description");
        description = scanner.nextLine();

        Vehicles vehicle = new Vehicles(date, mark, model, price, engine, transmission, state, year, power, kilometres, colour,
                coupeType, euroCategory, city, description, id);

        return vehicle;
    }
}

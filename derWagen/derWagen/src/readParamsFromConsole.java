import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class readParamsFromConsole {
    public static ArrayList<String> inputParams(Scanner sc, PrintStream out){
        ArrayList<String> searchParams = new ArrayList<>();
        String mark="", model="", engine="", transmission="", state="", colour="", coupeType="", city="", priceFrom="", priceTo="",
                yearFrom="", yearTo="", powerFrom="", powerTo="", kilometres="", euroCategory="", order="";
           out.println("mark");
           mark=sc.nextLine();
           searchParams.add("mark");
           searchParams.add(mark);
           out.println("model");
           model=sc.nextLine();
           searchParams.add("model");
           searchParams.add(model);
           out.println("price from: ");
           priceFrom=sc.nextLine();
           searchParams.add("priceFrom");
           searchParams.add(priceFrom);
           out.println("price to:");
           priceTo=sc.nextLine();
           if(!priceTo.equals("") && !priceFrom.equals("")) {
               if (Integer.parseInt(priceTo) < Integer.parseInt(priceFrom)) {
                   out.println("second price must be larger than first!");
               }
           }
            searchParams.add("priceTo");
            searchParams.add(priceTo);
           out.println("engine: ");
           engine=sc.nextLine();
           searchParams.add("engine");
           searchParams.add(engine);
           out.println("transmission: ");
           transmission=sc.nextLine();
           searchParams.add("transmission");
           searchParams.add(transmission);
           out.println("state:");
           state=sc.nextLine();
           searchParams.add("state");
           searchParams.add(state);
           out.println("year of Manufacture from: ");
           yearFrom=sc.nextLine();
           searchParams.add("yearFrom");
           searchParams.add(yearFrom);
           out.println("year of Manufacture to:");
           yearTo=sc.nextLine();
           if(!yearTo.equals("") && !yearFrom.equals("")){
               if(Integer.parseInt(yearTo)<Integer.parseInt(yearFrom)){
                   out.println("second year must be larger than first!");
               }
           }
           searchParams.add("yearTo");
           searchParams.add(yearTo);
           out.println("power from: ");
           powerFrom=sc.nextLine();
           searchParams.add("powerFrom");
           searchParams.add(powerFrom);
           out.println("power to: ");
           powerTo=sc.nextLine();
           if(!powerTo.equals("") && !powerFrom.equals("")){
               if(Integer.parseInt(powerTo)<Integer.parseInt(powerFrom)){
                   out.println("second power must be larger than first!");
               }
           }
           searchParams.add("powerTo");
            searchParams.add(powerTo);
           out.println("kilometres: ");
           kilometres=sc.nextLine();
           searchParams.add("kilometres");
           searchParams.add(kilometres);
           out.println("colour: ");
           colour=sc.nextLine();
           searchParams.add("colour");
           searchParams.add(colour);
           out.println("coupe type: ");
           coupeType=sc.nextLine();
           searchParams.add("coupeType");
           searchParams.add(coupeType);
           out.println("euro");
           euroCategory=sc.nextLine();
           searchParams.add("euroCategory");
           searchParams.add(euroCategory);
           out.println("city");
           city=sc.nextLine();
           searchParams.add("city");
           searchParams.add(city);
           out.println("order by (mark, price, year, power, kilometres, euroCategory, date): ");
           order=sc.nextLine();
        searchParams.add("order");
        searchParams.add(order);
        return searchParams;
    }

    public static Vehicle inputVehicleOptions(int idUsr, Scanner sc, PrintStream out){
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
        String mark="", model="", engine="", transmission="", state="", colour="", coupeType="", city="", description="";
        double price=0;
        int year=0, power=0, kilometres=0, euroCategory=0;

        do{
            out.println("mark");
            mark=sc.nextLine();
        }while(mark.equals(""));

        do{
            out.println("model");
            model=sc.nextLine();
        }while(model.equals(""));

        do{
            try{
                out.println("price: ");
                price=sc.nextDouble();
            }catch(Exception e){
                out.println("input again!");
                sc.nextLine();
            }
        }while(price==0);

        do{
            sc.nextLine();
            out.println("engine: ");
            engine=sc.nextLine();
        }while(engine.equals(""));

        do{
            out.println("transmission: ");
            transmission=sc.nextLine();
        }while(transmission.equals(""));

        do{
            out.println("state:");
            state=sc.nextLine();
        }while(state.equals(""));


        do{
            try{
                out.println("year of Manufacture: ");
                year=sc.nextInt();
            }catch(Exception e){
                out.println("input again!");
                sc.nextLine();
            }
        }while(year==0);

        do{
            try{
                out.println("power: ");
                power=sc.nextInt();
            }catch(Exception e){
                out.println("input again!");
                sc.nextLine();
            }
        }while(power==0);

        do{
            try{
                out.println("kilometres: ");
                kilometres=sc.nextInt();
            }catch(Exception e){
                out.println("input again!");
                sc.nextLine();
            }
        }while(kilometres==0);

        do{
            out.println("colour: ");
            colour=sc.next();
        }while(colour.equals(""));

        do{
            out.println("coupe type: ");
            coupeType=sc.next();
        }while(coupeType.equals(""));


        do{
            try{
                out.println("euro");
                euroCategory=sc.nextInt();
            }catch(Exception e){
                out.println("input again!");
                sc.nextLine();
            }
        }while(euroCategory==0);

        do{
            sc.nextLine();
            out.println("city");
            city=sc.nextLine();
        }while(city.equals(""));

        out.println("Description");
        description = sc.nextLine();

        Vehicle vehicle = new Vehicle(date, mark, model, price, engine, transmission, state, year, power, kilometres, colour,
                coupeType, euroCategory, city, description, idUsr);

        return vehicle;
    }
}

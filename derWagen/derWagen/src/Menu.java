import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static int typeOfListedVehicles;

    private ServerSocket server;
    private static Object usersLock;

    public Menu(){
        usersLock=new Object();
    }

    public void start(){
        try{
            myMenu(new Scanner(System.in), System.out);
            server=new ServerSocket(8080);
            while(true){
                Socket client = server.accept();
                Thread clientThread = new Thread(() -> {
                    Scanner sc=null;
                    PrintStream out=null;
                    try{
                        sc=new Scanner(client.getInputStream());
                        out=new PrintStream(client.getOutputStream());
                        myMenu(sc, out);
                    }catch(IOException | SQLException | SignUpException e){e.printStackTrace();}
                });
                clientThread.start();
            }
        }catch (IOException |SQLException |SignUpException e){e.printStackTrace();}
    }
    
    public static void myMenu(Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true){
            synchronized (usersLock){
                out.println("command(search, login, signUp, end)");
                String command=sc.nextLine().toLowerCase();
                switch (command) {
                    case "search" -> {
                        typeOfListedVehicles = 1;
                        VehicleOptions.makeQuery(0, sc, out);
                    }
                    case "signup" -> {
                        SignUp signUp = new SignUp();
                        signUp.signUp(sc, out);
                    }
                    case "login" -> {
                        Login login = new Login();
                        login.loginInfo(sc, out);
                    }
                    case "end" -> {
                        System.exit(14);
                    }
                }
            }
        }
    }

    public static void menuForNoVehicles(int idUsr, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true){
            if(typeOfListedVehicles==1){
                out.println("There isn't vehicles with this param!");
                if(idUsr==0) {
                    out.println("command(newSearch, menu)");
                }else{
                    out.println("command(newSearch, backToLoginMenu)");
                }
            }
            else if(typeOfListedVehicles==-1){
                out.println("there isn't your ads in your profile!");
                adsMenu(idUsr, sc, out);
            }

            String command=sc.nextLine().toLowerCase();
            switch(command){
                case "newsearch":
                    typeOfListedVehicles=1;
                    VehicleOptions.makeQuery(0, sc, out);
                    break;
                case "menu":
                    myMenu(sc, out);
                    break;
                case "backtologinmenu":
                    User user = Login.getUser();
                    loginMenu(idUsr, user, sc, out);
                    break;
            }
        }

    }

    public static void menuForAllVehiclesListed(int idUsr, String query, Vehicle []arr, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true) {
            if (idUsr == 0) {
                out.println("command(openVehicle, newSearch, menu)");
            } else {
                if (typeOfListedVehicles == 1) {
                    out.println("command(openVehicle, newSearch, backToLoginMenu)");
                } else{
                    out.println("command(openVehicle, backToAdsMenu, backToLoginMenu)");
                }
            }
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "newsearch":
                    typeOfListedVehicles = 1;
                    VehicleOptions.makeQuery(idUsr, sc, out);
                    break;
                case "openvehicle":
                    VehicleOptions.readVehicle(arr, query, idUsr, sc, out);
                case "menu":
                    myMenu(sc, out);
                    break;
                case "backtologinmenu":
                    User user = Login.getUser();
                    loginMenu(idUsr, user, sc, out);
                    break;
                case "backtoadsmenu":
                    adsMenu(idUsr, sc, out);
                    break;
            }
        }
    }

    public static void menuForOneVehicle(int idUsr, String query, Vehicle vehicle, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true) {
            if (idUsr == 0) {
                out.println("command(backToAds, exportToFile, newSearch, menu)");
            } else {
                if (typeOfListedVehicles == 1) {
                    out.println("command(backToAds, exportToFile, saveInProfile/unsaveInProfile, newSearch, backToLoginMenu)");
                } else if (typeOfListedVehicles == 0) {
                    out.println("command(backToAds, exportToFile, unsaveInProfile, backToAdsMenu, backToLoginMenu)");
                }else if (typeOfListedVehicles == 2) {
                    out.println("command(backToAds, exportToFile, saveInProfile, backToAdsMenu, backToLoginMenu)");
                }else {
                    out.println("command(backToAds, exportToFile, edit, delete, backToAdsMenu, backToLoginMenu)");
                }
            }
            String command = sc.nextLine().toLowerCase();
            UserOptions userOptions = new UserOptions();
            AdOptions adOptions = new AdOptions();
            switch (command) {
                case "newsearch":
                    typeOfListedVehicles = 1;
                    VehicleOptions.makeQuery(idUsr, sc, out);
                    break;
                case "exporttofile":
                    ExportToFile.exportToFile(vehicle, userOptions.getUserFromId(vehicle.getIdUsr(), out), sc, out);
                    break;
                case "backtoads":
                    VehicleOptions.readVehiclesFromDatabase(query, idUsr, sc, out);
                    break;
                case "saveinprofile":
                    if(!adOptions.saveAdInProfile(idUsr, vehicle.getId())){
                        out.println("this ad is already saved!");
                    }else{
                        out.println("this ad has been added successfully in your profile!");
                    }
                    break;
                case "unsaveinprofile":
                    if(!adOptions.unsaveAdInProfile(idUsr, vehicle.getId(), Login.user)){
                        out.println("this ad is already unsaved!");
                    }else{
                        out.println("this ad has been removed successfully from your profile!");
                    }
                    typeOfListedVehicles = 2;
                    menuForOneVehicle(idUsr, query, vehicle, sc, out);
                    break;
                case "menu":
                    myMenu(sc, out);
                    break;
                case "edit":
                    out.println("What to edit(mark, model, price, engine, transmission, state, year, power, kilometres, colour, " +
                            "coupeType, euroCategory, city, description) or want to back?:");
                    String property = sc.nextLine().toLowerCase();
                    if(property.equals("back")){
                        menuForOneVehicle(idUsr, query, vehicle, sc, out);
                    }
                    out.println("input "+property+": ");
                    String propertyValue = sc.nextLine().toLowerCase();
                    if(adOptions.editAd(vehicle.getId(), sc, out, property, propertyValue)){
                        out.println("ad has been edited successfully!");
                        vehicle.setField(property, propertyValue);
                        VehicleOptions.printVehicle(vehicle, idUsr, out);
                    }else{
                        out.println("something wrong!");
                    }
                    break;
                case "delete":
                    if(adOptions.deleteAd(vehicle.getId(), sc, out)){
                        out.println("ad has been deleted successfully!");
                        VehicleOptions.readVehiclesFromDatabase(query, idUsr, sc, out);
                    }else{
                        out.println("something wrong!");
                    }
                    break;
                case "backtologinmenu":
                    User user = Login.getUser();
                    loginMenu(idUsr, user, sc, out);
                    break;
                case "backtoadsmenu":
                    adsMenu(idUsr, sc, out);
                    break;
            }
        }
    }

    public static void loginMenu(int idUsr, User user, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true) {
            out.println("command(exit, search, ads, edit)");
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "search":
                    typeOfListedVehicles = 1;
                    VehicleOptions.makeQuery(idUsr, sc, out);
                    break;
                case "ads":
                    adsMenu(idUsr, sc, out);
                    break;
                case "edit":
                    editProfile(idUsr, user, sc, out);
                    break;
                case "exit":
                    myMenu(sc, out);
                    break;
            }
        }
    }

    public static void adsMenu(int idUsr, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true) {
            out.println("command(savedAds, myAds, addNewAd, backToLoginMenu)");
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "savedads":
                    typeOfListedVehicles = 0;
                    if(!AdOptions.savedAds(idUsr, sc, out)){out.println("there isn't saved ads in your profile!");}
                    break;
                case "myads":
                    typeOfListedVehicles = -1;
                    AdOptions.myAds(idUsr, sc, out);
                    break;
                case "addnewad":
                    Vehicle vehicle = AdOptions.addAd(idUsr, sc, out);
                    if(vehicle!=null){
                        out.println("Successfully added new ad in your profile!");
                        out.println(vehicle.toString());
                    }else{
                        out.println("something wrong! Try to add again!");
                    }
                    break;
                case "backtologinmenu":
                    User user = Login.getUser();
                    loginMenu(idUsr, user, sc, out);
                    break;
            }
        }
    }
    public static void editProfile(int idUsr, User user, Scanner sc, PrintStream out) throws IndexOfVehicleException, SQLException, SignUpException {
        while(true) {
            out.println("command(edit, delete, backToLoginMenu)");
            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "edit":
                    UserOptions options = new UserOptions();
                    out.println("What to edit(name, number, username, password) or want to back?:");
                    String property = sc.nextLine().toLowerCase();
                    if(options.editUser(idUsr, user, sc, out, property)){
                        out.println("successfully edited "+property+"!");
                    }else{
                        out.println("something wrong! Check property!");
                    }

                    break;
                case "delete":
                    if(UserOptions.deleteUser(idUsr, sc, out)){
                        out.println("successfully delete your profile "+user.getUsrName()+"!");
                        Menu.myMenu(sc, out);
                    }else{
                        out.println("can't delete your acount!");
                    }
                    break;
                case "backtologinmenu":
                    User user1 = Login.getUser();
                    loginMenu(idUsr, user1, sc, out);
                    break;
            }
        }
    }
}

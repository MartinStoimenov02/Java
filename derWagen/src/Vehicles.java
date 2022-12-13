import java.util.Date;

public class Vehicles {
    private int id;
    private Date date;
    private String mark;
    private String model;
    private double price;
    private String engine;
    private String transmission;
    private String state;
    private int yearOfManufacture;
    private int power;
    private int kilometres;
    private String colour;
    private String coupeType;
    private int euroCategory;
    private String city;
    private String description;
    int idUsr;

    public Vehicles(int id, Date date, String mark, String model, double price, String engine, String transmission,
                    String state, int yearOfManufacture, int power, int kilometres, String colour, String coupeType,
                    int euroCategory, String city, String description, int idUsr) {
        setId(id);
        setDate(date);
        setMark(mark);
        setModel(model);
        setPrice(price);
        setEngine(engine);
        setTransmission(transmission);
        setState(state);
        setYearOfManufacture(yearOfManufacture);
        setPower(power);
        setKilometres(kilometres);
        setColour(colour);
        setCoupeType(coupeType);
        setEuroCategory(euroCategory);
        setCity(city);
        setDescription(description);
        setIdUsr(idUsr);
    }

    public Vehicles(Date date, String mark, String model, double price, String engine, String transmission,
                    String state, int yearOfManufacture, int power, int kilometres, String colour, String coupeType,
                    int euroCategory, String city, String description, int idUsr) {
        setDate(date);
        setMark(mark);
        setModel(model);
        setPrice(price);
        setEngine(engine);
        setTransmission(transmission);
        setState(state);
        setYearOfManufacture(yearOfManufacture);
        setPower(power);
        setKilometres(kilometres);
        setColour(colour);
        setCoupeType(coupeType);
        setEuroCategory(euroCategory);
        setCity(city);
        setDescription(description);
        setIdUsr(idUsr);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public String getEngine() {
        return engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getState() {
        return state;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public int getPower() {
        return power;
    }

    public int getKilometres() {
        return kilometres;
    }

    public String getColour() {
        return colour;
    }

    public String getCoupeType() {
        return coupeType;
    }

    public int getEuroCategory() {
        return euroCategory;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public int getIdUsr() {
        return idUsr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setKilometres(int kilometres) {
        this.kilometres = kilometres;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setCoupeType(String coupeType) {
        this.coupeType = coupeType;
    }

    public void setEuroCategory(int euroCategory) {
        this.euroCategory = euroCategory;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }

    @Override
    public String toString() {
        return  "date: " + date + "\n" +
                "mark: " + mark + " " + model + "\n" +
                "price: " + price + "lv." +  "\n" +
                "engine: " + engine + "\n" +
                "transmission:" + transmission + "\n" +
                "state:" + state + "\n" +
                "year Of Manufacture:" + yearOfManufacture + "\n" +
                "power:" + power + "\n" +
                "kilometres:" + kilometres + "\n" +
                "colour: " + colour + "\n" +
                "coupe Type:" + coupeType + "\n" +
                "euro Category:" + euroCategory + "\n" +
                "city:" + city + "\n" +
                "Description:" + description + "\n"+
                "idUser:"+idUsr+"\n";
    }
}
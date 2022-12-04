public class BrokenDevice extends Device{
    String simptoms;
    int days;

    public BrokenDevice(String mark, String model, double price, String simptoms, int days) {
        super(mark, model, price);
        setSimptoms(simptoms);
        setDays(days);
    }

    public String getSimptoms() {
        return simptoms;
    }

    public int getDays() {
        return days;
    }

    public void setSimptoms(String simptoms) {
        this.simptoms = simptoms;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "BrokenDevice{" +
                "simptoms='" + simptoms + '\'' +
                ", days=" + days +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}

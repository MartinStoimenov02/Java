public abstract class Device {
    String mark;
    String model;
    double price;

    public Device(String mark, String model, double price) {
        setMark(mark);
        setModel(model);
        setPrice(price);
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

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

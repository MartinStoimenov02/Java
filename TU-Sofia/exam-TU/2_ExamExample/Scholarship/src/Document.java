public class Document {
    String name;
    String faculty;
    double uspeh;
    double dohod;

    public Document(String name, String faculty, double uspeh, double dohod){
        this.name=name;
        this.faculty=faculty;
        this.uspeh=uspeh;
        this.dohod=dohod;
    }

    public Document(String name, String faculty, double uspeh){
        this.name=name;
        this.faculty=faculty;
        this.uspeh=uspeh;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public double getUspeh() {
        return uspeh;
    }

    public double getDohod() {
        return dohod;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", uspeh=" + uspeh +
                ", dohod=" + dohod +
                '}';
    }
}

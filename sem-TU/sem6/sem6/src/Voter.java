import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Voter implements Comparator {
    private String name;
    private String city;
    private String street;
    private String address;

    public Voter(String name, String city, String street, String address) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List filter(List<Voter> voterList){
        List<Voter> newVoters = new ArrayList<>();
        for(Voter v : voterList){
            if(v.getCity().equals("Sofia")){
                newVoters.add(v);
            }
        }
        return newVoters;
    }

    public List sort(List <Voter> voterList){
        voterList.sort(Comparator.comparing(Voter::getStreet).thenComparing(Voter::getAddress));
        return voterList;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return Comparator.super.thenComparing(other);
    }
}
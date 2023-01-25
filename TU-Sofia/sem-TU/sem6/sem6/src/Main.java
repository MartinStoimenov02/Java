import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Voter> voterList = new ArrayList<>();
        Voter voter = new Voter("name", "Sofia", "Boris 3", "9");
        Voter voter1 = new Voter("name1", "Plovdiv", "Ivan Vazov", "5");
        Voter voter2 = new Voter("name2", "Blagoevgrad", "Asen 2", "10");
        voterList.add(voter);
        voterList.add(voter1);
        voterList.add(voter2);
        for(Voter v : voterList){
            System.out.println(v.toString());
        }

        System.out.println("sorted");
        voterList=voter.sort(voterList);
        for(Voter v : voterList){
            System.out.println(v.toString());
        }

        System.out.println("filtered:");
        voterList=voter.filter(voterList);
        for(Voter v : voterList){
            System.out.println(v.toString());
        }
    }
}
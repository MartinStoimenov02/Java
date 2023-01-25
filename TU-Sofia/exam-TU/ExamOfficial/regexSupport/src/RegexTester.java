import java.util.ArrayList;
import java.util.List;

public class RegexTester {
    public static List<Boolean> test (Regex regex, String[] strings){
        List<Boolean> testers = new ArrayList<>();
        for(int i=0; i<strings.length; i++){
            if(strings[i].matches(regex.getPattern())){
                testers.add(true);
            }
            else{
                testers.add(false);
            }
        }
        return testers;
    }
}

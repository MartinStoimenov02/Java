import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Regex implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String pattern;
    private String description;
    private int rating;
    public static int nextId=0;

    public Regex(String pattern, String description) throws IOException {
        this.pattern=pattern;
        this.description=description;
        this.rating=0;
        nextId=getLastId()+1;
        this.id=nextId;
    }

    public Regex(String pattern, String description, int id){
        this.pattern=pattern;
        this.description=description;
        this.rating=0;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getPattern() {
        return pattern;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getLastId() throws IOException {
        Server server = new Server();
        int latID=0;
        List<Regex> regexList = server.readFromFile();
        for(Regex r : regexList){
            latID=r.getId();
        }
        return latID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Regex{" +
                "id=" + id +
                ", pattern='" + pattern + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                "}\n";
    }
}

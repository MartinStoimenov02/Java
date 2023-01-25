public class Designation {
    int code;
    String title;

    public Designation(int code, String title) {
        this.code = code;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Designation{" +
                "code=" + code +
                ", title='" + title + '\'' +
                '}';
    }
}

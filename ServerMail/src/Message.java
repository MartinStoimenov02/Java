import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String time;
    private int sender;
    private int receiver;
    private String topic;
    private String text;
    private int read;

    public Message(int sender, int receiver, String topic, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.topic=topic;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.time = dtf.format(now);
        this.read=0;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String message) {
        this.text = message;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }


    public String toString(String sender, String receiver) {
        if(sender.equals(receiver)){sender=sender+"(me)";}
        return "sender: "+sender + "\t\t\t\t" + time + "\n" +
                "receiver: "+receiver + "\n" +
                "topic: " + topic + "\n" +
                "message: \n"+text + "\n";
    }
}

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.*;

public class MailboxOptions {
    public static String receiver;
    public static String sender;
    public static String time;
    public static String topic;
    public static String text;
    public static int read;
    public static void mailBox(PrintStream out, Scanner sc, int idUsr, String type) throws SQLException, SignUpException {
        List<Message> allMessages = new ArrayList<>();
        Set<String> files = Chat.listFilesUsingJavaIO();
        for(String filePath : files){
            for (Message m : Objects.requireNonNull(Chat.readChat("messagesFiles\\" + filePath, out))){
                if(type.equals("inbox")){
                    if (m.getReceiver()==idUsr){
                        allMessages.add(m);
                    }
                }else{
                    if (m.getSender()==idUsr){
                        allMessages.add(m);
                    }
                }

            }
        }
        allMessages.sort(Comparator.comparing(Message::getTime).reversed());
        if(allMessages.size()==0){
            out.println("There isn't mails in "+type+"!");
            Server.loginMenu(idUsr, sc, out);
        }
        int index=1;
        for(Message m : allMessages){
            setReceiverSender(m, idUsr, out, type);
            time=m.getTime();
            topic=m.getTopic();
            text=m.getText();
            read=m.getRead();
            out.print((index++)+") ");
            print(out, type);
        }
        Server.mailboxMenu(idUsr, out, sc, allMessages, type);
    }

    public static void read(List <Message> allMessages, int idUsr, PrintStream out, Scanner sc, String type) throws SQLException, SignUpException {
        int index;
        do {
            out.println("choose: ");
            index = sc.nextInt();
        } while (index>allMessages.size() || index<=0);
        sc.nextLine();
        setReceiverSender(allMessages.get(index-1), idUsr, out, type);
        Message message = allMessages.get(index-1);
        out.println(message.toString(sender, receiver));


        if(type.equals("sent")){
            String filePath = "messagesFiles\\msg"+idUsr+"-"+message.getReceiver()+".bin";
            Server.menuForOneMessage(index, idUsr, out, sc, allMessages, filePath, type);
        }

        String filePath = "messagesFiles\\msg"+message.getSender()+"-"+idUsr+".bin";
        List<Message> messagesFromFile = Chat.readChat(filePath, out);

        int count=0;
        for(Message m : messagesFromFile){
            if(message.getSender()==m.getSender() && message.getReceiver()==m.getReceiver() &&
                    message.getTime().equals(m.getTime()) && message.getTopic().equals(m.getTopic()) && message.getText().equals(m.getText())){
                break;
            }
            count++;
        }
        messagesFromFile.remove(count);
        message.setRead(1);
        messagesFromFile.add(message);
        Chat.saveNewMessage(messagesFromFile, filePath);
        Server.menuForOneMessage(count, idUsr, out, sc, allMessages, filePath, type);
    }


    public static void sentEmail(int friendID, int idUsr, Scanner sc, PrintStream out, String reply, String topic) throws SQLException, SignUpException {
        out.println("Last online: "+SearchUsers.getLastSeen(friendID, out));
        String filePath = Chat.createNewChat(idUsr, friendID);
        List <Message> messages = new ArrayList<>();
        if(Chat.readChat(filePath, out)!=null){
            messages=Chat.readChat(filePath, out);
        }
        Message message = Chat.writeMessage(idUsr, friendID, sc, out, reply, topic);
        messages.add(message);
        Chat.saveNewMessage(messages, filePath);
        UserOptions userOptions = new UserOptions();
        userOptions.addToContact(idUsr, friendID);
        out.println("mail sent successfully!");
        Server.loginMenu(idUsr, sc, out);
    }

    public static void setReceiverSender(Message m, int idUsr, PrintStream out, String type) throws SQLException, SignUpException {
        UserOptions userOptions = new UserOptions();
        User user = null;
        if(type.equals("inbox")){
            user = userOptions.getUserFromId(m.getSender(), out);
            if(user==null){
                sender="unknown user";
            }
            else{
                sender=user.getUsrName();
            }
            user = userOptions.getUserFromId(idUsr, out);
            receiver=user.getUsrName();
        }else{
            user = userOptions.getUserFromId(idUsr, out);
            sender=user.getUsrName()+" (me)";
            user = userOptions.getUserFromId(m.getReceiver(), out);
            if(user==null){
                receiver="unknown user";
            }
            else{
                receiver=user.getUsrName();
            }
        }



    }

    public static void print(PrintStream out, String type) {
        if(sender.equals(receiver)){
            sender=sender+"(me)";
            receiver=receiver+"(me)";
        }
        String readed="";
        if(read==0 && type.equals("inbox")){readed=" - unread";}
        if(type.equals("inbox")){
            out.println("sender: "+sender + "\t\t\t\t" + "topic: " + topic + "\t\t\t\t" + time + readed + "\n");
        }else{
            out.println("receiver: "+receiver + "\t\t\t\t" + "topic: " + topic + "\t\t\t\t" + time + readed + "\n");
        }

    }
}
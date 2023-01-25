public class Message {
    private int id;
    private String msg;
    private boolean status;
    private int idTS;

    public Message(int id, String msg, boolean status, int idTS) {
        setId(id);
        setMsg(msg);
        setStatus(status);
        setIdTS(idTS);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdTS() {
        return idTS;
    }

    public void setIdTS(int idTS) {
        this.idTS = idTS;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg;
    }
}

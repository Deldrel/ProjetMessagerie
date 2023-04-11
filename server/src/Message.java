import java.sql.Timestamp;

public class Message {
    private int id;
    private int user_id;
    private Timestamp timestamp;
    private String content;

    public Message(int id, int user_id, Timestamp timestamp, String content) {
        this.id = id;
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", userId=" + user_id + ", timestamp=" + timestamp + ", content=" + content + "]";
    }
}

import java.sql.Timestamp;

public class Log {
    private int id;
    private int user_id;
    private Timestamp timestamp;
    private String type;

    public Log(int id, int user_id, Timestamp timestamp, String type) {
        this.id = id;
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.type = type;
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

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Log [id=" + id + ", userId=" + user_id + ", timestamp=" + timestamp + ", type=" + type + "]";
    }
}

package model;

public class Notes {
    private String topic;
    private String dateOfCreate;
    private String email;
    private String message;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notes(String topic, String dateOfCreate, String email, String message) {
        this.topic = topic;
        this.dateOfCreate = dateOfCreate;
        this.email = email;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "topic='" + topic + '\'' +
                ", dateOfCreate='" + dateOfCreate + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

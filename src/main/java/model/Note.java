package model;

import java.util.Date;

public class Note {

    private int id;
    private String topic;
    private Date dateOfCreate;
    private String email;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
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

    public Note(int id, String topic, Date dateOfCreate, String email, String message) {
        this.id = id;
        this.topic = topic;
        this.dateOfCreate = dateOfCreate;
        this.email = email;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", dateOfCreate='" + dateOfCreate + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

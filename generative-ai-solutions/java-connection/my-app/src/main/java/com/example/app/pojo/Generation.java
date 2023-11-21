package com.example.app.pojo;

public class Generation {

    private String id;
    private String finish_reason;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
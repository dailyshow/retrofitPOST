package com.example.retrofitpractice5;

import com.google.gson.annotations.SerializedName;

public class Post {
    /**
     * {
     *     "userId": 1,
     *     "id": 1,
     *     "title": "sunt aut facere repellat ~~~",
     *     "body": "quia et suscipit\nsuscipit ~~~"
     * },
     */

    private int userId;
    private Integer id;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}

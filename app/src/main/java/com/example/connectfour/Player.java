package com.example.connectfour;

public class Player {
    private int id;
    private int color;

    public Player(int id, int color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }
}

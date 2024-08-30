package com.example.connectfour;

import android.net.Uri;

public class Player {
    private String name;
    private Uri imageUri;
    private String gridSize;
    private String discColor;

    public Player(String name, Uri imageUri, String gridSize, String discColor) {
        this.name = name;
        this.imageUri = imageUri;
        this.gridSize = gridSize;
        this.discColor = discColor;
    }

    // Getters and setters
    public String getName(){
        return name;
    }

    public Uri getImageUri(){
        return imageUri;
    }

    public String getGridSize(){
        return gridSize;
    }

    public String getDiscColor(){
        return discColor;
    }
}


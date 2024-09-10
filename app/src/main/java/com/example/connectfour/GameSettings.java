package com.example.connectfour;

public class GameSettings {

    private final String gameMode;
    private final String gridSize;
    private final String theme;

    public GameSettings(String gameMode, String gridSize, String theme) {
        this.gameMode = gameMode;
        this.gridSize = gridSize;
        this.theme = theme;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getGridSize() {
        return gridSize;
    }

    public String getTheme() {
        return theme;
    }
}

package com.example.connectfour;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class gridlayout extends AppCompatActivity {
    private GridLayout gridLayout;
    private TextView statusTextView;
    private GameBoard gameBoard;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayout);

        playerOne = new Player(1, Color.RED);
        playerTwo = new Player(2, Color.YELLOW);
        currentPlayer = playerOne;

        statusTextView = findViewById(R.id.statusTextView);
        gridLayout = findViewById(R.id.gridLayout);
        gameBoard = new GameBoard(6, 7); // This section of the code has to be updated
        initializeGame();
    }

    private void initializeGame() {
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(7); // 7 columns for Connect Four
        for (int i = 0; i < 42; i++) { // This section has to be updated
            Button button = new Button(this);
            button.setTag(i % 7);
            button.setBackgroundColor(Color.LTGRAY); // Default empty color
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = (int) getResources().getDimension(R.dimen.cell_size);
            params.height = (int) getResources().getDimension(R.dimen.cell_size);
            params.setMargins(5, 5, 5, 5);
            button.setLayoutParams(params);
            button.setOnClickListener(this::cellClicked);
            gridLayout.addView(button);
        }
        statusTextView.setText("Start Game");
    }

    @SuppressLint("SetTextI18n")
    private void cellClicked(View view) {
        int col = (int) view.getTag();
        boolean validMoveMade = false;

        for (int row = 5; row >= 0; row--) { // Check from the bottom row upwards
            int index = row * 7 + col;
            Button button = (Button) gridLayout.getChildAt(index);
            // Check if the spot is empty before placing a piece
            if (gameBoard.getBoard()[row][col] == 0) { // Ensure the spot is empty
                if (gameBoard.placePiece(col, currentPlayer.getId())) {
                    button.setBackgroundColor(currentPlayer.getColor());
                    validMoveMade = true;
                    if (gameBoard.checkForWin(currentPlayer.getId())) {
                        statusTextView.setText("Game own" + currentPlayer.getId());
                        disableButtons();
                    }
                    break; // Exit the loop once the piece is placed
                }
            }
        }

        if (validMoveMade) { // Only toggle the player if a valid move was made
            togglePlayer();
        }
    }

    private void togglePlayer() {
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
        statusTextView.setText("Player " + (currentPlayer.getId() == 1 ? "One's" : "Two's") + " Turn");
    }

    private void disableButtons() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            gridLayout.getChildAt(i).setEnabled(false);
        }
    }
}
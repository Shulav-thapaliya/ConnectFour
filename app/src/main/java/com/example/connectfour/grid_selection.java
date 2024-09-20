package com.example.connectfour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class grid_selection extends AppCompatActivity {

    private Spinner spinnerGridSize;
    private Spinner spinnerGridName;
    private Spinner spinnerGameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid_selection);

//        Spinner for the grid size
        spinnerGridSize = findViewById(R.id.spinnerGridSize);
        ArrayAdapter<CharSequence> adapterGridSize = ArrayAdapter.createFromResource(this,
                R.array.grid_sizes, R.layout.gridspinner);
        adapterGridSize.setDropDownViewResource(R.layout.gridspinner);
        spinnerGridSize.setAdapter(adapterGridSize);


//        Spinner for the grid name
        spinnerGridName = findViewById(R.id.spinnerGridName);
        ArrayAdapter<CharSequence> adapterGridName = ArrayAdapter.createFromResource(this,
                R.array.grid_names, R.layout.gridspinner);
        adapterGridName.setDropDownViewResource(R.layout.gridspinner);
        spinnerGridName.setAdapter(adapterGridName);

//        This section is for the game mode

        spinnerGameMode = findViewById(R.id.spinnerGameMode);
        ArrayAdapter<CharSequence> adapterGameMode = ArrayAdapter.createFromResource(this,
                R.array.game_modes, R.layout.gridspinner);
        adapterGameMode.setDropDownViewResource(R.layout.gridspinner);
        spinnerGameMode.setAdapter(adapterGameMode);

    }

//    This section of the code is to  create an object based on the information that is being provided to us
    public void createGameSettings(View view) {
        String selectedGameMode = spinnerGameMode.getSelectedItem().toString();
        String selectedGridSize = spinnerGridSize.getSelectedItem().toString();
        String selectedTheme = spinnerGridName.getSelectedItem().toString();

        GameSettings settings = new GameSettings(selectedGameMode, selectedGridSize, selectedTheme);

    // For demonstration, you could log these settings or display them in a toast
        Toast.makeText(this, "Settings: Mode=" + settings.getGameMode() +
                ", Grid Size=" + settings.getGridSize() +
                ", Theme=" + settings.getTheme(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(grid_selection.this, gridlayout.class);
//        intent.putExtra("GameSettings", (Serializable) settings);
        startActivity(intent);
    // You can now pass these settings to the next activity or use them to configure your game
    }


}
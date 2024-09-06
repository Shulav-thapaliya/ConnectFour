package com.example.connectfour; //This class is for the grid with circles which are buttons as well
                                  // Buttons show message "clicked" when clicked
import android.os.Bundle; 
import android.util.Log;
import android.view.Gravity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class GridActivity extends AppCompatActivity {

    private static final String TAG = "GridActivity";
    private GridLayout gridLayout;
    private Spinner spinner;
    private Button initializeButton;
    private String selectedSize = ""; // No default size

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        spinner = findViewById(R.id.spinner_grid_size);
        gridLayout = findViewById(R.id.grid_layout);
        initializeButton = findViewById(R.id.button_initialize);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.grid_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set spinner item selected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSize = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "Selected grid size: " + selectedSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set initialize button click listener
        initializeButton.setOnClickListener(v -> {
            if (!selectedSize.isEmpty()) {
                updateGridSize(selectedSize);
            } else {
                Log.w(TAG, "No grid size selected");
            }
        });
    }

    private void updateGridSize(String size) {

        // Remove old views
        gridLayout.removeAllViews();

        try {
            // Split the selected size
            String[] dimensions = size.split("x");
            int rows = Integer.parseInt(dimensions[0]);
            int columns = Integer.parseInt(dimensions[1]);

            if (rows <= 0 || columns <= 0) {
                throw new IllegalArgumentException("Grid size must be positive integers.");
            }

            // Update GridLayout properties
            gridLayout.setRowCount(rows);
            gridLayout.setColumnCount(columns);


            // Add new grid cells (buttons)
            for (int i = 0; i < rows * columns; i++) {
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(i / columns, 1f);
                params.columnSpec = GridLayout.spec(i % columns, 1f);

                Button button = createGridButton(i);
                gridLayout.addView(button, params);
            }

            // Ensure the grid fits the screen while maintaining its aspect ratio
            adjustGridLayout();
        } catch (NumberFormatException e) {
            Log.e(TAG, "Error parsing grid size: " + size, e);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Invalid grid size: " + size, e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error updating grid size", e);
        }
    }



    private CircularButton createGridButton(int index) {
        CircularButton button = new CircularButton(this);
        button.setText("Button " + (index + 1));

        // Create GridLayout.LayoutParams and set button size
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.rowSpec = GridLayout.spec(index / gridLayout.getColumnCount(), 1f);
        params.columnSpec = GridLayout.spec(index % gridLayout.getColumnCount(), 1f);

        // Apply layout parameters
        button.setLayoutParams(params);

        button.setOnClickListener(v -> {
            // Handle button click
            Button clickedButton = (Button) v;
            clickedButton.setText("Clicked!");
        });

        return button;
    }


    private void adjustGridLayout() {
        // Ensure the grid fits the screen while maintaining its aspect ratio
        ViewGroup.LayoutParams params = gridLayout.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        gridLayout.setLayoutParams(params);
    }

}

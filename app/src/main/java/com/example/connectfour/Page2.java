package com.example.connectfour;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class Page2 extends Activity {

    private Spinner spinnerGridSize;
    private Uri playerImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);


//         spinner for the grids
        spinnerGridSize = findViewById(R.id.spinnerGridSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grid_sizes, R.layout.spinner_page2);
        adapter.setDropDownViewResource(R.layout.spinner_page2);
        spinnerGridSize.setAdapter(adapter);
    }




    public void onPlayClicked(View view) {
        String gridSize = spinnerGridSize.getSelectedItem().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GRID_SIZE", gridSize);
        startActivity(intent);
    }
    public void onUploadImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            playerImageUri = data.getData();
            ImageView imageView = findViewById(R.id.imageViewPlayer);
            imageView.setImageURI(playerImageUri);
        }
    }

    public void onStartGame(View view) {
        EditText nameField = findViewById(R.id.editTextName);
        String name = nameField.getText().toString();

        Spinner gridSizeSpinner = findViewById(R.id.spinnerGridSize);
        String gridSize = gridSizeSpinner.getSelectedItem().toString();

        Spinner colorSpinner = findViewById(R.id.spinnerColor);
        String color = colorSpinner.getSelectedItem().toString();

        Player player = new Player(name, playerImageUri, gridSize, color);
        startGameWithPlayer(player);
    }

    private void startGameWithPlayer(Player player) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}










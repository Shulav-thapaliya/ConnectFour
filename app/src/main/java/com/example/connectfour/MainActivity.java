package com.example.connectfour;

import android.app.slice.SliceItem;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;




import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
//     This section of the code is to animate the text and the button
public void animate(View view) {
    final TextView textView = findViewById(R.id.textView);

    // Start the animation sequence
    textView.animate()
            .rotation(120) // Rotate the TextView by 120 degrees
            .scaleX(2f)    // Scale the TextView width to twice its size
            .scaleY(2f)    // Scale the TextView height to twice its size
            .setDuration(500) // Set the duration for the rotation and scaling
            .withEndAction(new Runnable() {
                @Override
                public void run() {
                    // Change the text color to black
                    textView.setTextColor(Color.WHITE);

                    // Fade out the TextView after changing color
                    textView.animate()
                            .alpha(0f) // Fade to completely transparent
                            .setDuration(300) // Set the duration of the fade-out
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setVisibility(View.GONE); // Make the TextView disappear
//                                  Intent to start the new Activity
                                    Intent intent = new Intent(MainActivity.this, Page2.class);
                                    startActivity(intent);
                                    finish();
                                }

                            })
                            .start();
                }
            })
            .start();
}

}
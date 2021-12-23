package vn.edu.huflit.themovieapp1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIMEOUT = 3000;
    TextView txtWelcome, txtWelcome1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);

        Animation fadeOut = new AlphaAnimation(0, 1);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1200);
        fadeOut.setDuration(1800);
        ImageView imageView = findViewById(R.id.logoWelcome);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Anton-Regular.ttf");
        txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome1 = findViewById(R.id.txtWelcome1);
        txtWelcome.setTypeface(typeface, Typeface.BOLD_ITALIC);
        txtWelcome1.setTypeface(typeface, Typeface.BOLD_ITALIC);

        imageView.setAnimation(fadeOut);
        txtWelcome.setAnimation(fadeOut);
        txtWelcome1.setAnimation(fadeOut);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}

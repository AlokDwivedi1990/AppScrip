package com.example.alokdwivedi.appscrip;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class launcher_page extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorAccent));

        setContentView(R.layout.launcher);

        img = (ImageView)findViewById(R.id.imgvw);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        img.startAnimation(animFadeIn);




        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Do something after 5s = 5000ms
                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                img.startAnimation(animFadeOut);
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      Intent i = new Intent(launcher_page.this, MainActivity.class);
                      startActivity(i);
                      finish();
                  }
              }, 700);

            }
        }, 2000);



    }
}

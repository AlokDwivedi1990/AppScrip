package com.example.alokdwivedi.appscrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainView {
   // first_page_next
   MainPresenter presenter;
   TextInputLayout name;
   String name_text;
   Intent intent;

    public static final String Name = "nameKey";

    public static SharedPreferences name_sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        toolbar.setTitle("Trivia");

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,android.R.color.holo_orange_light));

        name = (TextInputLayout)findViewById(R.id.input_layout_name);
        name_sharedPreferences = getSharedPreferences("MyPREFS", MODE_PRIVATE);
        editor = name_sharedPreferences.edit();
        intent = new Intent(this, Second_page.class);
        //presenter = new MainPresenterImpl(this);
        presenter = new MainPresenterImpl(this,name  , editor, intent);
    }



    public void move_to_next(View view) {
        presenter.onButtonClick();
    }


    @Override
    public void savePreference(String string, SharedPreferences.Editor editor, Intent i) {
        editor.putString(Name, string);
        editor.commit();
        if (!TextUtils.isEmpty(string))
        {
            startActivity(i);
        }
        else
        {
            Toast toast = Toast.makeText(MainActivity.this, "Oops, I think Name space is Empty!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

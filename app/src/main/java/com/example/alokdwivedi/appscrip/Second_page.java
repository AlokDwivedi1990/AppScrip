package com.example.alokdwivedi.appscrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Second_page extends AppCompatActivity implements MainView {
    public static SharedPreferences _sharedPreferences;
    SharedPreferences.Editor editor;
    TextView textView;
RadioGroup radioGroup;
RadioButton radioButton;
MainPresenter presenter;

    public static final String Cricketer_name = "cricketer_nameKey";

Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,android.R.color.holo_orange_light));

        _sharedPreferences = getSharedPreferences("MyPREFS", MODE_PRIVATE);
        editor = _sharedPreferences.edit();
         String name = _sharedPreferences.getString("nameKey", "");
        init(textView, name);

        // Toast.makeText(this,name , Toast.LENGTH_LONG ).show();

    }

    private void init(TextView textView, String name)
    {
      textView = (TextView)findViewById(R.id.title);
      textView.setText("Hello! "+name);
      i = new Intent(this, Third_page.class);
      radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
    }
    public void move_to_next(View view)
    {
        int radio = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(radio);
        presenter = new MainPresenterImpl(Second_page.this, this,radioGroup, radioButton, editor, i );
        presenter.onSecondPageButtonCick();
    }

    @Override
    public void savePreference(String string, SharedPreferences.Editor editor, Intent i) {
        if (radioButton == null)
        {
            Toast toast =  Toast.makeText(Second_page.this, "Oops, Please select one option", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else
        {
           // getCricketer_name = String.valueOf(radioButton.getText());
            editor.putString(Cricketer_name, string);
            editor.commit();

            startActivity(i);
        }
    }
}

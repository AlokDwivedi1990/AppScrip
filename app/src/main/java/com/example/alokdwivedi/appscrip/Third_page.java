package com.example.alokdwivedi.appscrip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Third_page extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Flag_color = "FlagcolorKey";


    CheckBox white, yellow, orange, green;
    String cricketername;
    TextView name;
    private  StringBuffer result;
    SqlHelperClass db;
    String strDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,android.R.color.holo_orange_light));

        setContentView(R.layout.indian_flag_color_page);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
         //strDate = mdformat.format(calendar.getTime());
strDate = DateFormat.getDateTimeInstance()
        .format(new Date());
        db = new SqlHelperClass(this);
        init();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cricketername = Second_page._sharedPreferences.getString("cricketer_nameKey", "");

    }


    private void init()
    {
        name = (TextView)findViewById(R.id.title);
        white = (CheckBox)findViewById(R.id.white);
        yellow = (CheckBox)findViewById(R.id.yellow);
        orange = (CheckBox)findViewById(R.id.orange);
        green =(CheckBox)findViewById(R.id.green);

        name.setText("Hello! "+ Second_page._sharedPreferences.getString("nameKey"," "));
    }

    public void move_to_next(View view) {
        result = new StringBuffer();

        if (!white.isChecked() && !yellow.isChecked() && !orange.isChecked() && !green.isChecked())
        {
            Toast toast = Toast.makeText(Third_page.this, "Please select atleast one option", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else
        {
            if (white.isChecked())
            {
                result.append(white.getText()).append(", ");
            }

            if (yellow.isChecked())
            {
                result.append(yellow.getText()).append(", ");
            }

            if (orange.isChecked())
            {
                result.append(orange.getText()).append(", ");
            }

            if (green.isChecked())
            {
                result.append(green.getText()).append(", ");
            }

            editor.putString(Flag_color, result.toString());
            editor.commit();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Summary");
            builder.setMessage("Hello! " + Second_page._sharedPreferences.getString("nameKey", "") + "\n" +
                    "Time: "+strDate+"\n"+
                    "Here are the answers selected:"+"\n"+
                    "Who is the best cricketer in the world?"+"\n"+
                    cricketername + "\n\n " +
                    "What are the colors in the national flag? "+"\n"+
                    sharedPreferences.getString("FlagcolorKey", "")+ "\n\n ");
            builder.setCancelable(false);


            //=====DATABASE insert
            final boolean isinserted = db.isInserted(strDate,  Second_page._sharedPreferences.getString("nameKey", ""),
                    cricketername ,
                    sharedPreferences.getString("FlagcolorKey", ""));

            //====
            builder.setNegativeButton("History", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isinserted)
                    {
                        Cursor res = db.showData();
                        if (res.getCount() == 0)
                        {
                            return;
                        }

                        StringBuffer stringBuffer = new StringBuffer();
                        while (res.moveToNext())
                        {
                            stringBuffer.append("GAME: " + res.getString(0) + "\n");
                            stringBuffer.append("Time: " + res.getString(1) + "\n");
                            stringBuffer.append("Name: " + res.getString(2) + "\n");
                            stringBuffer.append("Who is the best cricketer in the world? "+ "\n" + res.getString(3) + "\n");
                            stringBuffer.append("What are the colors in the national flag? "+"\n" + res.getString(4) + "\n\n");
                        }

                        AlertDialog.Builder showData = new AlertDialog.Builder(Third_page.this);
                        showData.setTitle("History");
                        showData.setMessage(stringBuffer);
                        showData.setCancelable(false);
                        //  showData.setNegativeButton("Close app", new O)
                        showData.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Third_page.this, MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                        });
                        showData.show();
                    }
                    else
                    {
                        Toast.makeText(Third_page.this,"Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }

            });

            builder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Third_page.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            });

            builder.show();
        }
    }
}

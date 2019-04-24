package com.example.alokdwivedi.appscrip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainPresenterImpl implements MainPresenter {

MainView mainView;
TextInputLayout name;
String name_text;
SharedPreferences.Editor editor;
Intent i;


RadioButton radioButton;
RadioGroup radioGroup;

Context context;

//======



MainPresenterImpl(MainView mainView, TextInputLayout name, SharedPreferences.Editor editor, Intent i)
{
    this.mainView = mainView;
    this.name = name;
    this.editor = editor;
    this.i = i;
}

    MainPresenterImpl(Context context , MainView mainView, RadioGroup radioGroup, RadioButton radioButton, SharedPreferences.Editor editor, Intent i)
    {
        this.context = context;
        this.mainView = mainView;
        this.radioGroup = radioGroup;
        this.radioButton = radioButton;
        this.editor = editor;
        this.i = i;
    }

    @Override
    public void onButtonClick() {
if (mainView!=null)
{
    name_text = name.getEditText().getText().toString();
    mainView.savePreference(name_text, editor, i);
}
}

    @Override
    public void onSecondPageButtonCick() {
        if (mainView!=null)
        {
            if (radioButton!=null)
            {
                mainView.savePreference(String.valueOf(radioButton.getText()), editor, i);
            }
            else {
               Toast toast =  Toast.makeText(context, "Oops, Please select one option..", Toast.LENGTH_SHORT);
               toast.setGravity(Gravity.CENTER, 0,0);
               toast.show();
            }
        }
    }

    @Override
    public void onThirdPageButtonClick() {

    }
}

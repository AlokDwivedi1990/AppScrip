package com.example.alokdwivedi.appscrip;

import android.content.Intent;
import android.content.SharedPreferences;

public interface MainView {

    void savePreference(String string, SharedPreferences.Editor editor, Intent i);

  //  void saveCricketer(String string, SharedPreferences.Editor editor, Intent i);

}



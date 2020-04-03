package com.example.gauharproject.ui.settings;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gauharproject.LoginActivity;
import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;
import com.example.gauharproject.db.UserDb;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Settings");


    }

    public LiveData<String> getText() {
        return mText;
    }
}
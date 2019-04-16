package com.example.myapplication.models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;

public class TrendModel {
    private int mId, mUserId;

    public TrendModel(int id, int userId, String companyName, String titel, String date, String logo, String description,String[] images) {
        mId = id;
        mUserId = userId;
        mCompanyName = companyName;
        mTitel = titel;
        mDate = date;
        mLogo = logo;
        mDescription = description;
        mImages = images;
    }

    public int getId() {
        return mId;
    }

    public int getUserId() {
        return mUserId;
    }

    private String mCompanyName, mTitel, mDate, mLogo, mDescription;
    private String[] mImages;

    public String getCompanyName() {
        return mCompanyName;
    }

    public String getTitel() {
        return mTitel;
    }

    public String getDate() {
        return mDate;
    }

    public String getLogo() {
        return mLogo;
    }

    public String getDescription() {
        return mDescription;
    }

    public String[] getImages() {
        return mImages;
    }







}



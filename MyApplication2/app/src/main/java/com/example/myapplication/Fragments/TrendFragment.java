package com.example.myapplication.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapters.TrendAdapter;
import com.example.myapplication.models.TrendModel;
import com.example.myapplication.models.TrenderFetcher;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TrendFragment extends Fragment {
    private RecyclerView mRecyclerView;

    ArrayList<TrendModel> list = new ArrayList<>();

    public static Fragment getInstance() {
        return new TrendFragment();
    }

    public TrendFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


    }



    TrenderFetcher fetcher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_trend, container, false );
        mRecyclerView = view.findViewById( R.id.trend_recycl );
        mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        setAdapter();

       fetcher = new TrenderFetcher(getContext());
        fetcher.setTrenderListener( new TrenderFetcher.TrenderListener() {
            @Override
            public void TrenderList(ArrayList<TrendModel> tlist) {
                list = tlist;
                setAdapter();

            }
        } );
        fetcher.fetch();

        return view;
    }


    void setAdapter() {
        if (isAdded()) {
            TrendAdapter trendAdapter = new TrendAdapter( list, this);
            mRecyclerView.setAdapter( trendAdapter );
        }
    }
}



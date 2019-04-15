package com.example.myapplication.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment {

    private ImageView mImageView;
    private String mImageName;

    public static ImageDialogFragment getInstance(String imageUrl) {
        ImageDialogFragment imageDialogFragment = new ImageDialogFragment();
        Bundle bundle = new Bundle(  );
        bundle.putString( "image_url",imageUrl );
        imageDialogFragment.setArguments( bundle );
        return  imageDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_image_dialog, container, false );
        mImageView = view.findViewById( R.id.img );
        String imgage_url = getArguments().getString( "image_url" );
        Glide.with( getContext() )
                .load( imgage_url )
                .centerCrop()
                .placeholder( R.drawable.blank_image )
                .into(mImageView );
        return view;
    }

}

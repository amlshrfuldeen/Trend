package com.example.myapplication.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.TrendSetData;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendTrendkFragment extends Fragment implements View.OnClickListener {

    public static final int PICK_IMAGE = 1;
    private EditText editTextTitel;
    private EditText editTextDesc;
    private Button sendData;
    private CircleImageView imgview1;
    private CircleImageView imgview2;
    private CircleImageView imgview3;

TrendSetData mTrendSetData=new TrendSetData();
    Context mContext;

    public SendTrendkFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_send_trendk, container, false );



        editTextTitel = view.findViewById( R.id.titel_txt );
        editTextDesc = view.findViewById( R.id.description_txt );


        sendData = view.findViewById( R.id.send_btn );
        sendData.setOnClickListener( this );

        imgview1 = view.findViewById( R.id.img1 );

        imgview1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult( Intent.createChooser( gallery, "select Picture" ), PICK_IMAGE );
                //احفضها في الفيو امج نفسه ثم احولها بستخدام فاكتوري بيت ماب وارسلها بيت ماب
            }
        } );


        imgview2 = view.findViewById( R.id.img2 );

        imgview2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult( Intent.createChooser( gallery, "select Picture" ), PICK_IMAGE );
                //احفضها في الفيو امج نفسه ثم احولها بستخدام فاكتوري بيت ماب وارسلها بيت ماب
            }
        } );


        imgview3 = view.findViewById( R.id.img3 );

        imgview3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult( Intent.createChooser( gallery, "select Picture" ), PICK_IMAGE );
                //احفضها في الفيو امج نفسه ثم احولها بستخدام فاكتوري بيت ماب وارسلها بيت ماب
            }
        } );

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == PICK_IMAGE) {
            Uri contentURI = data.getData();
//            try {
//               // Bitmap bmp = MediaStore.Images.Media.getBitmap( this.getContentResolver(), contentURI );
//               // imgview1.setImageBitmap( bmp );
//
//                //this.bmp = bmp;
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    //}

    @Override
    public void onClick(View v) {
        if (v == sendData) {

            //mTrendSetData.senddata();
        }
    }

}

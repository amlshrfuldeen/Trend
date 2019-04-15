package com.example.myapplication.adapters;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Fragments.ImageDialogFragment;
import com.example.myapplication.Fragments.TrendFragment;
import com.example.myapplication.R;
import com.example.myapplication.models.TrendModel;
import com.example.myapplication.models.TrenderFetcher;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


public class TrendAdapter extends RecyclerView.Adapter<TrendViewHolder> {

    private ArrayList<TrendModel> mTrenders;
    private TrendFragment mContext;


    public TrendAdapter(ArrayList<TrendModel> trenders, TrendFragment context) {
        mTrenders = trenders;
        mContext = context;
    }


    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View trenderCard = LayoutInflater.from( mContext.getContext() ).inflate( R.layout.card, parent, false );
        return new TrendViewHolder( trenderCard,mContext );
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        holder.bind( mTrenders.get( position ) );


    }

    @Override
    public int getItemCount() {
        return mTrenders.size();
    }
}


class TrendViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_LENGTH = 20 ;
    private ImageView mProfile, mImg1, mImg2, mImg3;
    private TextView mCompanyName, mTitel, mDescription, mDate;
    private MaterialButton mReply,mMore;

    private TrendFragment mContext;
    boolean isMore;

    public TrendViewHolder(@NonNull View itemView,TrendFragment context) {

        super( itemView );
        this.mContext=context;
        isMore = true;
        mProfile = itemView.findViewById( R.id.profil_img );
        mImg1 = itemView.findViewById( R.id.img1 );
        mImg2 = itemView.findViewById( R.id.img2 );
        mImg3 = itemView.findViewById( R.id.img3 );
        mCompanyName = itemView.findViewById( R.id.company_name_txt );
        mTitel = itemView.findViewById( R.id.titel_txt );
        mDescription = itemView.findViewById( R.id.description_txt );
        mDate = itemView.findViewById( R.id.date_tex );
        mReply = itemView.findViewById( R.id.reply_btn );
        mMore = itemView.findViewById( R.id.more_btn );


    }


    void bind(final TrendModel td) {
        mCompanyName.setText( td.getCompanyName() );
        mTitel.setText( td.getTitel() );
        mDate.setText( td.getDate()  );

        if(mDescription.length()>MAX_LENGTH)
        {
            shorDescription(td);
            mMore.setVisibility( View.VISIBLE );
        }

        mImg1.setVisibility( View.GONE );
        mImg2.setVisibility( View.GONE );
        mImg3.setVisibility( View.GONE );

        ImageView[] images = {mImg1,mImg2,mImg3};

        Glide.with( itemView )
                .load( TrenderFetcher.getImage( td.getLogo() ) )
                .centerCrop()
                .placeholder( R.drawable.blank_image )
                .into( mProfile );
       for (int index=0;index<td.getImages().length;index++){
           final String imgage_url = TrenderFetcher.getImage( td.getImages()[index] );
           ImageView imageView = images[index];
           Glide.with( itemView )
                   .load( imgage_url )
                   .centerCrop()
                   .placeholder( R.drawable.blank_image )
                   .into(imageView);
           imageView.setVisibility( View.VISIBLE );

           imageView.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   showImage(imgage_url);
               }
           } );


       }
        mMore.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMore){
                    mMore.setText( "Less" );
                    mDescription.setText( td.getDescription() );

                }else{
                    mMore.setText( "More" );
                    shorDescription(td);

                }

                isMore =!isMore;
            }
        } );

    }



    private void shorDescription(TrendModel td) {

        mDescription.setText( td.getDescription().substring( 0,MAX_LENGTH )+"..." );

    }

    private  void showImage(String imageUrl){
    FragmentTransaction ft = mContext.getFragmentManager().beginTransaction();
    Fragment prev = mContext.getFragmentManager().findFragmentByTag("dialog");
    if (prev != null) {
        ft.remove(prev);
    }
    ft.addToBackStack(null);

    ImageDialogFragment imageDialogFragment = ImageDialogFragment.getInstance(imageUrl);
    imageDialogFragment.show( ft,"dialog" );
}
}

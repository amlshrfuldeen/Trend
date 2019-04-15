package com.example.myapplication;

import android.view.View;
import android.widget.Button;

import com.example.myapplication.Fragments.TrendFragment;

import androidx.fragment.app.Fragment;

public class TrendActivity extends SingleFragmentActivity {





    @Override
    Fragment createFragment() {
        return TrendFragment.getInstance();

    }
}

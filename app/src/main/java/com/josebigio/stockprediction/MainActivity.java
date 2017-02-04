package com.josebigio.stockprediction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.josebigio.stockprediction.ui.views.implementations.SearchViewImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.searchViewFragment)
    FrameLayout searchViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SearchViewImp fragment = SearchViewImp.newInstance();
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.searchViewFragment,fragment).commit();
    }

}

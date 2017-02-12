package com.josebigio.stockprediction;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.josebigio.stockprediction.ui.presenters.interfaces.MainPresenter;
import com.josebigio.stockprediction.ui.views.adapters.MainAdapter;
import com.josebigio.stockprediction.ui.views.implementations.SearchViewImp;
import com.josebigio.stockprediction.ui.views.interfaces.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainView {



    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.actionButton)
    Button actionButton;
    @BindView(R.id.pager)
    ViewPager pager;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application)getApplication()).getMainComponent().inject(this);
        Timber.d("[lifecycle] onCreate. bundle %s",savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter.setView(this);
    }

    @Override
    public void initialize() {
        Timber.d("[lifecycle] initialize");
        adapter = new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        actionButton.setOnClickListener(button->{
            mainPresenter.onActionClicked();
        });
    }


    @Override
    public void addSearchView() {
        Timber.d("addSearchView");
        SearchViewImp fragment = SearchViewImp.newInstance();
        adapter.addFragment(fragment);
        adapter.notifyDataSetChanged();
        int currentItem = pager.getCurrentItem();
        pager.setCurrentItem(currentItem+1);
    }

}

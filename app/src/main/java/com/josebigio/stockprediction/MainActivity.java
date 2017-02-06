package com.josebigio.stockprediction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.josebigio.stockprediction.ui.presenters.interfaces.MainPresenter;
import com.josebigio.stockprediction.ui.views.implementations.SearchViewImp;
import com.josebigio.stockprediction.ui.views.interfaces.MainView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainView {



    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application)getApplication()).getMainComponent().inject(this);
        Timber.d("[lifecycle] onCreate. bunle %s",savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter.setView(this);

    }

    @Override
    public void startSearchView() {
        SearchViewImp fragment = SearchViewImp.newInstance();
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        if(supportFragmentManager.findFragmentByTag(SearchViewImp.class.getSimpleName()) == null) {
            supportFragmentManager.beginTransaction().add(R.id.searchViewFragment,fragment
                    ,SearchViewImp.class.getSimpleName()).commit();
        }
    }
}

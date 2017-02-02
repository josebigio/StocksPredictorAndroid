package com.josebigio.stockprediction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.josebigio.stockprediction.api.StocksProvider;
import com.josebigio.stockprediction.models.Stock;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject StocksProvider stocksProvider;

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((Application)getApplication()).getApiComponent().inject(this);
        autoCompleteTextView.addTextChangedListener(textWatcher);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(adapter);



    }

    private List<String> getResults(List<Stock> list) {
        List<String> result = new ArrayList<>();
        for(Stock stock: list) {
            result.add(stock.getCompanyName());
        }
        return result;
    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(final CharSequence s, int start, int before, int count) {
            stocksProvider.findStocks(s.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(stocks -> {
                        Timber.d("stocks: %s",stocks);
                        adapter.clear();
                        adapter.addAll(getResults(stocks));
                    }, error -> {
                        Timber.e("error getting stocks",error);
                    });


        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}

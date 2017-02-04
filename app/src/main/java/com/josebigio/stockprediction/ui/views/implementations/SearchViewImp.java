package com.josebigio.stockprediction.ui.views.implementations;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.josebigio.stockprediction.Application;
import com.josebigio.stockprediction.R;
import com.josebigio.stockprediction.ui.presenters.interfaces.SearchPresenter;
import com.josebigio.stockprediction.ui.views.interfaces.SearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * <h1>SearchViewImp</h1>
 */
public class SearchViewImp extends android.support.v4.app.Fragment implements SearchView {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    @Inject
    SearchPresenter searchPresenter;

    public static SearchViewImp newInstance() {
        SearchViewImp myFragment = new SearchViewImp();
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.search_view, container, false);
        ((Application)getActivity().getApplication()).getMainComponent().inject(this);
        ButterKnife.bind(this,result);
        searchPresenter.setView(this);
        autoCompleteTextView.addTextChangedListener(textWatcher);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            Timber.d("Selected: pos %d, view %s",position,view);
            searchPresenter.onStockSelected(position);

        });

        return result;
    }



    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(final CharSequence s, int start, int before, int count) {
            searchPresenter.onTextChanged(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };



    @Override
    public void clearResults() {
        adapter.clear();
    }

    @Override
    public void setSearchDataSet(List<String> results) {
        adapter.addAll(results);
    }
}

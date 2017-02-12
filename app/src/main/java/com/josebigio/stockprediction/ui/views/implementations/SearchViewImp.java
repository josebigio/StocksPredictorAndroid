package com.josebigio.stockprediction.ui.views.implementations;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.androidplot.xy.XYPlot;
import com.josebigio.stockprediction.Application;
import com.josebigio.stockprediction.R;
import com.josebigio.stockprediction.models.PlotData;
import com.josebigio.stockprediction.ui.presenters.interfaces.SearchPresenter;
import com.josebigio.stockprediction.ui.views.UnfilteredAdapter;
import com.josebigio.stockprediction.ui.views.interfaces.SearchView;
import com.josebigio.stockprediction.ui.views.plot.PlotState;
import com.josebigio.stockprediction.ui.views.plot.PlotView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.josebigio.stockprediction.R.id.plot;

/**
 * <h1>SearchViewImp</h1>
 */
public class SearchViewImp extends android.support.v4.app.Fragment implements SearchView {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @BindView(plot)
    XYPlot graph;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    PlotView plotView;


    @Inject
    SearchPresenter searchPresenter;
    ProgressDialog progressDialog;
    ArrayAdapter<String> adapter;


    private static final String PLOT_DATA_KEY = "PlotDataKey";

    public static SearchViewImp newInstance() {
        SearchViewImp myFragment = new SearchViewImp();

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.d("[lifecycle] onCreateView. bundle %s",savedInstanceState);
        View result =  inflater.inflate(R.layout.search_view, container, false);
        ((Application)getActivity().getApplication()).getMainComponent().inject(this);
        ButterKnife.bind(this,result);
        searchPresenter.setView(this);
        plotView = new PlotView(graph);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressBar.setVisibility(View.GONE);
        autoCompleteTextView.addTextChangedListener(textWatcher);
        adapter = new UnfilteredAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            Timber.d("Selected: pos %d, view %s",position,view);
            hideKeyboard();
            searchPresenter.onStockSelected(position);

        });
        if(savedInstanceState != null) {
            PlotState state = (PlotState)savedInstanceState.getSerializable(PLOT_DATA_KEY);
            if(state!=null) {
                plotView.setState(state);
            }
        }
        return result;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        try {
            outState.putSerializable(PLOT_DATA_KEY,plotView.getState());
        }
        catch (Exception e) {
            Timber.e("Unable to serialize plot data: %s",e);
        }
        Timber.d("[lifecycle] onSaveInstanceState. outState %s",outState);
        super.onSaveInstanceState(outState);
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

    @Override
    public void renderPlot(String title, PlotData plotData) {
        plotView.render(plotData,title);
    }

    @Override
    public void showLoading(boolean show) {
        if(show) {
            progressDialog.show();
        }
        else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showTextLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String error) {
       new AlertDialog.Builder(getContext())
                .setTitle("Error")
                .setMessage(error)
                .create()
                .show();
    }


    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

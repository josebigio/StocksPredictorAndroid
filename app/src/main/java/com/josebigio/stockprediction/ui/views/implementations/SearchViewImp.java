package com.josebigio.stockprediction.ui.views.implementations;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
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

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.josebigio.stockprediction.Application;
import com.josebigio.stockprediction.R;
import com.josebigio.stockprediction.models.PlotData;
import com.josebigio.stockprediction.ui.presenters.interfaces.SearchPresenter;
import com.josebigio.stockprediction.ui.views.UnfilteredAdapter;
import com.josebigio.stockprediction.ui.views.interfaces.SearchView;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
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


    @Inject
    SearchPresenter searchPresenter;
    ProgressDialog progressDialog;
    ArrayAdapter<String> adapter;


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

    @Override
    public void renderPlot(String title, PlotData plotData) {

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        graph.clear();
        graph.setTitle(title);
        Number[] domainLabels = plotData.getyCoords().toArray(new Number[plotData.getyCoords().size()]);
        XYSeries series1 = new SimpleXYSeries(
                plotData.getxCoords(), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Strike");
        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        graph.addSeries(series1, series1Format);
        graph.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });
        graph.redraw();
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

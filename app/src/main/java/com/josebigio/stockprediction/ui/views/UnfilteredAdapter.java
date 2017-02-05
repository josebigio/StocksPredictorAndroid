package com.josebigio.stockprediction.ui.views;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <h1>UnfilteredAdapter</h1>
 */
public class UnfilteredAdapter<T> extends ArrayAdapter<T> {
    private Filter filter = new NoFilter();
    private List<T> items = new ArrayList<>();
    Object lock = new Object();


    public UnfilteredAdapter(Context context, int resource) {
        super(context, resource);
    }

    public UnfilteredAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public UnfilteredAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
        items = Arrays.asList(objects);
    }

    public UnfilteredAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
        items = Arrays.asList(objects);

    }

    public UnfilteredAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        items = objects;
    }

    public UnfilteredAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
        items = objects;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public void clear() {
        super.clear();
        items.clear();
    }

    @Override
    public void addAll(Collection<? extends T> collection) {
        super.addAll(collection);
        this.items.addAll(collection);
    }

    private class NoFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence arg0) {
            FilterResults result = new FilterResults();
            result.values = items;
            result.count = items.size();
            return result;
        }

        @Override
        protected void publishResults(CharSequence arg0, FilterResults arg1) {
            notifyDataSetChanged();
        }
    }


}

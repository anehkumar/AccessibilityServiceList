package com.accessbilitycustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AccessibilityAdapter extends BaseAdapter {

    ArrayList<LocalObject> _data;
    Context _c;
    ViewHar viewHar;

    public AccessibilityAdapter(ArrayList<LocalObject> data, Context context) {
        _data = data;
        _c = context;
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.service_access, null);
        } else {
            view = convertView;
        }

        viewHar = new ViewHar();
        viewHar.appName = (TextView) view.findViewById(R.id.name);
        viewHar.status = (TextView) view.findViewById(R.id.status);

        LocalObject object = (LocalObject) _data.get(position);
        viewHar.appName.setText(object.getApplicationName());
        viewHar.status.setText(object.getApplicationStatus());
        return view;
    }

    static class ViewHar {
        TextView appName, status;
    }
}

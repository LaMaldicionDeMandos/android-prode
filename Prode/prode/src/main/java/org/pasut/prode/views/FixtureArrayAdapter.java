package org.pasut.prode.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.pasut.prode.R;
import org.pasut.prode.entities.Fixture;

import java.util.List;

/**
 * Created by marcelo on 02/05/14.
 */
public class FixtureArrayAdapter extends ArrayAdapter<Fixture> {
    public FixtureArrayAdapter(Context context, List<Fixture> fixtures) {
        super(context, android.R.layout.simple_list_item_1, fixtures);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fixture fixture = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_fixtures, parent, false);
        }
        TextView header = (TextView)convertView.findViewById(R.id.header);
        TextView detail = (TextView)convertView.findViewById(R.id.detail);
        header.setText(fixture.getName());
        detail.setText(fixture.getDescription());
        return convertView;
    }
}

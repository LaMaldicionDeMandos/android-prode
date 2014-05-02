package org.pasut.prode.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import org.pasut.prode.R;
import org.pasut.prode.entities.Fixture;
import org.pasut.prode.services.FixtureService;
import org.pasut.prode.services.PersisterService;
import org.pasut.prode.views.FixtureArrayAdapter;

import java.util.Calendar;
import java.util.List;

import roboguice.fragment.RoboFragment;

public class FixtureFragment extends RoboFragment implements AbsListView.OnItemClickListener {
    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter<Fixture> adapter;

    private List<Fixture> fixtures = Lists.newArrayList();

    @Inject
    private FixtureService fixtureService;

    // TODO: Rename and change types of parameters
    public static FixtureFragment newInstance() {
        FixtureFragment fragment = new FixtureFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FixtureFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Change Adapter to display your content
        adapter = new FixtureArrayAdapter(getActivity(), fixtures);

        fixtureService.findAvailableFixtures(Calendar.getInstance().getTime(), new PersisterService.FindCallback<List<Fixture>>() {
            @Override
            public void onFound(List<Fixture> fixtures) {
                FixtureFragment.this.fixtures.clear();
                FixtureFragment.this.fixtures.addAll(fixtures);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixture, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(adapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        setEmptyText("LISTA DE FEATURES");

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }
}

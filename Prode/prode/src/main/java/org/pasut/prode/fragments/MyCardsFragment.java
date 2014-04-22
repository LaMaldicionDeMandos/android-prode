package org.pasut.prode.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pasut.prode.R;

import roboguice.fragment.RoboFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link org.pasut.prode.fragments.MyCardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MyCardsFragment extends RoboFragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CurrentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCardsFragment newInstance() {
        MyCardsFragment fragment = new MyCardsFragment();
        return fragment;
    }
    public MyCardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cards, container, false);
    }
}

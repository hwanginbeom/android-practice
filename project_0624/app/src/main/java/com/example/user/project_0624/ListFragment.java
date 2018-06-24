package com.example.user.project_0624;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    private ListView restaurantListView;
    private ListAdapter adapter;
    private List<Restaurant> restaurantList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private ArrayAdapter nationAdapter;
    private Spinner nationSpinner;
    private ArrayAdapter tagAdapter;
    private Spinner tagSpinner;
    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        restaurantListView = (ListView) getView().findViewById(R.id.list);
        restaurantList = new ArrayList<Restaurant>();
        adapter = new ListAdapter(getContext().getApplicationContext(), restaurantList);
        restaurantListView.setAdapter(adapter);

        tagSpinner = (Spinner) getView().findViewById(R.id.tagSpinner);
        tagAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.tag, android.R.layout.simple_dropdown_item_1line);
        tagSpinner.setAdapter(tagAdapter);
        nationSpinner = (Spinner) getView().findViewById(R.id.nationSpinner);
        tagAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.nation, android.R.layout.simple_dropdown_item_1line);
        tagSpinner.setAdapter(nationAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

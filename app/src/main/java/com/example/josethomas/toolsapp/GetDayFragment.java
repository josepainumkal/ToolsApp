package com.example.josethomas.toolsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GetDayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GetDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetDayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GetDayFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
  /*  public static GetDayFragment newInstance(String param1, String param2) {
        GetDayFragment fragment = new GetDayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

     public static GetDayFragment newInstance() {
        GetDayFragment fragment = new GetDayFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_get_day, container, false);

        final String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        final String[] days = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        final TextView resultText = (TextView)v.findViewById(R.id.resultantDay0);

        final Spinner monthSpinner = (Spinner)v.findViewById(R.id.month_spinner);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        final Spinner daySpinner = (Spinner)v.findViewById(R.id.day_spinner);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        final Spinner yearSpinner = (Spinner)v.findViewById(R.id.year_spinner);
        ArrayList<String> years = new ArrayList<String>();
        for(int i=2050; i>= 1500; i--){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        Button dayBtn = (Button)v.findViewById(R.id.daySubmit);
        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = daySpinner.getSelectedItem().toString();
                String month = monthSpinner.getSelectedItem().toString();
                String year = yearSpinner.getSelectedItem().toString();

                String monthNo = "00";
                switch (month){
                    case "January": monthNo = "01";break;
                    case "February": monthNo = "02";break;
                    case "March": monthNo = "03";break;
                    case "April": monthNo = "04";break;
                    case "May": monthNo = "05";break;
                    case "June": monthNo = "06";break;
                    case "July": monthNo = "07";break;
                    case "August": monthNo = "08";break;
                    case "September": monthNo = "09";break;
                    case "October": monthNo = "10";break;
                    case "November": monthNo = "11";break;
                    case "December": monthNo = "12";break;
                }

                String enteredDate = day+"/"+monthNo+"/"+year;

                try{
                    SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date myDate = newDateFormat.parse(enteredDate);
                    newDateFormat.applyPattern("EEEE d MMM yyyy");
                    String rDate = newDateFormat.format(myDate);

                    resultText.setText(rDate);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onDayFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDayFragmentInteraction(Uri uri);
    }
}

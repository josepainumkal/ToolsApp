package com.example.josethomas.toolsapp;

import android.app.Service;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BMIFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BMIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText heightTxtView = null;
    private EditText weightTxtView = null;
    private Button submitButton = null;
    private Button clearButton = null;
    private ImageView imgView = null;
    private TextView resultTxt = null;



    public BMIFragment() {
        // Required empty public constructor
    }


     public static BMIFragment newInstance() {
        BMIFragment fragment = new BMIFragment();
        return fragment;
    }


   /* public static BMIFragment newInstance(String param1, String param2) {
        BMIFragment fragment = new BMIFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
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

        View v = inflater.inflate(R.layout.fragment_bmi, container, false);

        heightTxtView = (EditText)v.findViewById(R.id.height);
        weightTxtView = (EditText)v.findViewById(R.id.weight);
        imgView = (ImageView)v.findViewById(R.id.imageView);
        resultTxt = (TextView)v.findViewById(R.id.resultText);
        submitButton  = (Button)v.findViewById(R.id.bmiSubmit);
        clearButton = (Button)v.findViewById(R.id.bmiClear);

        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                 public void onClick(View v) {
                    bmiCalculate(v);
                  }
          });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmiClear(v);
            }
        });

        resultTxt.setText("");
        imgView.setImageResource(android.R.color.transparent);

                // Inflate the layout for this fragment
        return v;
    }

    public void bmiClear(View v){
        heightTxtView.setText("");
        weightTxtView.setText("");
        resultTxt.setText("");
        imgView.setImageResource(android.R.color.transparent);
    }

    public void bmiCalculate(View v){

        resultTxt.setText("");
        imgView.setImageResource(android.R.color.transparent);

        //hiding the software keyboard
        View view = getActivity().getCurrentFocus();
        if(view!=null){
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

        RelativeLayout vwParentRow = (RelativeLayout)v.getParent();
        heightTxtView = (EditText) vwParentRow.getChildAt(1);
        weightTxtView = (EditText) vwParentRow.getChildAt(3);

        Log.d("HAHAHAHAHAHA", heightTxtView.toString());
        Log.d("Height", heightTxtView.getText().toString());
        Log.d("Weight", weightTxtView.getText().toString());

        if(heightTxtView.getText().toString().isEmpty() || weightTxtView.getText().toString().isEmpty()){
            Toast.makeText(getActivity(),
                           getActivity().getString(R.string.valError),
                           Toast.LENGTH_LONG).show();
        }else{
            float height = Float.parseFloat(heightTxtView.getText().toString());
            float weight = Float.parseFloat(weightTxtView.getText().toString());
           // height = height * Float.parseFloat("0.01");
            double heightInMeter = height * 0.01;
            double heightInMeterSquare = heightInMeter * heightInMeter;
            double bmi = (weight / heightInMeterSquare);
            String resultText = null;

            if (bmi < 15) {
                resultText = "Very Severely Under Weight";
                imgView.setImageResource(R.drawable.vsuw);
            } else if (bmi >= 15 && bmi < 16) {
                resultText = "Severely Under Weight";
                imgView.setImageResource(R.drawable.suv);
            } else if (bmi >= 16 && bmi < 18.5) {
                resultText = "Under Weight";
                imgView.setImageResource(R.drawable.uw);
            } else if (bmi >= 18.5 && bmi <= 25) {
                resultText = "Normal (Healthy Weight)";
                imgView.setImageResource(R.drawable.healthy);
            } else if (bmi > 25 && bmi < 30) {
                resultText = "Over Weight";
                imgView.setImageResource(R.drawable.o_weight);
            } else if (bmi >= 30 && bmi < 35) {
                resultText = "Obese Class I (Moderately obese)";
                imgView.setImageResource(R.drawable.o_class1);
            } else if (bmi >= 35 && bmi <= 40) {
                resultText = "Obese Class II (Severely obese)";
                imgView.setImageResource(R.drawable.o_class2);
            } else if (bmi > 40) {
                resultText = "Obese Class III (Very severely obese)";
                imgView.setImageResource(R.drawable.o_class3);
            }else if (bmi > 50) {
                resultText = "Obese Class III (Very severely obese)";
                imgView.setImageResource(R.drawable.o_class4);
            }
            resultTxt.setText(resultText);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
     /**/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}

package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.Student;
import edu.gatech.seclass.sdpvocabquiz.database.Student.SeniorityLevel;

public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnRegisterListener mListener;
    Button registerButton;
    TextInputLayout email, major, username;
    Spinner spinner;
    private TextInputEditText usernameEditText, emailEditText, majorEditText;


    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        spinner = view.findViewById(R.id.senioritySpinner);
        email = view.findViewById(R.id.emailTextInput);
        username = view.findViewById(R.id.userTextInput);
        major = view.findViewById(R.id.majorTextInput);

        usernameEditText = view.findViewById(R.id.userTextEdit);
        emailEditText = view.findViewById(R.id.emailTextEdit);
        majorEditText = view.findViewById(R.id.majorTextEdit);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                registerButton.setEnabled(isRegisterButtonEnabled());
            }
        };

        usernameEditText.addTextChangedListener(watcher);
        emailEditText.addTextChangedListener(watcher);
        majorEditText.addTextChangedListener(watcher);

        registerButton = view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String majorString = major.getEditText().getText().toString();
                String usernameString = username.getEditText().getText().toString();
                String emailString = email.getEditText().getText().toString();
                if(!isEmailValid(emailString)) {
                    Toast.makeText(getActivity(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                String seniorityString = spinner.getSelectedItem().toString().toUpperCase();
                if(isUsernameAvailable(usernameString)) {
                    Student student = new Student(usernameString, emailString, majorString, SeniorityLevel.valueOf(seniorityString));
                    if (mListener != null) {
                        mListener.onRegistered(student);
                    }
                } else {
                    Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();

                }
            }
        });

        registerButton.setEnabled(false);

        return view;
    }

    private boolean isRegisterButtonEnabled() {
        return (major.getEditText().getText().toString().trim().length() != 0) &&
                (username.getEditText().getText().toString().trim().length() != 0) &&
                (email.getEditText().getText().toString().trim().length() != 0);
    }

    private boolean isUsernameAvailable(String username){
        return !((LoginActivity)getActivity()).doesUserExist(username);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterListener) {
            mListener = (OnRegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginListener");
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
    public interface OnRegisterListener {
        void onRegistered(Student student);
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

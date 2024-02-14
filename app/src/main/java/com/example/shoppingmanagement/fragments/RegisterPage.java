package com.example.shoppingmanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingmanagement.R;
import com.example.shoppingmanagement.activitys.user;
import com.example.shoppingmanagement.activitys.userData;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterPage.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterPage newInstance(String param1, String param2) {
        RegisterPage fragment = new RegisterPage();
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
        View view  = inflater.inflate(R.layout.fragment_register_page, container, false);
        Button registerButton = view.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = view.findViewById(R.id.emailEditText);
                EditText passwordEditText = view.findViewById(R.id.passwordEditText);
                EditText phoneEditText = view.findViewById(R.id.phoneEditText);

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                // Check if the email already exists
                boolean emailExists = false;
                for (user u : userData.getUserList()) {
                    if (u.getEmail().equals(email)) {
                        emailExists = true;
                        break;
                    }
                }
                if (emailExists) {
                    Log.d("User", "Email " + email + " is already registered.");
                    Toast.makeText(getActivity(), "Email " + email + " is already registered.", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a new user object
                    user newUser = new user(email, password, phone);
                    userData.addUser(newUser);

                    for (user u : userData.getUserList()) {
                        Log.d("User", "Email: " + u.getEmail() + ", Password: " + u.getPassword() + ", Phone: " + u.getPhone());

                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    Navigation.findNavController(view).navigate(R.id.action_registerPage_to_mainPage,bundle);
                }

            }

        });

        return view;
    }
}
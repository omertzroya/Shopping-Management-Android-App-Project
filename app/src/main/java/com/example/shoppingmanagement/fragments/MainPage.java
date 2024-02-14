package com.example.shoppingmanagement.fragments;

import static android.content.Intent.getIntent;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingmanagement.R;
import com.example.shoppingmanagement.activitys.Product;
import com.example.shoppingmanagement.activitys.ProductAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPage.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPage newInstance(String param1, String param2) {
        MainPage fragment = new MainPage();
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
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        String email = getArguments().getString("email");

        TextView textView = view.findViewById(R.id.helloTextView);
        textView.setText( "שלום, " + email);



        // Initialize the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a list of products (you can replace this with your actual list)
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("מלפפון", 10));
        products.add(new Product("עגבנייה", 12));
        products.add(new Product("עגבנייה", 12));
        // Create an adapter and set it to the RecyclerView
        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog to prompt the user for the product details
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Product");

                // Set up the layout for the dialog
                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_product, null);
                EditText productNameEditText = dialogView.findViewById(R.id.productNameEditText);
                EditText productPriceEditText = dialogView.findViewById(R.id.productPriceEditText);
                builder.setView(dialogView);

                // Set up the buttons for OK and Cancel

                   builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                       @Override

                       public void onClick(DialogInterface dialog, int which) {
                           // Get the entered name and price
                           String productName = productNameEditText.getText().toString();
                           double productPrice = Double.parseDouble(productPriceEditText.getText().toString());

                           // Add the new product to the dataset
                           products.add(new Product(productName, productPrice));
                           // Notify the adapter about the change
                           adapter.notifyItemInserted(products.size() - 1);
                       }
                   });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Dismiss the dialog if canceled
                    }
                });

                // Show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        return view;

    }
}
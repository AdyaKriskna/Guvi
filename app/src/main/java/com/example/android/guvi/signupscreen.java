package com.example.android.guvi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signupscreen extends AppCompatActivity {
    EditText organization_name,address,pincode,email,office_number,mobile_number,login_name,password,confim_password;
    Button oregister;
    DatabaseReference databaseReference;
    String spinner_name=null;
    int users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen);
        oregister=(Button)findViewById(R.id.oregister);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        init();
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_name=String.valueOf(spinner.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        AutoCompleteTextView auto=(AutoCompleteTextView)findViewById(R.id.autocomplete);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_list_item_1);
        auto.setAdapter(adapter1);
        final String autocompleteText=auto.getText().toString();
        oregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        users = Integer.parseInt(String.valueOf(dataSnapshot.child("0").getValue()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//                if (organization_name.getText().toString() != null && spinner_name.toString() != null&& address.getText().toString() != null && autocompleteText.toString()!=null && pincode.getText().toString() && email.getText().toString() && office_number.getText().toString() && mobile_number.getText().toString() != "" && login_name.getText().toString() != "" && password.getText().toString() != "" && confim_password.getText().toString() != "" && password.getText().toString() == confim_password.getText().toString())
//                {
                    databaseReference.child("0").setValue(String.valueOf(users + 1));
                    databaseReference.child(String.valueOf(users + 1)).child("OrgnizatonName").setValue(organization_name.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("Category").setValue(spinner_name.toString());
                    databaseReference.child(String.valueOf(users + 1)).child("Address").setValue(address.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("Country").setValue(autocompleteText.toString());
                    databaseReference.child(String.valueOf(users + 1)).child("Pincode").setValue(pincode.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("Email").setValue(email.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("OfficeNumber").setValue(office_number.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("MobileNumber").setValue(mobile_number.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("LoginName").setValue(login_name.getText().toString());
                    databaseReference.child(String.valueOf(users + 1)).child("Password").setValue(password.getText().toString());
                    Toast.makeText(signupscreen.this, "Sucess", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
    private void init() {
        organization_name=(EditText)findViewById(R.id.organization_name);
        address=(EditText)findViewById(R.id.address);
        pincode=(EditText)findViewById(R.id.pincode);
        email=(EditText)findViewById(R.id.email);
        office_number=(EditText)findViewById(R.id.office_number);
        mobile_number=(EditText)findViewById(R.id.mobile_number);
        login_name=(EditText)findViewById(R.id.login_name);
        password=(EditText)findViewById(R.id.password);
        confim_password=(EditText)findViewById(R.id.confirm_password);
    }
}
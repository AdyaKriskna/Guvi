package com.example.android.guvi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signupscreeno extends AppCompatActivity {
    EditText user_name,date_of_birth,address,pincode,email,mobile_number,login_name,password,confim_password;
    Button pregister;
    DatabaseReference pdatabaseReference;
    int users;
    protected void onCreate(Bundle savedInstanceState) {
        pdatabaseReference= FirebaseDatabase.getInstance().getReference();
        init();
        AutoCompleteTextView auto=(AutoCompleteTextView)findViewById(R.id.autocomplete);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_list_item_1);
        auto.setAdapter(adapter1);
        final String autocompleteText=auto.getText().toString();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signupscreeno);
        AutoCompleteTextView auto1=(AutoCompleteTextView)findViewById(R.id.autocomplet);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_list_item_1);
        auto.setAdapter(adapter2);
            pregister=(Button)findViewById(R.id.pregister);
            pregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(signupscreeno.this,MainActivity.class);
                    startActivity(intent);
                }
            });
        pregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdatabaseReference.addValueEventListener(new ValueEventListener() {
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
                pdatabaseReference.child("0").setValue(String.valueOf(users + 1));
                pdatabaseReference.child(String.valueOf(users + 1)).child("UserName").setValue(user_name.getText().toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("Address").setValue(address.getText().toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("Country").setValue(autocompleteText.toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("Pincode").setValue(pincode.getText().toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("Email").setValue(email.getText().toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("MobleNumber").setValue(mobile_number.getText().toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("LoginName").setValue(login_name.getText().toString());
                pdatabaseReference.child(String.valueOf(users + 1)).child("Password").setValue(password.getText().toString());
                Toast.makeText(signupscreeno.this, "Sucess", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
    private void init() {
        user_name=(EditText)findViewById(R.id.user_name);
        date_of_birth=(EditText)findViewById(R.id.date);
        pincode=(EditText)findViewById(R.id.ppincode);
        email=(EditText)findViewById(R.id.pemail);
        mobile_number=(EditText)findViewById(R.id.pmobile_number);
        password=(EditText)findViewById(R.id.ppassword);
        confim_password=(EditText)findViewById(R.id.pcon_password);
        address=(EditText)findViewById(R.id.paddress);
    }
}
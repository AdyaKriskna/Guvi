package com.example.android.guvi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference loginDatabaseReference;
    EditText editText,editText2;
    int users;
    String login_email,login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginDatabaseReference= FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        TextView t=(TextView)findViewById(R.id.textView3);
        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,choose.class);
                startActivity(i);
            }
        });
        Button t1=(Button)findViewById(R.id.button);
        t1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        users=Integer.parseInt(String.valueOf(dataSnapshot.child("0").getValue()));
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                for (int i=1;i<=users;i++){
                    final int finalI = i;
                    loginDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            login_email= (String) dataSnapshot.child(String.valueOf(finalI)).child("Email").getValue();
                            login_password=(String)dataSnapshot.child(String.valueOf(finalI)).child("Password").getValue();
                            if(login_email.equals(editText)&&login_password.equals(editText2)){
                                Intent i=new Intent(MainActivity.this,apporiginal.class);
                                startActivity(i);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), (CharSequence) databaseError,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
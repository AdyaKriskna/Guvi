package com.example.android.guvi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class choose extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        TextView t=(TextView)findViewById(R.id.button2);
        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(choose.this,signupscreeno.class);
                startActivity(i);
            }
        });
        TextView t1=(TextView)findViewById(R.id.button3);
        t1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(choose.this,signupscreen.class);
                startActivity(ii);
            }
        });


    }

}
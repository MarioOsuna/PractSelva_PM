package com.example.practselva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridLayout g;
    Button buttonCrear;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        g=findViewById(R.id.GridLayout);
        buttonCrear=findViewById(R.id.buttonCrear);
        editText=findViewById(R.id.editTextNum);




    }
}

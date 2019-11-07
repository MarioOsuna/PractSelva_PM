package com.example.practselva;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    GridLayout g;
    Button buttonCrear;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        g = findViewById(R.id.GridLayout);
        buttonCrear = findViewById(R.id.buttonCrear);
        editText = findViewById(R.id.editTextNum);
        /*final String[] animales = {String.valueOf(R.string.leon), String.valueOf(R.string.aguila), String.valueOf(R.string.chimpance),
                String.valueOf(R.string.delfin), String.valueOf(R.string.elefante), String.valueOf(R.string.foca),
                String.valueOf(R.string.Gato), String.valueOf(R.string.oso), String.valueOf(R.string.perro), String.valueOf(R.string.tigre)};*/

        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(String.valueOf(editText.getText()));
                if (n == 0) {
                    Toast.makeText(MainActivity.this, "Debe introducir un número del 1 al 10", Toast.LENGTH_SHORT).show();
                } else {
                    final Spinner spinnerAnimal[] = new Spinner[n];
                    final Spinner spinnerTiempo[] = new Spinner[n];
                    final Button buttonPlay[] = new Button[n];

                    final ArrayAdapter<CharSequence> adapterAnimales = ArrayAdapter.createFromResource(MainActivity.this, R.array.animales, android.R.layout.simple_spinner_item);
                    adapterAnimales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    ArrayAdapter<CharSequence> adapterTiempo = ArrayAdapter.createFromResource(MainActivity.this, R.array.segundos, android.R.layout.simple_spinner_item);
                    adapterTiempo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    for (int i = 0; i < n; i++) {

                        spinnerAnimal[i] = new Spinner(MainActivity.this);
                        spinnerAnimal[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        spinnerAnimal[i].setAdapter(adapterAnimales);
                        spinnerAnimal[i].setId(View.generateViewId());
                       // spinnerAnimal[i].setOnItemClickListener(MainActivity.this);

                        g.addView(spinnerAnimal[i]);


                        spinnerTiempo[i] = new Spinner(MainActivity.this);
                        spinnerTiempo[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        spinnerTiempo[i].setAdapter(adapterTiempo);
                        spinnerTiempo[i].setId(View.generateViewId());
                        g.addView(spinnerTiempo[i]);

                        buttonPlay[i] = new Button(MainActivity.this);
                        buttonPlay[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        buttonPlay[i].setText("");
                        buttonPlay[i].setBackgroundResource(android.R.drawable.ic_media_play);
                        buttonPlay[i].setId(View.generateViewId());

                        final int finalI = i;

                        buttonPlay[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onItemSelected(adapterAnimales,v, finalI,spinnerAnimal[finalI].getId());
                            }
                        });
                        g.addView(buttonPlay[i]);
                    }

                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String texto=parent.getItemAtPosition(position).toString();

            Toast.makeText(parent.getContext(),texto,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

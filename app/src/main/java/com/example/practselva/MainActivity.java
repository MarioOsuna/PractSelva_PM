package com.example.practselva;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridLayout g;
    Button buttonCrear;
    EditText editText;
    Thread hilo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        g = findViewById(R.id.GridLayout);
        buttonCrear = findViewById(R.id.buttonCrear);
        editText = findViewById(R.id.editTextNum);


        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debe introducir un número del 1 al 10", Toast.LENGTH_SHORT).show();
                } else {

                    int n = Integer.parseInt(String.valueOf(editText.getText()));

                    final int[] Flag = {n};
                    if (n >= 1 && n <= 10) {
                        final Spinner spinnerAnimal[] = new Spinner[n];
                        final Spinner spinnerTiempo[] = new Spinner[n];
                        final Button buttonPlay[] = new Button[n];
                        final MediaPlayer[] mediaPlayer = new MediaPlayer[n];


                        final ArrayAdapter<CharSequence> adapterAnimales = ArrayAdapter.createFromResource(MainActivity.this, R.array.animales, android.R.layout.simple_spinner_item);
                        adapterAnimales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        ArrayAdapter<CharSequence> adapterTiempo = ArrayAdapter.createFromResource(MainActivity.this, R.array.segundos, android.R.layout.simple_spinner_item);
                        adapterTiempo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        for (int i = 0; i < n; i++) {

                            spinnerAnimal[i] = new Spinner(MainActivity.this);
                            spinnerAnimal[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            spinnerAnimal[i].setAdapter(adapterAnimales);
                            spinnerAnimal[i].setId(View.generateViewId());


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
                                    Flag[0]--;

                                    String cadena1 = String.valueOf(spinnerAnimal[finalI].getSelectedItem());
                                    String cadena2 = String.valueOf(spinnerTiempo[finalI].getSelectedItem());
                                    int num = 0;
                                    switch (cadena2) {
                                        case "0 seg":
                                            num = 0;
                                            break;
                                        case "5 seg":
                                            num = 5000;
                                            break;
                                        case "10 seg":
                                            num = 10000;
                                            break;
                                        case "15 seg":
                                            num = 15000;
                                            break;
                                        case "20 seg":
                                            num = 20000;
                                            break;
                                        case "25 seg":
                                            num = 25000;
                                            break;
                                        case "30 seg":
                                            num = 30000;
                                            break;
                                    }

                                    switch (cadena1) {
                                        case "León":
                                        case "Lion":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.lion);


                                            break;
                                        case "Elefante":
                                        case "Elephant":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.elephant);

                                            break;
                                        case "Águila":
                                        case "Eagle":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.eagle);

                                            break;
                                        case "Tigre":
                                        case "Tiger":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.bengal_tiger);

                                            break;
                                        case "Foca":
                                        case "Sea Lion":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.sea_lion);

                                            break;
                                        case "Gato":
                                        case "Cat":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.domestic_cat);

                                            break;
                                        case "Chimpance":
                                        case "Chimpanzee":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.chimp);

                                            break;
                                        case "Perro":
                                        case "Dog":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.dogbark);

                                            break;
                                        case "Delfín":
                                        case "Dolphins":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.dolphin);

                                            break;
                                        case "Oso":
                                        case "Bear":
                                            mediaPlayer[finalI] = MediaPlayer.create(MainActivity.this, R.raw.bear);
                                            break;
                                    }
                                    if (hilo == null) {
                                        final int finalNum = num;
                                        hilo = new Thread() {

                                            @Override
                                            public void run() {
                                                try {
                                                    sleep(finalNum);
                                                    mediaPlayer[finalI].start();


                                                    hilo = null;

                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };


                                        hilo.start();
                                    }

                                    /*try {
                                        Thread.sleep(num);
                                        mediaPlayer[finalI].start();


                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }*

                                    spinnerAnimal[finalI].setEnabled(false);
                                    spinnerTiempo[finalI].setEnabled(false);
                                    buttonPlay[finalI].setEnabled(false);*/


                                    spinnerAnimal[finalI].setVisibility(View.GONE);
                                    spinnerTiempo[finalI].setVisibility(View.GONE);
                                    buttonPlay[finalI].setVisibility(View.GONE);

                                    if (Flag[0] == 0) {
                                        editText.setEnabled(true);
                                        buttonCrear.setEnabled(true);
                                    }

                                }
                            });
                            g.addView(buttonPlay[i]);

                            editText.setEnabled(false);
                            buttonCrear.setEnabled(false);


                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Debe introducir un número del 1 al 10", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String texto = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}

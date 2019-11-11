package com.example.practselva;


import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridLayout g;
    Button buttonCrear;
    EditText editText;

    Thread hilo = null;
    // MiCronometro crono = null;

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

                    final boolean[] activo = {false};

                    final int[] Flag = {n};

                    if (n >= 1 && n <= 10) {
                        final Spinner spinnerAnimal[] = new Spinner[n];
                        final EditText editTextTiempo[] = new EditText[n];
                        final Button buttonPlay[] = new Button[n];
                        final MediaPlayer[] mediaPlayer = new MediaPlayer[n];


                        final ArrayAdapter<CharSequence> adapterAnimales = ArrayAdapter.createFromResource(MainActivity.this, R.array.animales, android.R.layout.simple_spinner_item);
                        adapterAnimales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        for (int i = 0; i < n; i++) {

                            spinnerAnimal[i] = new Spinner(MainActivity.this);
                            spinnerAnimal[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            spinnerAnimal[i].setAdapter(adapterAnimales);
                            spinnerAnimal[i].setId(View.generateViewId());


                            g.addView(spinnerAnimal[i]);


                            editTextTiempo[i] = new EditText(MainActivity.this);
                            editTextTiempo[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            editTextTiempo[i].setText("0");
                            editTextTiempo[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                            editTextTiempo[i].setId(View.generateViewId());

                            g.addView(editTextTiempo[i]);


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
                                    activo[0] = true;

                                    String cadena1 = String.valueOf(spinnerAnimal[finalI].getSelectedItem());

                                    String cadena2 = String.valueOf(editTextTiempo[finalI].getText());

                                    int num = Integer.parseInt(cadena2) * 1000;

                                    if (num > 30000) {
                                        Toast.makeText(MainActivity.this, "Introduzca 30 segundos o menos", Toast.LENGTH_SHORT).show();

                                    } else {


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
                                        final int finalNum = num;
                                        if (hilo == null) {


                                            hilo = new Thread() {

                                                @Override
                                                public void run() {
                                                    try {

                                                        Thread.sleep(finalNum);

                                                        mediaPlayer[finalI].start();

                                                        hilo = null;

                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            };


                                            hilo.start();
                                        }


                                   /* spinnerAnimal[finalI].setEnabled(false);
                                    editTextTiempo[finalI].setEnabled(false);
                                    buttonPlay[finalI].setEnabled(false);*/


                                        spinnerAnimal[finalI].setVisibility(View.GONE);
                                        editTextTiempo[finalI].setVisibility(View.GONE);
                                        buttonPlay[finalI].setVisibility(View.GONE);

                                        if (Flag[0] == 0) {
                                            editText.setEnabled(true);
                                            buttonCrear.setEnabled(true);
                                        }
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
    /*clase mi cronómetro

    public class MiCronometro extends AsyncTask<String,String,String> {

        int contador=0;

        @Override
        protected void onProgressUpdate(String... values) {
            editTextTiempo[finalI1].setText(values[0]);
        }

        @Override
        protected String doInBackground(String... strings) {
            int contador=0;

            while(true){

                while(activo[0]){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contador++;
                    int segundos=contador%60;
                    int minutos=contador/60;
                    String texto= minutos+":"+segundos;
                    publishProgress(texto);
                }
            }
        }
    }

    fin clase*/

}

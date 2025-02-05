package com.conversor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
//TEsting
//Testestteso
import com.conversor.activity_conversor;
import com.example.conversor.R;

public class MainActivity extends AppCompatActivity{

    // 1. declaración de variables

    private Spinner listaValores;
    public static final String EXTRA_MESSAGE = "com.example.conversor.MESSAGE";
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            //2. Data binding: enlace de datos

            listaValores = findViewById(R.id.spinner);

            //3. Adaptador (datos) del Spinner

            String[] unidades = {"Peso","Volumen","Distancia", "Temperatura", "Capacidad Disco Duro"};

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>              (this,android.R.layout.simple_spinner_item, unidades);

            listaValores.setAdapter(adaptador);

            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                CharSequence spinnerValue = (CharSequence) listaValores.getSelectedItem();
                Intent intent = new Intent(MainActivity.this, activity_conversor.class);
                intent.putExtra("spinnerselection", spinnerValue.toString());
                startActivity(intent);
                    Log.d("BOTON", "El usuario a clickado el boton de comenzar");
                }
            });
        }

}
package com.conversor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.conversor.R;

public class activity_conversor extends AppCompatActivity{

    //DECLARACION DE VARIABLES

    private Spinner listaValores;
    private Spinner listaValores2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conversor);

        //ENLAZADO DE DATOS

        listaValores = findViewById(R.id.spinner);
        listaValores2 = findViewById(R.id.spinner3);


        //ADAPTAR LOS DATOS AL SPINNER
        String[] enterValue = new String[0];
        double[] conversorValue = new double[0];




        Button buttonback = findViewById(R.id.buttonBack);
        //"Peso","Volumen","Distancia", "Temperatura", "Capacidad Disco Duro"
        String selectedUnity = getIntent().getStringExtra("spinnerselection");
        Log.d("spinnerselection", selectedUnity);
        switch (selectedUnity){
            case "Peso":
                enterValue = new String[]{"μg","mg","g","kg","t"};
                conversorValue = new double[]{1000000, 1000, 1, 0.001, 0.000001};
                break;
            case "Volumen":
                enterValue = new String[]{"mL","L","m3"};
                conversorValue = new double[]{1000, 1, 0.001};
                break;

            case "Distancia":
                enterValue = new String[]{"mm","cm","m","km"};
                conversorValue = new double[]{1000, 100, 1, 0.001};
                break;

            case "Temperatura":
                enterValue = new String[]{"Celsius","Farenheit","Kelvin"};
                conversorValue = new double[]{1, 33.8, 274.15};
                break;

            case "Capacidad Disco Duro":
                enterValue = new String[]{"b","B","KB", "MB","GB"};
                conversorValue = new double[]{8000000, 1000000, 1000, 1, 0.001};
                break;
            default:
                System.exit(0);
        }
        TextView tituloConversor = (TextView)findViewById(R.id.textView);
        tituloConversor.setText("Conversor de "+selectedUnity);
        if (selectedUnity == "Temperatura"){
            tituloConversor.setTextSize(10);
        } else if (selectedUnity == "Capacidad Disco Duro") {
            tituloConversor.setTextSize(10);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, enterValue);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, enterValue);
        listaValores.setAdapter(adaptador);
        listaValores2.setAdapter(adaptador2);

        Button buttonConvert = findViewById(R.id.buttonConvert);
        EditText numberInput = findViewById(R.id.numberInput);

        buttonConvert.setOnClickListener(view -> {
            double numberinputed = Double.parseDouble(numberInput.getText().toString());
            Log.d("NUMBERINPUT", String.valueOf(numberinputed));

        });

        buttonback.setOnClickListener(v -> {
            Intent intent = new Intent(activity_conversor.this, MainActivity.class);
            startActivity(intent);
            Log.d("BOTON", "El usuario a clickado el boton de atrás");
        });
        double[] finalConversorValue = conversorValue;
        String[] finalEnterValue = enterValue;

        buttonConvert.setOnClickListener(view -> {
            double numberInputed = Double.parseDouble(numberInput.getText().toString());
            int selectedSpinner1 = listaValores.getSelectedItemPosition();
            int selectedSpinner2 = listaValores2.getSelectedItemPosition();
            double d_resultado = (finalConversorValue[selectedSpinner2] / finalConversorValue[selectedSpinner1] * numberInputed);
            String resultado = Double.toString(d_resultado);
            //Log.d("CONVERSION", String.format("%.2f %s = %.2f %s", numberInputed, finalEnterValue[selectedSpinner1], resultado));

            Log.d("TESTCONVERSOR", numberInputed + " " + finalEnterValue[selectedSpinner1] + " = " + resultado + " " + finalEnterValue[selectedSpinner2]);
            TextView resultadoVisual = (TextView)findViewById(R.id.textView2);
            resultadoVisual.setText(numberInputed + " " + finalEnterValue[selectedSpinner1] + " = " + resultado + " " + finalEnterValue[selectedSpinner2]);
        });
        /*
        //CONVERTIR (antiguo)
        String[] finalEnterValue1 = enterValue;
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el valor ingresado y las posiciones seleccionadas en los Spinners
                double numberInputed = Double.parseDouble(numberInput.getText().toString());
                int selectedSpinner1 = listaValores.getSelectedItemPosition();
                int selectedSpinner2 = listaValores2.getSelectedItemPosition();

                double conversionRate = 0;
                // Manejar casos donde se convierte de una unidad más pequeña a una más grande
                    if (selectedSpinner1 >= selectedSpinner2) {
                        for (int i = 0; i <= Arrays.asList(finalExitValue).indexOf(selectedSpinner2) - Arrays.asList(finalEnterValue).indexOf(selectedSpinner1); i++){
                            // Obtener la tasa de conversión correspondiente
                            conversionRate = finalConversorValue[selectedSpinner1];
                            conversionRate = 1 * conversionRate;
                        }
                    }
                    else if (selectedSpinner1 < selectedSpinner2){
                        for (int i = 0; i <= Arrays.asList(finalEnterValue).indexOf(selectedSpinner1) - Arrays.asList(finalExitValue).indexOf(selectedSpinner2); i++){
                            // Obtener la tasa de conversión correspondiente
                            conversionRate = finalConversorValue[selectedSpinner1];
                            conversionRate = 1 / conversionRate;
                        }
                    }
                    else{
                        Log.d("Conversion", "onClick: Error if CONVERTIR");
                    }
                    
                    // Realizar la conversión
                    double convertedValue = numberInputed * conversionRate;

                    // Obtener la unidad de salida correspondiente
                    String convertedUnit = finalExitValue[selectedSpinner2];

                    // Mostrar el resultado por consola
                    Log.d("Conversion", String.format("%.2f %s = %.2f %s", numberInputed, finalEnterValue[selectedSpinner1], convertedValue, convertedUnit));

            }

        });
        */
    }
}
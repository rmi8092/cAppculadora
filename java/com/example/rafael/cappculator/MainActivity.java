package com.example.rafael.cappculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView zonaDisplay, zonaDisplayPeq;
    Button btnBorrar, btnCambioSigno, btnPorcentaje, btnDividir, btnProducto, btnSumar, btnRestar, btnResultado, btnDecimal;
    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve, btnCero;
    Boolean loperando1, lnuevoOperando, ldecimal,lpositivo;
    String operacion, entrada = "";
    double operando1, operando2, resultado = 0;
    int longitudResultado = 0;
    int tamano = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        btnCambioSigno = (Button) findViewById(R.id.btnCambioSigno);
        btnPorcentaje = (Button) findViewById(R.id.btnPorcentaje);
        btnDividir = (Button) findViewById(R.id.btnDividir);
        btnProducto = (Button) findViewById(R.id.btnProducto);
        btnSumar = (Button) findViewById(R.id.btnSumar);
        btnRestar = (Button) findViewById(R.id.btnRestar);
        btnResultado = (Button) findViewById(R.id.btnResultado);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        btnUno = (Button) findViewById(R.id.btnUno);
        btnDos = (Button) findViewById(R.id.btnDos);
        btnTres = (Button) findViewById(R.id.btnTres);
        btnCuatro = (Button) findViewById(R.id.btnCuatro);
        btnCinco = (Button) findViewById(R.id.btnCinco);
        btnSeis = (Button) findViewById(R.id.btnSeis);
        btnSiete = (Button) findViewById(R.id.btnSiete);
        btnOcho = (Button) findViewById(R.id.btnOcho);
        btnNueve = (Button) findViewById(R.id.btnNueve);
        btnCero = (Button) findViewById(R.id.btnCero);

        btnCero.setOnClickListener(this);
        btnUno.setOnClickListener(this);
        btnDos.setOnClickListener(this);
        btnTres.setOnClickListener(this);
        btnCuatro.setOnClickListener(this);
        btnCinco.setOnClickListener(this);
        btnSeis.setOnClickListener(this);
        btnSiete.setOnClickListener(this);
        btnOcho.setOnClickListener(this);
        btnNueve.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);
        btnCambioSigno.setOnClickListener(this);
        btnPorcentaje.setOnClickListener(this);
        btnDividir.setOnClickListener(this);
        btnProducto.setOnClickListener(this);
        btnSumar.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnResultado.setOnClickListener(this);
        btnDecimal.setOnClickListener(this);

        loperando1 = true;
        ldecimal = false;
        lpositivo = true;
        lnuevoOperando = true;
    }

    private void mostrarResultado(){
        switch (operacion){
            case "+":
                resultado = operando1 + operando2;
                break;
            case "-":
                resultado = operando1 - operando2;
                break;
            case "x":
                resultado = operando1 * operando2;
                break;
            case "/":
                resultado = operando1 / operando2;
                break;
            default:
                //resultado no cambia
                break;
        }
        longitudResultado = Double.toString(resultado).length();
        if(longitudResultado > 7){
            zonaDisplay.setTextSize(60);
        }
        if(longitudResultado > 9){
            zonaDisplay.setTextSize(50);
        }
        zonaDisplay.setText(Double.toString(resultado));
    }

    @Override
    public void onClick(View control) {
        zonaDisplay = (TextView) findViewById(R.id.display);
        zonaDisplayPeq = (TextView) findViewById(R.id.displayPeq);
        entrada = (String) ((Button) control).getText();

        switch (control.getId()) {
            case R.id.btnSumar:
            case R.id.btnRestar:
            case R.id.btnProducto:
            case R.id.btnDividir:
            case R.id.btnResultado:
                tamano = 0;
                lnuevoOperando = true;
                if (loperando1 == false) {
                    operando2 = Double.parseDouble(zonaDisplay.getText().toString());
                    mostrarResultado();
                    zonaDisplayPeq.setText(zonaDisplayPeq.getText() + entrada); //revisar
                    operando1 = resultado;
                    loperando1 = true;
                } else {
                    operando1 = Double.parseDouble(zonaDisplay.getText().toString());
                    zonaDisplayPeq.setText(zonaDisplay.getText().toString() + entrada); //revisar
                    loperando1 = false;
                }
                operacion = entrada;
                ldecimal = false;
                break;
            case R.id.btnBorrar:
                zonaDisplay.setText("0");
                zonaDisplayPeq.setText("");
                zonaDisplay.setTextSize(80);
                lnuevoOperando = true;
                operando1 = operando2 = 0;
                loperando1 = true;
                break;
            case R.id.btnPorcentaje:
                operando2 = Double.parseDouble(zonaDisplay.getText().toString());
                resultado = (operando1 * operando2) / 100;
                zonaDisplay.setText(String.valueOf(resultado));
                zonaDisplayPeq.setText(zonaDisplayPeq.getText() + entrada);
                ldecimal = false;
                loperando1 = true;
                break;
            case R.id.btnDecimal:
                if(ldecimal == false){
                    zonaDisplay.setText(zonaDisplay.getText() + entrada);
                    zonaDisplayPeq.setText(zonaDisplayPeq.getText() + entrada);
                    ldecimal = true;
                }
                else {
                    return;
                }
                break;
            case R.id.btnCambioSigno:
                if(lpositivo){
                    zonaDisplay.setText("-" + zonaDisplay.getText());
                    zonaDisplayPeq.setText("-" + zonaDisplayPeq.getText());
                    lpositivo = false;
                }
                else {
                    Double numero = Double.parseDouble(zonaDisplay.getText().toString());
                    numero *= -1;
                    zonaDisplay.setText(String.valueOf(numero));
                    zonaDisplayPeq.setText(String.valueOf(numero));
                }
                break;
            default:
                if(tamano < 5) {
                    zonaDisplayPeq.setText(zonaDisplayPeq.getText() + entrada);
                    if (lnuevoOperando) {
                        zonaDisplay.setText(entrada);
                        lnuevoOperando = false;
                    } else {
                        zonaDisplay.setText(zonaDisplay.getText() + entrada);
                    }
                    tamano++;
                }
                break;
        }
    }
}

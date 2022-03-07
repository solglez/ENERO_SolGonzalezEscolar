package com.example.enero_solgonzalezescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AvtivityCalc extends AppCompatActivity {
    EditText etAltura, etPeso;
    Button btnAct2;
    String resultado;
    Double valor;
    LinearLayout llDatos, llCalc;
    TextView tvResult;
    ImageView imResult1, imgResult2, imgResult3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avtivity_calc);
        declararVariables();


    }

    public void onClickBtn(View view) {
        if(view.getId()==R.id.btnAct2){
            //Antes de calcular nada comprobamos que se han introducido datos
            if (etAltura.getText().toString().equals("")||etPeso.getText().toString().equals("")){
                Toast.makeText(AvtivityCalc.this, (R.string.sinvalor),Toast.LENGTH_LONG).show();
            }else {

                //Apunte de examen: no funciona bien el android:inputType="numberDecimal" de los edit text

                //Utilizamos decimal format para asegurar que el resultado tiene un máximo de dos decimales
                DecimalFormat df=new DecimalFormat("#.00");
                double alto = Double.valueOf(etAltura.getText().toString());
                double peso = Double.valueOf(etPeso.getText().toString());
                resultado="IMC = ";
                valor=peso/(alto*alto);
                resultado += String.valueOf(df.format(valor));
                llDatos.setVisibility(View.GONE);
                llCalc.setVisibility(View.VISIBLE);
                tvResult.setText(resultado);
                if(valor<25.0){
                    imResult1.setVisibility(View.VISIBLE);
                    imgResult2.setVisibility(View.GONE);
                    imgResult3.setVisibility(View.GONE);
                }else if(valor<30.0){
                    imResult1.setVisibility(View.GONE);
                    imgResult2.setVisibility(View.VISIBLE);
                    imgResult3.setVisibility(View.GONE);
                }else{
                    imResult1.setVisibility(View.GONE);
                    imgResult2.setVisibility(View.GONE);
                    imgResult3.setVisibility(View.VISIBLE);
                }

            }

        }else if(view.getId()==R.id.btnNuevoCalc){
            llDatos.setVisibility(View.VISIBLE);
            llCalc.setVisibility(View.GONE);
            imResult1.setVisibility(View.VISIBLE);
            imgResult2.setVisibility(View.GONE);
            imgResult3.setVisibility(View.GONE);
            etPeso.setText("");
            etAltura.setText("");

        } else if(view.getId()==R.id.btnFin){
            //Retornamos a la main
            Intent i=new Intent();
            //Devolvemos el control a actividad origen con envío de datos
            setResult(RESULT_CANCELED,i);
            //Finalizamos actividad (sino no retorna a main, para evitar apilamientos)
            finish();
        }

    }

    //Método para declarar las variables sin que entorpezcan el código
    public void declararVariables(){
        etAltura=findViewById(R.id.etAltura);
        etPeso=findViewById(R.id.etPeso);
        llDatos=findViewById(R.id.llDatos);
        llCalc=findViewById(R.id.llResultado);
        tvResult=findViewById(R.id.tvResult);
        imResult1=findViewById(R.id.imgResult1);
        imgResult2=findViewById(R.id.imgResult2);
        imgResult3=findViewById(R.id.imgResult3);

        llDatos.setVisibility(View.VISIBLE);
        llCalc.setVisibility(View.GONE);
        imResult1.setVisibility(View.VISIBLE);
        imgResult2.setVisibility(View.GONE);
        imgResult3.setVisibility(View.GONE);
    }
}
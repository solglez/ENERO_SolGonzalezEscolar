package com.example.enero_solgonzalezescolar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    ImageButton btnLogo;
    LinearLayout llOpciones, llLogo;
    RadioGroup rgOpciones;
    RadioButton rbCalcular, rbInfo;
    Button btnAct1;
    int CODIGO_LLAMADA_CALC, CODIGO_LLAMADA_INFO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaración de variables
        declararVariables();
        //Escuchamos el método onLongClick del logo
        llLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Cambiamos visivilidad de los linears layouts
                llLogo.setVisibility(View.GONE);
                llOpciones.setVisibility(View.VISIBLE);
                return true;
            }
        });
        //Escuchamos el método onLongClick del logo
        btnLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Cambiamos visivilidad de los linears layouts
                llLogo.setVisibility(View.GONE);
                llOpciones.setVisibility(View.VISIBLE);
                return true;
            }
        });


        //Escuchamos el onCheckedChange del radio group de opciones y adecuamos el botón
        rgOpciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rbCalcular){
                    btnAct1.setText(R.string.calcular_imc);
                }else{
                    btnAct1.setText(R.string.mas_info);
                }
            }
        });
    }



    public void onClickBtn(View view) {
        //Segun el rb pulsado cambiamos comportamiento
        Intent intent;
        if(rbCalcular.isChecked()){
            intent=new Intent(this, AvtivityCalc.class);
            startActivityForResult(intent,CODIGO_LLAMADA_CALC);
        }else{
            intent=new Intent(this, ActivityInfo.class);
            startActivityForResult(intent,CODIGO_LLAMADA_INFO);
        }
    }

    //Método donde nos enteramos de las respuestas
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Comprobamos el código de quién responde
        if(requestCode==CODIGO_LLAMADA_CALC){
            //Comprobamos cómo terminó la ejecución
            if(resultCode==RESULT_CANCELED){
                finish();
            }
        }else if(requestCode==CODIGO_LLAMADA_INFO){

        }
    }



    //Método para declarar las variables sin que entorpezcan el código
    public void declararVariables(){
            btnLogo=findViewById(R.id.btnLogo);
            llOpciones=findViewById(R.id.llOpciones);
            llLogo=findViewById(R.id.llLogo);
            rgOpciones=findViewById(R.id.rgOpcion);
            rbCalcular=findViewById(R.id.rbCalcular);
            rbInfo=findViewById(R.id.rbInfo);
            btnAct1=findViewById(R.id.btnAct1);

            llLogo.setVisibility(View.VISIBLE);
            llOpciones.setVisibility(View.GONE);
            CODIGO_LLAMADA_CALC=1;
            CODIGO_LLAMADA_INFO=2;
        }
}
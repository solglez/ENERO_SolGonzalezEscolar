package com.example.enero_solgonzalezescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityInfo extends AppCompatActivity {
    RadioGroup rgAct3;
    RadioButton rbWeb, rbCall;
    Intent intent;
    int LLAMADA_TELEFONICA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        declararVariables();



    }

    public void onClickBtn(View view) {
        if(view.getId()==R.id.btnOK){
            if(rbWeb.isChecked()){
                //Visualizar una página web
                intent=new Intent(Intent.ACTION_VIEW,Uri.parse(getResources().getString(R.string.wiki)));
                startActivity(intent);
            }else{
                //Realizar llamada de forma directa
                //¿Tenemos permiso? - La forma de actuar difiere según la versión de API
                //Testeamos el nivel de api para poder comprobar el permiso sin que las v<23 rompan:
                if(Build.VERSION.SDK_INT>=23){
                    int permiso= checkSelfPermission(Manifest.permission.CALL_PHONE);
                    if (permiso== PackageManager.PERMISSION_GRANTED){ //Estas dos lineas podrían ser una
                        //Tenemos permiso, lanzamos el intent
                        intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)915555555"));
                        startActivity(intent);
                    }else{
                        //No tenemos permiso, lo pedimos (diálogo del sistema)
                        //LLAMADA_TELEFONICA es la constante para recuperar la respuesta
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONICA);
                        //Método para interceptar respuesta debajo
                    }
                }else{
                    //Para versión menor a 23 (PERMISOS EN MANIFEST) valdría con el intent directamente:
                    intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:(+34)915555555"));
                    startActivity(intent);
                }
            }

        }else if(view.getId()==R.id.btnVolver){
            //Retornamos a la main
            intent=new Intent();
            //Devolvemos el control a actividad origen con envío de datos
            setResult(RESULT_OK,intent);
            //Finalizamos actividad (sino no retorna a main, para evitar apilamientos)
            finish();
        }
    }

    //Método para escuchar la respuesta del usuario tras pedirle permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==LLAMADA_TELEFONICA){
            //Si la respuesta corresponde a la petición de llamada telefónica
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //Si el permiso se ha concedido
                Intent i=new Intent(Intent.ACTION_CALL,Uri.parse("tel:(+34)915555555"));
                startActivity(i);
            }else{
                //Si el usuario no da permiso
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void declararVariables(){
        rgAct3=findViewById(R.id.rgOpcionInfo);
        rbCall=findViewById(R.id.rbCall);
        rbWeb=findViewById(R.id.rbWeb);
        LLAMADA_TELEFONICA=5;
    }
}
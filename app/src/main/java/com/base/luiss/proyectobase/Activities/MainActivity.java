package com.base.luiss.proyectobase.Activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.luiss.proyectobase.R;
import com.base.luiss.proyectobase.Util.*;
import com.base.luiss.proyectobase.datos.ControlSQLiteOpenHelper;
import com.base.luiss.proyectobase.modelo.persona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText txtNombre,txtApellido;
    Button btnGuardar, btnMostrar;
    general gn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicializarComponentes();
    }

    private void InicializarComponentes(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        gn = new general();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarControles(2))
                {
                    String sNombre = txtNombre.getText().toString();
                    String sApellido = txtApellido.getText().toString();
                    if(insertar(sNombre, sApellido)){
                        //Realizar alguna otra operación
                    }
                }
                else{
                    gn.mostrarMensaje(getApplicationContext(),"Teclee valores",constantes.TOAST_DURACION_LARGO);
                }

            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarControles(1))
                {
                    String sNombre = txtNombre.getText().toString();
                    consultaPorNombre(sNombre);
                }
                else{
                    gn.mostrarMensaje(getApplicationContext(),"Teclee valores",constantes.TOAST_DURACION_LARGO);
                }

            }
        });
    }

    public boolean insertar(String sNombre, String sApellido) {

        boolean bRespuesta = false;
        SQLiteDatabase bd = null;
        try {
            ControlSQLiteOpenHelper admin = new ControlSQLiteOpenHelper(this,
                    "administracion", null, 1);
            bd = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("nombre", sNombre);
            registro.put("apellido", sApellido);
            bd.insert("personas", null, registro);
            bd.close();
            txtNombre.setText("");
            txtApellido.setText("");
            gn.mostrarMensaje(this,"Se cargaron los datos de la persona", constantes.TOAST_DURACION_LARGO);
            bRespuesta = true;
        }
        catch(Exception ex){
            gn.mostrarMensaje(this,"Error al inserta", constantes.TOAST_DURACION_LARGO);
            ex.printStackTrace();
        }finally {
            bd.close();

        }


        return bRespuesta;
    }

    public ArrayList<persona> consultaPorNombre(String sNombre) {

        persona objPersona = new persona();
        ArrayList<persona> lisPersona = new ArrayList<>();
        ControlSQLiteOpenHelper admin = null;
        SQLiteDatabase bd = null;
        Cursor fila = null;

        try {
            admin = new ControlSQLiteOpenHelper(this,"administracion", null, 1);
            bd = admin.getWritableDatabase();

            StringBuilder sQuery = new StringBuilder();
            sQuery.append("select nombre,apellido from personas where nombre='" + sNombre + "'");
            fila = bd.rawQuery(sQuery.toString(), null);
            if (fila.moveToFirst()) {
                while (fila.isAfterLast() == false) {
                    objPersona.setNombre(fila.getString(0));
                    objPersona.setApellido(fila.getString(1));
                    lisPersona.add(objPersona);
                    txtNombre.setText(fila.getString(0));
                    txtApellido.setText(fila.getString(1));
                    fila.moveToNext();
                }
            }else{
                gn.mostrarMensaje(this,"No existe la personas con dicho nombre",constantes.TOAST_DURACION_LARGO);
            }
            
            for (int i = 0; i< 10; i++){
                
            }
            

        }
        catch (Exception ex){
            gn.mostrarMensaje(this,"Error al obtener los datos",constantes.TOAST_DURACION_LARGO);
            ex.printStackTrace();
        }finally {
            bd.close();
            fila.close();
        }

        return  lisPersona;
    }

    private boolean validarControles(int iControles){
        boolean bRespuesta = false;

        switch (iControles){
            case 1:
                if(!txtNombre.getText().toString().equals(""))
                        bRespuesta = true;
                break;
            case 2:
                if(!txtNombre.getText().toString().equals(""))
                    if(!txtApellido.getText().toString().equals(""))
                        bRespuesta = true;
                break;
            default:
                gn.mostrarMensaje(this,"Numero no definido",constantes.TOAST_DURACION_LARGO);
                break;

        }

        return  bRespuesta;
    }


}

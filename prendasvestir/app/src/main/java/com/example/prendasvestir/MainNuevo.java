package com.example.prendasvestir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prendasvestir.entity.Prenda;
import com.example.prendasvestir.models.PrendaDAO;

public class MainNuevo extends AppCompatActivity {
    String arrayTalla[] = {"S" , "M" , "L" , "XL"};
    String arrayColor[] = {"Amarillo", "Azul", "Blanco" , "Negro" , "Rojo" , "Verde"};
    Spinner spinnerTalla;
    Spinner spinnerColor;

    EditText txtPrenda;
    EditText txtMarca;
    EditText txtPrecio;
    PrendaDAO daoPrenda=new PrendaDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo);

        txtPrenda = (EditText)findViewById(R.id.txtNombre);
        txtMarca = (EditText)findViewById(R.id.txtMarca);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);

        spinnerTalla = (Spinner)findViewById(R.id.SpinnerTalla);
        spinnerColor = (Spinner)findViewById(R.id.SpinnerColor);
        CargarSpinner();

        daoPrenda.openBD();
    }

    private void CargarSpinner() {
        ArrayAdapter adapterTalla=new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,arrayTalla);
        spinnerTalla.setAdapter(adapterTalla);

        ArrayAdapter adapterColor=new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,arrayColor);
        spinnerColor.setAdapter(adapterColor);
    }

    public void Guardar(View view){
        String talla = spinnerTalla.getSelectedItem().toString();
        String color = spinnerColor.getSelectedItem().toString();
        double precio = Double.parseDouble(txtPrecio.getText().toString());
        String prenda = txtPrenda.getText().toString();
        String marca = txtMarca.getText().toString();

        Prenda p = new Prenda();
        p.setColor(color);
        p.setMarca(marca);
        p.setTalla(talla);
        p.setPrecioUnitario(precio);
        p.setNombre(prenda);

       int res =  daoPrenda.Registrar(p);


       if(res > 0){
           Toast.makeText(this   ,"Registro correcto.!!" ,Toast.LENGTH_LONG).show();
           LimpiarCasillas();
       }else{
           Toast.makeText(this   ,"No se ha podido registrar.!!" ,Toast.LENGTH_LONG).show();
       }

    }

    public void ViewListar(View view){
        Intent it=new Intent(MainNuevo.this,MainActivity.class);
        startActivity(it);
    }

    public void LimpiarCasillas(){
        txtPrenda.setText("");
        txtMarca.setText("");
        txtPrecio.setText("");
        txtPrenda.requestFocus();
    }
}
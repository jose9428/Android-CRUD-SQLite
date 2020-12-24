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

public class MainModificar extends AppCompatActivity {
    String arrayTalla[] = {"S" , "M" , "L" , "XL"};
    String arrayColor[] = {"Amarillo", "Azul", "Blanco" , "Negro" , "Rojo" , "Verde"};
    Spinner spinnerTalla;
    Spinner spinnerColor;

    EditText txtPrenda;
    EditText txtMarca;
    EditText txtPrecio;
    PrendaDAO daoPrenda=new PrendaDAO(this);
    Prenda prenda = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modificar);

        txtPrenda = (EditText)findViewById(R.id.txtNombre);
        txtMarca = (EditText)findViewById(R.id.txtMarca);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);

        spinnerTalla = (Spinner)findViewById(R.id.SpinnerTalla);
        spinnerColor = (Spinner)findViewById(R.id.SpinnerColor);
        CargarSpinner();

        prenda = (Prenda) getIntent().getSerializableExtra("prenda");
        Asignar(prenda);
        daoPrenda.openBD();
    }

    public void Asignar(Prenda prenda){
        txtMarca.setText(prenda.getMarca());
        txtPrecio.setText(""+prenda.getPrecioUnitario());
        txtPrenda.setText(prenda.getNombre());

        for (int x = 0; x < spinnerTalla.getAdapter().getCount(); x++) {
            if (spinnerTalla.getItemAtPosition(x).toString().equals(prenda.getTalla())) {
                spinnerTalla.setSelection(x);
                break;
            }
        }

        for (int x = 0; x < spinnerColor.getAdapter().getCount(); x++) {
            if (spinnerColor.getItemAtPosition(x).toString().equals(prenda.getColor())) {
                spinnerColor.setSelection(x);
                break;
            }
        }

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
        String nombre = txtPrenda.getText().toString();
        String marca = txtMarca.getText().toString();

        Prenda p = new Prenda();
        p.setColor(color);
        p.setMarca(marca);
        p.setTalla(talla);
        p.setPrecioUnitario(precio);
        p.setNombre(nombre);
        p.setId(prenda.getId());

        int res =  daoPrenda.Modificar(p);


        if(res > 0){
            Toast.makeText(this   ,"Se Actualizo correctamente los datos.!!" ,Toast.LENGTH_LONG).show();
            ViewListar(view);
        }else{
            Toast.makeText(this   ,"No se ha podido actualizar.!!" ,Toast.LENGTH_LONG).show();
        }


    }

    public void ViewListar(View view){
        Intent it=new Intent(MainModificar.this,MainActivity.class);
        startActivity(it);
    }
}
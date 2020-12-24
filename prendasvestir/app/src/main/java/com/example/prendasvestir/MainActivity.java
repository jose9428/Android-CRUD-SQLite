package com.example.prendasvestir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prendasvestir.entity.Prenda;
import com.example.prendasvestir.models.PrendaDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    PrendaDAO daoPrenda=new PrendaDAO(this);
    ArrayList<Prenda> lista=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        daoPrenda.openBD();
        ListarDatos();
    }

    public void ViewRegistrar(View view){
        Intent it=new Intent(MainActivity.this,MainNuevo.class);
        startActivity(it);
    }

    public void ListarDatos() {

        lista=daoPrenda.Listado();
        ArrayList<String> listaCad=new ArrayList<>();
        for(Prenda p:lista){
            listaCad.add(p.getNombre() + " , "+p.getColor()+" , "+p.getPrecioUnitario());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,listaCad);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Prenda p = lista.get(i);
                //Toast.makeText(getApplication()   ,"ID : "+p.getId() ,Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager=getSupportFragmentManager();
                DialogoAlerta dialogo=new DialogoAlerta();
                dialogo.setPrenda(p);
                dialogo.setPrendaDAO(daoPrenda);
                dialogo.show(fragmentManager,"tagAlerta");
            }
        });

    }
}
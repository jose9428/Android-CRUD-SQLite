package com.example.prendasvestir.models;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.prendasvestir.entity.Prenda;
import com.example.prendasvestir.util.BDPrenda;
import com.example.prendasvestir.util.ConstantesBD;

import java.util.ArrayList;

public class PrendaDAO {
    BDPrenda bdPrenda;
    SQLiteDatabase database;


    public PrendaDAO(Context c) {
        bdPrenda=new BDPrenda(c);
    }

    public void openBD(){
        database=bdPrenda.getWritableDatabase();
    }

    public void closeBD(){
        bdPrenda.close();
        database.close();
    }

    public int Registrar(Prenda p){
        int res = 0;
        try{
            ContentValues values=new ContentValues();
            values.put("nombre",p.getNombre());
            values.put("marca",p.getMarca());
            values.put("talla",p.getTalla());
            values.put("color",p.getColor());
            values.put("precioUnitario" ,p.getPrecioUnitario());
            res = (int)database.insert(ConstantesBD.NOMBRETABLA,null,values);
        }catch (Exception e){
        }
        return res;
    }

    public int Modificar(Prenda p){
        int res = 0;

        try{
            ContentValues values=new ContentValues();
            values.put("nombre",p.getNombre());
            values.put("marca",p.getMarca());
            values.put("talla",p.getTalla());
            values.put("color",p.getColor());
            values.put("precioUnitario" ,p.getPrecioUnitario());
            res = (int) database.update(ConstantesBD.NOMBRETABLA,values,
                    "id="+p.getId(),null);
        }catch (Exception e){
        }
        return res;
    }

    public ArrayList<Prenda> Listado(){
        ArrayList<Prenda> lista=new ArrayList<>();
        try{
            Cursor c=database.rawQuery("SELECT * FROM "+ConstantesBD.NOMBRETABLA, null);
            while (c.moveToNext()){
                Prenda p = new Prenda();
                p.setId(c.getInt(0));
                p.setNombre(c.getString(1));
                p.setMarca(c.getString(2));
                p.setTalla(c.getString(3));
                p.setColor(c.getString(4));
                p.setPrecioUnitario(c.getDouble(5));
                lista.add(p);
            }
        }catch (Exception e){

        }
        return lista;
    }

    public int Eliminar(int id){
        int res = 0;
        try{
           res = (int) database.delete(ConstantesBD.NOMBRETABLA,"id="+id,null);

        }catch (Exception e){
        }
        return res;
    }
}

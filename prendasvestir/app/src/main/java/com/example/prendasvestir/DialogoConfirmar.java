package com.example.prendasvestir;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.prendasvestir.entity.Prenda;
import com.example.prendasvestir.models.PrendaDAO;

public class DialogoConfirmar extends DialogFragment {
    PrendaDAO daoPrenda= null;
    Prenda prenda = null;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("¿Esta seguro que desea eliminar la prenda con codigo "+prenda.getId() +"?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        daoPrenda.openBD();
                        int res = daoPrenda.Eliminar(prenda.getId());

                        if(res > 0){
                            Intent it=new Intent(getActivity(),MainActivity.class);
                            startActivity(it);
                            Toast.makeText(getActivity() , "Se elimino correctamente",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getActivity() , "No se ha podido eliminar el registro",Toast.LENGTH_LONG).show();
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indice) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    public void setPrendaDAO(PrendaDAO p){
        this.daoPrenda = p;
    }

    public void setPrenda(Prenda p){
        this.prenda = p;
    }

}

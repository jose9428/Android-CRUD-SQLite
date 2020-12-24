package com.example.prendasvestir;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.widget.Toast;

import com.example.prendasvestir.entity.Prenda;
import com.example.prendasvestir.models.PrendaDAO;

public class DialogoAlerta extends DialogFragment {
    PrendaDAO daoPrenda= null;
    Prenda prenda = null;

     @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState ) {
        final  String[] items={"Eliminar","Modificar"};
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona operacion")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indice) {
                        if(indice == 0){
                            FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                            DialogoConfirmar dialogo=new DialogoConfirmar();
                            dialogo.setPrendaDAO(daoPrenda);
                            dialogo.setPrenda(prenda);
                            dialogo.show(fragmentManager,"tagAlerta");

                        }else if(indice == 1){
                            Intent it=new Intent(getActivity(),MainModificar.class);
                            Bundle bundle= new Bundle();
                            bundle.putSerializable("prenda", prenda);
                            it.putExtras(bundle);
                            startActivity(it);
                        }
                    }
                });

        return builder.create();
    }


    public void setPrenda(Prenda p){
         this.prenda = p;
    }
    public void setPrendaDAO(PrendaDAO p){
        this.daoPrenda = p;
    }

}

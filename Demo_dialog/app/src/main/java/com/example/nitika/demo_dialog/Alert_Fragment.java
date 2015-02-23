package com.example.nitika.demo_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by NITIKA on 20-Feb-15.
 */
public class Alert_Fragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle("close")
                .setMessage("do you what to close")
                .setPositiveButton("yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity().getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("no",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();


                    }
                })
               .create()

                ;


    }
}

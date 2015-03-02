package com.example.nitika.assg_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by NITIKA on 24-Feb-15.
 */
public class Alert_fragment_dialog extends DialogFragment implements TextView.OnEditorActionListener {

public interface  EditNameDialogListner{
    void onfinishEditDialog(String input);
}
    EditText name,phno;
    public Alert_fragment_dialog()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.alert_fragment_dialog_ui,container);
        name=(EditText)view.findViewById(R.id.id_name);
        phno=(EditText)view.findViewById(R.id.id_phone_no);
        getDialog().setTitle("Dialog Fragment Example");

        // Show soft keyboard automatically
        name.requestFocus();
       phno.requestFocus();
        name.setOnEditorActionListener(this);
        phno.setOnEditorActionListener(this);

        return view;



    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListner activity = (EditNameDialogListner) getActivity();
            activity.onfinishEditDialog((name.getText().toString()));
            activity.onfinishEditDialog((phno.getText().toString()));
            this.dismiss();
            return true;
        }
        return false;
    }
/*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        return  new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle("ADD")
                .setMessage("DO YOU WANT TO ADD")
                .setPositiveButton("save",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(),"yess",Toast.LENGTH_LONG).show();

                    }
                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(),"yess",Toast.LENGTH_LONG).show();
                    }
                }).create();
    }
    */
}

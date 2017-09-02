package com.kodesnippets.aaqib.remotecontrol;


import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by silen on 8/27/2017.
 */
/**
 * Helper functions
 */
public class Util {

    public static Boolean isEditTextEmpty(EditText editText){

        if(editText.getText().toString().isEmpty() || editText.getText().toString().equals("")){
            return true;
        }
        return false;
    }

    public static void showToast(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}

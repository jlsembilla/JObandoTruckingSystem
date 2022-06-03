package com.example.eld;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class addNewDriver extends AppCompatActivity {

    private Button generate;

    public AlertDialog.Builder dialogBuilder;
    public AlertDialog dialog;
    public TextView usernameL, passwordL, usernameT, passwordT;
    public Button copyToClipboard;
    final Random rand = new Random();

    public EditText fName, mName, lName, age, exp, tModel, tPlateNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_driver);

        fName = (EditText) findViewById(R.id.fname);
        mName = (EditText) findViewById(R.id.mname);
        lName = (EditText) findViewById(R.id.lname);
        age = (EditText) findViewById(R.id.age);
        exp = (EditText) findViewById(R.id.exp);
        tModel = (EditText) findViewById(R.id.tmodel);
        tPlateNum = (EditText) findViewById(R.id.platenum);

        usernameT = findViewById(R.id.userNameText);
        passwordT = findViewById(R.id.PasswordText);

        generate = (Button) findViewById(R.id.generateBtn);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog();

                String fname = String.valueOf(fName.getText());

                if(fName.equals("") || mName.equals("") || lName.equals("") || age.equals("") || exp.equals("") || tModel.equals("") || tPlateNum.equals("")){;

                    Toast.makeText(addNewDriver.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    usernameT.setVisibility(View.VISIBLE);
                    passwordT.setVisibility(View.VISIBLE);

                    usernameT.setText(fname + rand.nextInt(100));
                    passwordT.setText(generatePass());

                }
            }
        });
    }

    public void createPopupDialog(){

        dialogBuilder = new AlertDialog.Builder(this);
        final View popUp = getLayoutInflater().inflate(R.layout.popup, null);
        usernameL = (TextView) popUp.findViewById(R.id.usernameLabel);
        passwordL = (TextView) popUp.findViewById(R.id.passwordLabel);
        usernameT = (TextView) popUp.findViewById(R.id.userNameText);
        passwordT = (TextView) popUp.findViewById(R.id.PasswordText);
        copyToClipboard = (Button) popUp.findViewById(R.id.copyBtn);

        dialogBuilder.setView(popUp);
        dialog = dialogBuilder.create();
        dialog.show();

        copyToClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", "Username: " + usernameT.getText().toString() + "\nPassword: " + passwordT.getText().toString());
                cm.setPrimaryClip(clip);

                Toast.makeText(addNewDriver.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String generatePass(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++){
            char c = chars[rand.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
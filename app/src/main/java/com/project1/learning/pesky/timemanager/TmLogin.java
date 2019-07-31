package com.project1.learning.pesky.timemanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class TmLogin extends AppCompatActivity implements View.OnClickListener
{
    EditText usernameEditText;
    EditText passwordEditText;
    TextView txtViewMessage;



    Button loginButton ;
    ProgressBar loadingProgressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        txtViewMessage = findViewById(R.id.txtViewMessage1);



        txtViewMessage.setEnabled(false);
        txtViewMessage.setVisibility(View.INVISIBLE);
        txtViewMessage.setTextColor(Color.RED);
        //txtViewMessage.setBackgroundColor(Color.RED);
        txtViewMessage.setText("Accesso non valido.");


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




                // se username e password rispondono al pattern che decidiamo a priori, allora:
                // abilitiamo il pulsante Login/Accedi
                if  ((!usernameEditText.getText().toString().equals("")) &&
                    (!passwordEditText.getText().toString().equals("")) )
                {
                    loginButton.setEnabled(true);
                }
                else
                {
                    loginButton.setEnabled(true);
                }

                txtViewMessage.setVisibility( View.INVISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {


                /*
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                */
            }
        };


        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        loginButton.setOnClickListener(this);

    }





    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.login) {

            /*
            Log.d("PROVA", "Valore di Username:" + usernameEditText.getText().toString());
            Log.d("PROVA", "Valore di Password:" + passwordEditText.getText().toString());

            if ( (usernameEditText.getText().toString().equals("Renato")) && (passwordEditText.getText().toString().equals("pwd") ) )
            {

                Log.d("PROVA", "Valore di User e password verificati");

                Intent intent = new Intent(this, AttivitaGiornaliera.class);
                startActivity(intent);
            }
            */

            String user = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            boolean auth = true;

            //if (auth){
            if ( (user.equals("Kennedy")) && (password.equals("Kennedy") ) || (user.equals("")) && (password.equals(""))){
                // Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                Log.d("PROVA", "Valore di User e password verificati");
                Intent intent = new Intent(this, TmAttivitaGiornaliera.class);
                Toast.makeText(getApplicationContext(), "Loged in as"+user, Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                this.finish();
            }
            else
            {
                txtViewMessage.setVisibility( View.VISIBLE);

                //Toast.makeText(getApplicationContext(), "Dati non validi", Toast.LENGTH_SHORT).show();
            }

        }
    }

}

package com.example.delll.aes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends ActionBarActivity {


   private Button mEncryptButton;
   private Button mDecryptButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEncryptButton = (Button)findViewById(R.id.encrypt_button);
        mEncryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input;
                EditText password;
                EditText output;
                input = (EditText)findViewById(R.id.encrypt_text);
                password = (EditText)findViewById(R.id.password_text);
                output = (EditText)findViewById(R.id.decrypt_text);
                try {
                    Encrypt a = new Encrypt("");
                    output.setText(a.encrypt(password.getText().toString(), input.getText().toString()));
                    input.setText("");
                }  catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        mDecryptButton = (Button)findViewById(R.id.decrypt_button);
        mDecryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input;
                EditText password;
                EditText output;
                input = (EditText)findViewById(R.id.encrypt_text);
                password = (EditText)findViewById(R.id.password_text);
                output = (EditText)findViewById(R.id.decrypt_text);
                try {
                    Decrypt a = new Decrypt("");
                    input.setText(a.decrypt(password.getText().toString(), output.getText().toString()));
                }  catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

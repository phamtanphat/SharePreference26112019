package com.example.sharepreference26112019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText mEdtUserName,mEdtPassword;
    CheckBox mCbSave;
    Button mBtnLogin;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mapview();
        setListener();
    }

    private void init() {
        mSharedPreferences = getSharedPreferences("CacheApp",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
    private void mapview() {
        mEdtPassword = findViewById(R.id.edittextPassword);
        mEdtUserName = findViewById(R.id.edittextUsername);
        mCbSave = findViewById(R.id.checkboxSave);
        mBtnLogin = findViewById(R.id.buttonLogin);

        String username = mSharedPreferences.getString("username","");
        String password = mSharedPreferences.getString("password","");
        Boolean isSaved = mSharedPreferences.getBoolean("isSaved",false);

        mEdtUserName.setText(username);
        mEdtPassword.setText(password);
        mCbSave.setChecked(isSaved);

    }

    private void setListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEdtUserName.getText().toString();
                String password = mEdtPassword.getText().toString();

                if (username.equals("phatandroid") && password.equals("123456")){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if (mCbSave.isChecked()){
                        mEditor.putString("username",username);
                        mEditor.putString("password",password);
                        mEditor.putBoolean("isSaved",true);
                        mEditor.commit();
                    }else{
                        mEditor.clear();
                        mEditor.commit();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

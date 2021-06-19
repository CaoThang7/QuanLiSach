package com.example.duanmau_ps12545_lycaothanggd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import dao.QuanTriDAO;
import model.User;


public class LoginActivity extends AppCompatActivity {
    LinearLayout ggg;
    public static User USER =null;
    QuanTriDAO qtDao;
    EditText etUsername,etPass;
    Button btDangNhap,btHuy,btnMy;
    CheckBox ckRem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ggg = (LinearLayout)findViewById(R.id.ggg);
        ggg.setBackgroundResource(R.drawable.hinhnen3);




        qtDao=new QuanTriDAO(LoginActivity.this);
        //Tham chieu
        etUsername= findViewById(R.id.txtUsername);
        etPass= findViewById(R.id.txtPassword);
        btDangNhap= findViewById(R.id.btnDangNhap);
//        btHuy= findViewById(R.id.btnHuy);
        ckRem=findViewById(R.id.checkbox);
        loadData();

//       btnMy.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent i=new Intent(Lab1btThemActivity.this,MainActivity.class);
//               startActivity(i);
//           }
//       });


        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=etUsername.getText().toString();
                String pass=etPass.getText().toString();
                boolean check=ckRem.isChecked();
                User user=new User(username,pass);
                if(qtDao.checkLogin(user)){
                    luuTT(username,pass,check);
                    USER =user;
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!!!",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại!!!",Toast.LENGTH_SHORT).show();
                }





//
//              String username= "lycaothang";
//              String pass="123";

//              boolean check=ckRem.isChecked();
//              luuTT(username,pass,check);
//
//              if(etUsername.getText().toString().equals(username)&& etPass.getText().toString().equals(pass)){
//                  Toast.makeText(Lab1btThemActivity.this,"Đăng nhập thành công!!!",Toast.LENGTH_LONG).show();
//                  Intent i=new Intent(Lab1btThemActivity.this,MainnActivity.class);
//                  startActivity(i);
//
////
//              }else{
//                  Toast.makeText(Lab1btThemActivity.this,"Đăng nhập thất bại mời nhập lại",Toast.LENGTH_LONG).show();
//              }

                // chuyen sang trang lab1b3
                //Intent i=new Intent(Lab1btThemActivity.this,Lab1b3Activity.class);
                //startActivity(i);


            }
        });
    }
    private  void luuTT(String un,String pwd,boolean check){
        SharedPreferences preferences=getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(check){
            editor.putString("username",un);
            editor.putString("password",pwd);
            editor.putBoolean("check",check);
        }else{
            editor.clear();
        }
        editor.commit();
    }

    private void loadData(){
        SharedPreferences pref=getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        boolean check=pref.getBoolean("check",false);
        if(check){
            etUsername.setText(pref.getString("username",""));
            etPass.setText(pref.getString("password",""));
            ckRem.setChecked(check);

        }
}

    }


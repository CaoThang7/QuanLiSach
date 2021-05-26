package com.example.duanmau_ps12545_lycaothanggd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import dao.QuanTriDAO;
import model.User;

import static com.example.duanmau_ps12545_lycaothanggd.LoginActivity.USER;

public class DoiMatKhauActivity extends AppCompatActivity {
//    EditText oldPwd,newPwd,rePwd;
//    Button btDmk,btHuydoimk;
//    QuanTriDAO qtDao;
    LinearLayout doimk;
    EditText etmkc,etmkm,etmkm2;
    QuanTriDAO qtdao;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
//        doimk=findViewById(R.id.doimk);
//        doimk.setBackgroundResource(R.drawable.hinhbocuc);
//        qtDao=new QuanTriDAO(DoiMatKhauActivity.this);
//
//        oldPwd=findViewById(R.id.oldPwd);
//        newPwd=findViewById(R.id.newPwd);
//        rePwd=findViewById(R.id.rePwd);
//        btDmk=findViewById(R.id.btDmk);
//        btHuydoimk=findViewById(R.id.btnHuydoimk);
//
//        btDmk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String old= oldPwd.getText().toString();
//                String nPwd= newPwd.getText().toString();
//                String re= rePwd.getText().toString();
//                String un= LoginActivity.USER.getUsername();
//                if(!qtDao.checkOldPWD(old)){
//                    Toast.makeText(DoiMatKhauActivity.this,"Mật khẩu cũ không đúng!!!",Toast.LENGTH_SHORT).show();
//                }else{
//                    if(!nPwd.equals(re)){
//                        Toast.makeText(DoiMatKhauActivity.this,"Mật khẩu nhập lại phải trùng mật khẩu mới!!!",Toast.LENGTH_SHORT).show();
//                    }else{
//                        if(qtDao.updatePwd(new User(un,nPwd))){
//                            Toast.makeText(DoiMatKhauActivity.this,"Cập nhật thành công!!!",Toast.LENGTH_SHORT).show();
//                            Intent i=new Intent(DoiMatKhauActivity.this,MainActivity.class);
//                            startActivity(i);
//
//                            LoginActivity.USER.setPassword(nPwd);
//                        }else{
//                            Toast.makeText(DoiMatKhauActivity.this,"Thất bại!!!",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                }
//            }
//        });
//
//        btHuydoimk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(DoiMatKhauActivity.this,MainActivity.class);
//                startActivity(i);
//            }
//        });
//
        doimk=findViewById(R.id.doimk);
        doimk.setBackgroundResource(R.drawable.hinhbocuc);
        etmkc=findViewById(R.id.oldPwd);
        etmkm=findViewById(R.id.newPwd);
        etmkm2=findViewById(R.id.rePwd);
        Button btchance = findViewById(R.id.btDmk);
        Button btHuydoimk=findViewById(R.id.btnHuydoimk);
        qtdao=new QuanTriDAO(DoiMatKhauActivity.this);
        final String usernamecu=USER.getUsername();
        final String passwordcu=USER.getPassword();
        btchance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkc=etmkc.getText().toString();
                String mkm=etmkm.getText().toString();
                String mkm2=etmkm2.getText().toString();
                if(!passwordcu.equals(mkc)){
                    Toast.makeText(DoiMatKhauActivity.this,"Sai mật khẩu cũ",Toast.LENGTH_LONG).show();
                }else {
                    if(mkm.equals(mkm2)) {
                        if (qtdao.chancepassword(usernamecu, mkm)) {
                            Toast.makeText(DoiMatKhauActivity.this, "Cập nhật thành công!!!", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(DoiMatKhauActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                    }else {
                        Toast.makeText(DoiMatKhauActivity.this, "mật khẫu mới không trùng khớp", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btHuydoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DoiMatKhauActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

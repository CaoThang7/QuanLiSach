package com.example.duanmau_ps12545_lycaothanggd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.duanmau_ps12545_lycaothanggd.Fragment.BlankFragmentHoaDonChiTiet;
import com.example.duanmau_ps12545_lycaothanggd.Fragment.Fragment_chi;
import com.example.duanmau_ps12545_lycaothanggd.Fragment.Fragment_hoadon;
import com.example.duanmau_ps12545_lycaothanggd.Fragment.Fragment_sach;
import com.example.duanmau_ps12545_lycaothanggd.Fragment.Fragment_sachbanchay;
import com.example.duanmau_ps12545_lycaothanggd.Fragment.Fragment_thongke;
import com.example.duanmau_ps12545_lycaothanggd.Fragment.Fragment_thu;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dr_ly;
    Toolbar tb;
    NavigationView nv;
    ActionBarDrawerToggle drawerToggle;
//    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////        viewPager=findViewById(R.id.ViewPager);
////        addTabs(viewPager);
//        //Chuyen sang tab layout
//        ((TabLayout)findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);



        dr_ly=findViewById(R.id.dr_ly);
        tb=findViewById(R.id.tg_bar);
        nv=findViewById(R.id.nv_view);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerToggle=setupDrawerToogle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        dr_ly.addDrawerListener(drawerToggle);

        if(savedInstanceState == null){
            nv.setCheckedItem(R.id.nguoidung);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thu()).commit();
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragments=null;
//                Class fragmentClass = null;
                switch (item.getItemId()){
                    case R.id.nguoidung:
//                    fragmentClass = Fragment_thu.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thu()).commit();
                        break;
                    case R.id.theloai:
//                        fragmentClass = Fragment_chi.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_chi()).commit();
                        break;
                    case R.id.sach:
//                        fragmentClass = Fragment_chi.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_sach()).commit();
                        break;
                    case R.id.sachbanchay:
//                        fragmentClass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_sachbanchay()).commit();
                        break;
                    case R.id.hoadon:
//                        fragmentClass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_hoadon()).commit();
                        break;

                    case R.id.thongke:
//                        fragmentClass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thongke()).commit();
                        break;

//                    case R.id.gioithieu:
////                        Toast.makeText(MainActivity.this,"Đây là: giới thiệu",Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new KhoanTCFragment()).commit();
//                        break;

                    case R.id.thoat:
//                        Toast.makeText(MainActivity.this,"Đây là: Exit",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                        break;

//                    case R.id.hoadon:
////                        fragmentClass = Fragment_thongke.class;
//                        Toast.makeText(MainActivity.this,"Đây là: Hoa Don",Toast.LENGTH_SHORT).show();
//                        break;

                    default:
//                        fragmentClass=Fragment_thu.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thu()).commit();
                }


                item.setChecked(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });


    }



    //OPtion menu(Gắn menu vào ActionBar/ToolBar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
//        getMenuInflater().inflate(R.menu.search_menu,menu);
        return super.onCreateOptionsMenu(menu);



    }


    private ActionBarDrawerToggle setupDrawerToogle(){
        return new ActionBarDrawerToggle(MainActivity.this,dr_ly,tb,R.string.Open,R.string.Close);
    }


    //search



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        if(item.getItemId()==R.id.doimatkhau) {
            Toast.makeText(this, "OKE LET GO!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, DoiMatKhauActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }

//    public void addTabs(ViewPager viewPager){
//        TabAdapter adapter=new TabAdapter(getSupportFragmentManager());
//        adapter.add(new Fragment_hoadon(),"TAB 1");
//        adapter.add(new BlankFragmentHoaDonChiTiet(),"TAB 2");
//        viewPager.setAdapter(adapter);
//
//    }


}

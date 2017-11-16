package com.jeferson.proyecticartive.activities.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.fragments.PerfilFragment;
import com.jeferson.proyecticartive.activities.fragments.PrincipalFragment;
import com.jeferson.proyecticartive.activities.fragments.TicketsFragment;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    //private ActivityWithViewPagerBinding bind;
    //private VpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Fragment principalFragment = new PrincipalFragment();
        final Fragment ticketsFragment = new TicketsFragment();
        final Fragment perfilFragment = new PerfilFragment();

        //Configuracion del bottomnavigation
        BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bnve.enableAnimation(false);
        bnve.enableItemShiftingMode(true);

        //configurar tamañado de los iconos
        int iconSize =30;
        bnve.setIconSize(iconSize, iconSize);
        bnve.setItemHeight(BottomNavigationViewEx.dp2px(this, 55));
        //bnve.setTextVisibility(false);
        //Cambiar los fragments cuando se precionan
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, principalFragment).commit();
        }
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if(item.getItemId() == R.id.menu_ticket){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, ticketsFragment).commit();
                }else if (item.getItemId() == R.id.menu_perfil){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, perfilFragment).commit();
                }else if (item.getItemId() == R.id.menu_home){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, principalFragment).commit();
                }
                return true;
            }
        });
    }

}

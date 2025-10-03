package com.example.lab_git;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_screen1) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment1())
                        .commit();
                return true;
            }
            else if (id == R.id.nav_screen3) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment3())
                        .commit();
                return true;
            }
            return false;
        });

        // по умолчанию открываем первый экран
        bottomNav.setSelectedItemId(R.id.nav_screen1);
    }
}
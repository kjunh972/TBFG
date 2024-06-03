package com.tbfg;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_nav_menu {

    private Context context;

    public bottom_nav_menu(Context context) {
        this.context = context;
    }

    public void setOnNavigationItemSelectedListener(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navigation_home) {
                    Intent intent = new Intent(context, Favorite.class);
                    context.startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_dashboard) {
                    // "Dashboard" 아이템 클릭 시 실행할 동작 정의
                    return true;
                } else if (id == R.id.navigation_notifications) {
                    // "Notifications" 아이템 클릭 시 실행할 동작 정의
                    return true;
                } else if (id == R.id.navigation_settings) {
                    Intent intent = new Intent(context, timetable.class);
                    context.startActivity(intent);
                    return true;
                }

                return false;
            }
        });
    }
}

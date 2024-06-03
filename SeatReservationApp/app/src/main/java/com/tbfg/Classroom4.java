package com.tbfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Classroom4 extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private boolean[] isHeartFilled = new boolean[9]; // Assuming there are 9 buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom4);

        sharedPreferences = getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        loadFavorites();

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실 선택화면으로 이동
                Intent intent = new Intent(Classroom4.this, Campus.class);
                startActivity(intent);
            }
        });

        // 하트 아이콘을 변경하는 버튼 설정
        setHeartButtonListener(btn1, 0);
        setHeartButtonListener(btn2, 1);
        setHeartButtonListener(btn3, 2);
        setHeartButtonListener(btn4, 3);
        setHeartButtonListener(btn5, 4);
        setHeartButtonListener(btn6, 5);
        setHeartButtonListener(btn7, 6);
        setHeartButtonListener(btn8, 7);
        setHeartButtonListener(btn9, 8);
    }

    private void setHeartButtonListener(final Button button, final int index) {
        updateButtonDrawable(button, index);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 하트 상태 변경
                isHeartFilled[index] = !isHeartFilled[index];
                saveFavorite(index, isHeartFilled[index]);
                updateButtonDrawable(button, index);
            }
        });
    }

    private void updateButtonDrawable(Button button, int index) {
        if (isHeartFilled[index]) {
            button.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(Classroom4.this, R.drawable.heart_filled),
                    null, null, null);
        } else {
            button.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(Classroom4.this, R.drawable.heart_unfilled),
                    null, null, null);
        }
    }

    private void saveFavorite(int index, boolean isFilled) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("button_" + index, isFilled);
        editor.apply();
    }

    private void loadFavorites() {
        for (int i = 0; i < isHeartFilled.length; i++) {
            isHeartFilled[i] = sharedPreferences.getBoolean("button_" + i, false);
        }
    }
}

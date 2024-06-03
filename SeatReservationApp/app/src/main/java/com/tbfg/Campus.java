package com.tbfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Campus extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus);

        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실1 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom1.class);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실2 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom2.class);
                startActivity(intent);
            }
        });

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실3 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom3.class);
                startActivity(intent);
            }
        });

        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실4 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom4.class);
                startActivity(intent);
            }
        });

        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실5 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom5.class);
                startActivity(intent);
            }
        });

        Button btn6 = findViewById(R.id.button6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실6 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom6.class);
                startActivity(intent);
            }
        });

        Button btn7 = findViewById(R.id.button7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실7 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom7.class);
                startActivity(intent);
            }
        });

        Button btn8 = findViewById(R.id.button8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실8 선택화면으로 이동
                Intent intent = new Intent(Campus.this, Classroom8.class);
                startActivity(intent);
            }
        });



        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 교실 선택화면으로 이동
                Intent intent = new Intent(Campus.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
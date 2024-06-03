package com.tbfg;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Mypage extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewStudentID;
    private TextView textViewUsername;
    private TextView textViewDepartment;
    private TextView textViewYear;
    private TextView textViewClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage); // 레이아웃 파일과 연결합니다.

        textViewName = findViewById(R.id.textViewName);
        textViewStudentID = findViewById(R.id.textViewStudentID);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewDepartment = findViewById(R.id.textViewDepartment);
        textViewYear = findViewById(R.id.textViewYear);
        textViewClass = findViewById(R.id.textViewClass);

        // 사용자 정보를 설정하는 예시
        textViewName.setText("홍길동");
        textViewStudentID.setText("20201234");
        textViewUsername.setText("hong123");
        textViewDepartment.setText("컴퓨터공학과");
        textViewYear.setText("3학년");
        textViewClass.setText("A반");
    }
}

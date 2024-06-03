package com.tbfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        ImageView imStudent = findViewById(R.id.im_student);
        imStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignupStd(); // "im_student" 이미지 클릭 시 signup_std 액티비티로 이동
            }
        });

        ImageView imProfessor = findViewById(R.id.im_professor);
        imProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignupPeo(); // "im_professor" 이미지 클릭 시 signup_pro 액티비티로 이동
            }
        });
    }

    public void goToSignupStd() {
        Intent intent = new Intent(this, Signup_Std.class);
        intent.putExtra("position", "student"); // position 값을 전달
        startActivity(intent);
    }

    public void goToSignupPeo() {
        Intent intent = new Intent(this, Signup_Pro.class);
        intent.putExtra("position", "professor"); // position 값을 전달
        startActivity(intent);
    }
}


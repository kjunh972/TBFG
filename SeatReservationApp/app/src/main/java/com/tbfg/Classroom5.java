package com.tbfg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Classroom5 extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private boolean[] isHeartFilled = new boolean[9]; // 9개의 버튼이 있다고 가정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom5);

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);
        loadFavorites(); // 즐겨찾기 정보 불러오기

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
                Intent intent = new Intent(Classroom5.this, Campus.class);
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

                // 클릭한 버튼의 정보를 서버로 전송하여 저장
                sendDataToServer(index, isHeartFilled[index]);
            }
        });
    }

    private void updateButtonDrawable(Button button, int index) {
        if (isHeartFilled[index]) {
            // 하트가 채워진 이미지 설정
            button.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(Classroom5.this, R.drawable.heart_filled),
                    null, null, null);
        } else {
            // 빈 하트 이미지 설정
            button.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(Classroom5.this, R.drawable.heart_unfilled),
                    null, null, null);
        }
    }

    private void saveFavorite(int index, boolean isFilled) {
        // 즐겨찾기 정보 저장
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("button_" + index, isFilled);
        editor.apply();
    }

    private void loadFavorites() {
        // 즐겨찾기 정보 불러오기
        for (int i = 0; i < isHeartFilled.length; i++) {
            isHeartFilled[i] = sharedPreferences.getBoolean("button_" + i, false);
        }
    }

    private void sendDataToServer(int index, boolean isFilled) {
        // 서버로 전송할 데이터 준비
        Map<String, String> postData = new HashMap<>();
        postData.put("user_id", "로그인한 사용자의 ID");
        postData.put("classroom_num", String.valueOf(index));
        postData.put("is_filled", String.valueOf(isFilled));

        // 서버로 데이터를 전송하는 코드 작성
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 서버 주소
                    URL url = new URL("http://10.0.2.2/favorite.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    // 데이터 전송
                    StringBuilder postDataString = new StringBuilder();
                    for (Map.Entry<String, String> entry : postData.entrySet()) {
                        if (postDataString.length() != 0) {
                            postDataString.append('&');
                        }
                        postDataString.append(entry.getKey());
                        postDataString.append('=');
                        postDataString.append(entry.getValue());
                    }
                    byte[] postDataBytes = postDataString.toString().getBytes("UTF-8");
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(postDataBytes);
                    os.flush();
                    os.close();

                    // 서버 응답을 확인
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // 성공적으로 저장되었음을 알림
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Classroom5.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // 저장 실패
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Classroom5.this, "저장에 실패하였습니다", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // 예외 발생 시 에러 메시지 출력
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Classroom5.this, "저장에 실패하였습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

}



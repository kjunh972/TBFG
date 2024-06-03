package com.tbfg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log; // 로그 출력을 위한 import 추가
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername = findViewById(R.id.getId);
        editTextPassword = findViewById(R.id.getPw);
    }

    public void onLoginButtonClick(View view) {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        new LoginTask().execute(username, password);
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String result = "";

            try {
                URL url = new URL("http://10.0.2.2:80/login.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String postData = "id=" + username + "&password=" + password;
                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    result += line;
                }
                br.close();

            } catch (Exception e) {
                Log.e("LoginError", "Login request failed", e); // 오류 로그 추가
                result = "Exception: " + e.getMessage();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("LoginResult", result); // 결과 로그 추가
            if (result.equals("Login successful")) {
                Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

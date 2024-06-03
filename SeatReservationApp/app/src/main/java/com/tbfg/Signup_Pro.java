package com.tbfg;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup_Pro extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private EditText editTextSchool, editTextDepartment, editTextName;
    private Button buttonRegister;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_pro);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextSchool = findViewById(R.id.editTextSchool);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        editTextName = findViewById(R.id.editTextName);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Intent에서 position 값을 받아옵니다.
        position = getIntent().getStringExtra("position");

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClicked(v);
            }
        });
    }

    public void onRegisterClicked(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String school = editTextSchool.getText().toString();
        String department = editTextDepartment.getText().toString();
        String name = editTextName.getText().toString();

        if (password.equals(confirmPassword)) {
            new RegisterUserTask().execute(username, password, school, position, department, name);
        } else {
            Toast.makeText(Signup_Pro.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    private class RegisterUserTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String school = params[2];
            String position = params[3];
            String department = params[4];
            String name = params[5];

            try {
                URL url = new URL("http://10.0.2.2/register.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                String postData = "username=" + username + "&password=" + password + "&school=" + school +
                        "&position=" + position + "&department=" + department + "&name=" + name;

                OutputStream os = httpURLConnection.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                // 회원가입이 성공하면 true 반환
                return sb.toString().contains("회원가입이 완료되었습니다.");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(Signup_Pro.this, "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Signup_Pro.this, MainActivity.class); // LoginActivity로 이동
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Signup_Pro.this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}

package kr.co.softcampus.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextId, editTextPw;
    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // EditText 연결
        editTextId = findViewById(R.id.editTextId);
        editTextPw = findViewById(R.id.editTextPw);

        // 버튼 연결
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        // 로그인 버튼 클릭 이벤트
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString().trim();
                String pw = editTextPw.getText().toString().trim();

                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "ID와 PW를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 로그인 성공 가정 후 다음 화면으로 이동
                    Intent intent = new Intent(LoginActivity.this, ExerciseRoutineActivity.class);
                    intent.putExtra("userId", id);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // 회원가입 버튼 클릭 이벤트
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}

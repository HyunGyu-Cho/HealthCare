package kr.co.softcampus.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText, ageEditText, heightEditText, weightEditText,
            bmrEditText, idEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // EditText 연결
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        bmrEditText = findViewById(R.id.bmrEditText);
        idEditText = findViewById(R.id.idEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        // 회원가입 버튼
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String age = ageEditText.getText().toString().trim();
                String height = heightEditText.getText().toString().trim();
                String weight = weightEditText.getText().toString().trim();
                String bmr = bmrEditText.getText().toString().trim();
                String id = idEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                // 입력값 검증
                if (name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()
                        || bmr.isEmpty() || id.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "모든 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 사용자 정보 저장 처리 (여기서는 간단히 출력으로 처리)
                System.out.println("회원가입 정보: ");
                System.out.println("이름: " + name);
                System.out.println("나이: " + age);
                System.out.println("키: " + height);
                System.out.println("몸무게: " + weight);
                System.out.println("기초대사량: " + bmr);
                System.out.println("ID: " + id);
                System.out.println("비밀번호: " + password);

                // 회원가입 완료 후 로그인 화면으로 이동
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

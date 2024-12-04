package kr.co.softcampus.healthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExerciseRoutineActivity extends AppCompatActivity {

    private TextView greetingTextView, dateTextView, routineTextView;
    private Spinner exercisePartSpinner;

    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_routine);

        dbHelper = new SQLiteHelper(this);

        greetingTextView = findViewById(R.id.greetingTextView);
        dateTextView = findViewById(R.id.dateTextView);
        exercisePartSpinner = findViewById(R.id.exercisePartSpinner);
        routineTextView = findViewById(R.id.routineTextView);

        String userId = getIntent().getStringExtra("userId");
        greetingTextView.setText("반갑습니다, " + userId + "님");

        String currentDate = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(new Date());
        dateTextView.setText(currentDate);

        String[] exerciseParts = {"등", "어깨", "가슴", "하체"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, exerciseParts);
        exercisePartSpinner.setAdapter(adapter);

        // Spinner 선택 이벤트: 선택된 운동 부위에 맞는 루틴을 표시
        exercisePartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPart = exerciseParts[position];
                String routine = dbHelper.getRoutine(selectedPart);
                if (routine != null) {
                    routineTextView.setText(routine);
                } else {
                    routineTextView.setText(selectedPart + "에 대한 루틴이 없습니다.");
                    Toast.makeText(ExerciseRoutineActivity.this, "해당 부위에 저장된 루틴이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                routineTextView.setText("");
            }
        });
    }
}

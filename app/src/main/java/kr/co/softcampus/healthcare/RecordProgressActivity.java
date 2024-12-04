package kr.co.softcampus.healthcare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RecordProgressActivity extends AppCompatActivity {

    private TextView exercisePartTextView;
    private LinearLayout routineListLayout;
    private Button saveButton;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_progress);

        // View 연결
        exercisePartTextView = findViewById(R.id.exercisePartTextView);
        routineListLayout = findViewById(R.id.routineListLayout);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new SQLiteHelper(this);

        // Intent로 전달된 운동 부위
        String exercisePart = getIntent().getStringExtra("exercisePart");
        exercisePartTextView.setText("운동 부위: " + exercisePart);

        // 현재 날짜
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // 운동 루틴 동적 로드 (DB에서 해당 부위의 루틴 가져오기)
        String routine = dbHelper.getRoutine(exercisePart);
        if (routine != null) {
            String[] routines = routine.split(";"); // 루틴을 ';'로 구분해서 저장했다고 가정
            for (String item : routines) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(item);
                routineListLayout.addView(checkBox);
            }
        } else {
            Toast.makeText(this, "루틴이 없습니다.", Toast.LENGTH_SHORT).show();
        }

        // 저장 버튼 클릭 이벤트
        saveButton.setOnClickListener(v -> {
            StringBuilder completedRoutines = new StringBuilder();
            for (int i = 0; i < routineListLayout.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) routineListLayout.getChildAt(i);
                if (checkBox.isChecked()) {
                    completedRoutines.append(checkBox.getText().toString()).append(";");
                }
            }

            // 체크된 루틴 저장
            if (completedRoutines.length() > 0) {
                dbHelper.saveProgress(currentDate, exercisePart, completedRoutines.toString());
                Toast.makeText(this, "운동 기록이 저장되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "완료된 운동이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

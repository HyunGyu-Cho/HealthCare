package kr.co.softcampus.healthcare;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class RecommendationActivity extends AppCompatActivity {

    private TextView recommendationTextView;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        recommendationTextView = findViewById(R.id.recommendationTextView);
        dbHelper = new SQLiteHelper(this);

        Map<String, String> recommendations = generateRecommendations();

        StringBuilder recommendationText = new StringBuilder();
        for (Map.Entry<String, String> entry : recommendations.entrySet()) {
            recommendationText.append(entry.getKey()).append(" 추천 루틴:\n")
                    .append(entry.getValue()).append("\n\n");
        }

        recommendationTextView.setText(recommendationText.toString());
    }

    private Map<String, String> generateRecommendations() {
        Map<String, String> recommendations = new HashMap<>();
        String[] parts = {"등", "어깨", "가슴", "하체"};

        for (String part : parts) {
            String routine = dbHelper.getRoutine(part);
            recommendations.put(part, routine != null ? routine : "추천 운동이 없습니다.");
        }

        return recommendations;
    }
}

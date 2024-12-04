package kr.co.softcampus.healthcare;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CustomRoutineActivity extends AppCompatActivity {

    private Spinner backSpinner, shoulderSpinner, chestSpinner, legSpinner;
    private Button saveButton;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_routine);

        dbHelper = new SQLiteHelper(this);

        backSpinner = findViewById(R.id.backSpinner);
        shoulderSpinner = findViewById(R.id.shoulderSpinner);
        chestSpinner = findViewById(R.id.chestSpinner);
        legSpinner = findViewById(R.id.legSpinner);
        saveButton = findViewById(R.id.saveButton);

        fetchExerciseData(); // OpenAPI에서 데이터 가져오기

        saveButton.setOnClickListener(v -> {
            String backExercise = backSpinner.getSelectedItem().toString();
            String shoulderExercise = shoulderSpinner.getSelectedItem().toString();
            String chestExercise = chestSpinner.getSelectedItem().toString();
            String legExercise = legSpinner.getSelectedItem().toString();

            dbHelper.saveRoutine("등", backExercise);
            dbHelper.saveRoutine("어깨", shoulderExercise);
            dbHelper.saveRoutine("가슴", chestExercise);
            dbHelper.saveRoutine("하체", legExercise);

            Toast.makeText(this, "루틴이 저장되었습니다!", Toast.LENGTH_SHORT).show();
        });
    }

    private void fetchExerciseData() {
        String url = "https://api.example.com/exercises"; // OpenAPI URL 수정

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        List<String> exerciseList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            exerciseList.add(jsonArray.getJSONObject(i).getString("name"));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, exerciseList);
                        backSpinner.setAdapter(adapter);
                        shoulderSpinner.setAdapter(adapter);
                        chestSpinner.setAdapter(adapter);
                        legSpinner.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.e("API_ERROR", "Error fetching data: " + error.getMessage())
        );
        queue.add(stringRequest);
    }
}

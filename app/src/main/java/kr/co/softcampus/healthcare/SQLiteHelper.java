package kr.co.softcampus.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HealthCareDB";
    private static final int DATABASE_VERSION = 1;

    // 테이블 이름
    private static final String TABLE_ROUTINES = "Routines";
    private static final String TABLE_PROGRESS = "Progress";

    // 공통 컬럼
    private static final String COLUMN_ID = "id";

    // Routines 테이블 컬럼
    private static final String COLUMN_PART = "part"; // 운동 부위 (예: 등, 어깨)
    private static final String COLUMN_ROUTINE = "routine"; // 운동 루틴

    // Progress 테이블 컬럼
    private static final String COLUMN_DATE = "date"; // 운동 날짜
    private static final String COLUMN_COMPLETED_ROUTINES = "completed_routines"; // 완료된 루틴

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Routines 테이블 생성
        String createRoutinesTable = "CREATE TABLE " + TABLE_ROUTINES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PART + " TEXT UNIQUE, " +
                COLUMN_ROUTINE + " TEXT)";
        db.execSQL(createRoutinesTable);

        // Progress 테이블 생성
        String createProgressTable = "CREATE TABLE " + TABLE_PROGRESS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_PART + " TEXT, " +
                COLUMN_COMPLETED_ROUTINES + " TEXT)";
        db.execSQL(createProgressTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블 삭제 후 재생성
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRESS);
        onCreate(db);
    }

    // -------------------- 루틴 관리 메서드 -------------------- //

    // 루틴 저장
    public void saveRoutine(String part, String routine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PART, part);
        values.put(COLUMN_ROUTINE, routine);

        // 이미 존재하는 경우 업데이트
        db.insertWithOnConflict(TABLE_ROUTINES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    // 특정 운동 부위의 루틴 가져오기
    public String getRoutine(String part) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ROUTINES, new String[]{COLUMN_ROUTINE},
                COLUMN_PART + "=?", new String[]{part}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String routine = cursor.getString(0);
            cursor.close();
            return routine;
        }
        return null; // 해당 부위에 루틴이 없는 경우
    }

    // 루틴 수정
    public void updateRoutine(String part, String updatedRoutine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROUTINE, updatedRoutine);

        db.update(TABLE_ROUTINES, values, COLUMN_PART + "=?", new String[]{part});
        db.close();
    }

    // 모든 루틴 삭제 (디버깅 또는 테스트용)
    public void deleteAllRoutines() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ROUTINES, null, null);
        db.close();
    }

    // -------------------- 운동 진행 기록 관리 메서드 -------------------- //

    // 운동 진행 데이터 저장
    public void saveProgress(String date, String part, String completedRoutines) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_PART, part);
        values.put(COLUMN_COMPLETED_ROUTINES, completedRoutines);

        db.insert(TABLE_PROGRESS, null, values);
        db.close();
    }

    // 특정 날짜의 운동 기록 조회
    public Cursor getProgress(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PROGRESS, null, COLUMN_DATE + "=?", new String[]{date}, null, null, null);
    }

    // 특정 날짜와 운동 부위의 진행 기록 조회
    public String getProgressForPart(String date, String part) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROGRESS, new String[]{COLUMN_COMPLETED_ROUTINES},
                COLUMN_DATE + "=? AND " + COLUMN_PART + "=?", new String[]{date, part}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String completedRoutines = cursor.getString(0);
            cursor.close();
            return completedRoutines;
        }
        return null; // 해당 날짜와 부위의 기록이 없는 경우
    }

    // 모든 운동 기록 삭제 (테스트용)
    public void deleteAllProgress() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRESS, null, null);
        db.close();
    }
}

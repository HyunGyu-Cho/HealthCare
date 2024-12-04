# HealthCare 애플리케이션

## 📋 프로젝트 설명
HealthCare는 Java로 개발된 Android 애플리케이션입니다. 사용자가 운동 루틴을 효과적으로 관리할 수 있도록 로그인, 오늘의 운동 루틴, 운동 진행 기록, 나만의 운동 루틴 생성, 추천 운동 루틴 제공 등의 기능을 제공합니다.

---


## 🛠️ 개발 환경
- **플랫폼**: Android (Native)
- **언어**: Java
- **IDE**: Android Studio

---


## 🛠️ 주요 기능
### 1. **로그인 및 회원가입**
- 사용자는 ID와 비밀번호를 입력하여 로그인할 수 있습니다.
- 로그인 성공 시 "ExerciseRoutineActivity" 화면으로 이동합니다.

### 2. **오늘의 운동 루틴**
- 사용자의 이름과 현재 날짜를 표시합니다.
- 오늘의 운동 부위(예: 등, 어깨, 가슴, 하체)를 랜덤으로 선택하여 표시합니다.
- 선택된 운동 부위에 맞는 운동 루틴을 보여줍니다.
  - 예:
    ```
    데드리프트 5개 x 5세트
    풀업 10개 x 3세트
    ```

### 3. **운동 진행 기록**
- 오늘의 운동 루틴에 있는 운동을 한 줄씩 표시하며, 각 운동 옆에 체크박스가 제공됩니다.
- 사용자는 완료한 운동을 체크할 수 있습니다.

### 4. **나만의 운동 루틴 만들기**
- [공공데이터 포털 Open API](https://www.data.go.kr/data/15068730/fileData.do#tab-layer-openapi) 데이터를 활용하여 운동 종목을 선택할 수 있습니다.
- 운동 부위별로 나만의 루틴을 생성할 수 있습니다:
  - 루틴 1. 등
  - 루틴 2. 어깨
  - 루틴 3. 가슴
  - 루틴 4. 하체

### 5. **추천 운동 루틴**
- 각 운동 부위에 대한 추천 운동 루틴을 제공합니다.
  - 예:
    ```
    등 추천 운동 루틴:
    - 데드리프트 5개 x 5세트
    - 바벨로우 8개 x 4세트
    ```


---

## 📂 프로젝트 구조
HealthCareApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/
│   │   │   │   │   ├── healthcare/
│   │   │   │   │   │   ├── LoginActivity.java                 # 로그인 화면
│   │   │   │   │   │   ├── ExerciseRoutineActivity.java       # 오늘의 운동 루틴 화면
│   │   │   │   │   │   ├── RecordProgressActivity.java        # 운동 진행 기록 화면
│   │   │   │   │   │   ├── CustomRoutineActivity.java         # 나만의 운동 루틴 생성 화면
│   │   │   │   │   │   ├── RecommendationActivity.java        # 추천 운동 루틴 화면
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_login.xml                     # 로그인 화면 레이아웃
│   │   │   │   │   ├── activity_exercise_routine.xml          # 오늘의 운동 루틴 레이아웃
│   │   │   │   │   ├── activity_record_progress.xml           # 운동 진행 기록 레이아웃
│   │   │   │   │   ├── activity_custom_routine.xml            # 나만의 운동 루틴 생성 레이아웃
│   │   │   │   │   ├── activity_recommendation.xml            # 추천 운동 루틴 레이아웃
│   │   │   ├── AndroidManifest.xml                            # 앱 구성 및 액티비티 선언 파일
├── build.gradle                                               # 모듈 수준 Gradle 설정
├── settings.gradle                                            # 프로젝트 설정 파일



---

## 🚀 실행 방법
1. Android Studio에서 프로젝트를 열고 Gradle Sync를 실행합니다.
2. 애플리케이션을 에뮬레이터 또는 실제 디바이스에서 실행합니다.
3. 다음 기능을 테스트할 수 있습니다:
   - 로그인 및 회원가입
   - 오늘의 운동 루틴 확인
   - 운동 진행 여부 기록
   - 나만의 운동 루틴 생성
   - 추천 운동 루틴 확인

---

## 📌 참고 사항
- 공공데이터 포털에서 제공하는 Open API를 활용하여 운동 데이터를 가져옵니다.
- 데이터베이스는 SQLite를 활용하여 운동 기록과 사용자 설정을 저장합니다.


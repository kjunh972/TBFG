package com.tbfg;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class timetable extends AppCompatActivity {
    // '추가' 버튼
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        // 하단 네비게이션 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottom_nav_menu bottomNavMenu = new bottom_nav_menu(this);
        bottomNavMenu.setOnNavigationItemSelectedListener(bottomNavigationView);
        // 현재 Activity에 맞는 아이템을 선택
        //  bottomNavigationView.setSelectedItemId(R.id.navigation_settings);




        // 레이아웃에서 '추가' 버튼을 찾기
        btnAdd = findViewById(R.id.btn_add);

        // '추가' 버튼 클릭 이벤트 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(); // 팝업 창 표시 메서드 호출
            }
        });
    }

    // 팝업 창을 표시하는 메서드
    private void showPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup_layout, null); // 팝업 레이아웃을 가져오기
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show(); // 팝업 창 표시

        // 팝업 레이아웃에서 필요한 UI 요소들 찾기
        EditText editRoomNumber = view.findViewById(R.id.room);
        EditText editSubjectName = view.findViewById(R.id.subject);
        Spinner spinnerStartTime = view.findViewById(R.id.start_time_spinner);
        Spinner spinnerEndTime = view.findViewById(R.id.end_time_spinner);
        Spinner spinnerDay = view.findViewById(R.id.spinner_day);

        // 리소스에서 시간 배열 가져오기
        String[] startTimeArray = getResources().getStringArray(R.array.time_array);
        String[] endTimeArray = getResources().getStringArray(R.array.time_array);

        // 시작 시간 스피너에 어댑터 설정
        ArrayAdapter<String> startTimeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startTimeArray);
        startTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartTime.setAdapter(startTimeAdapter);

        // 종료 시간 스피너에 어댑터 설정
        ArrayAdapter<String> endTimeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endTimeArray);
        endTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEndTime.setAdapter(endTimeAdapter);

        // 팝업 창의 '확인' 버튼 클릭 이벤트 설정
        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 값들 가져오기
                String roomNumber = editRoomNumber.getText().toString();
                String subjectName = editSubjectName.getText().toString();
                String startTime = spinnerStartTime.getSelectedItem().toString();
                String endTime = spinnerEndTime.getSelectedItem().toString();
                String day = spinnerDay.getSelectedItem().toString();

                // 입력 검증
                if (roomNumber.isEmpty() || subjectName.isEmpty()) {
                    // 강의실 호수와 과목명이 빈칸인 경우 경고 메시지 표시
                    Toast.makeText(timetable.this, "강의실 호수와 과목명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (isEndTimeBeforeStartTime(startTime, endTime)) {
                    // 종료 시간이 시작 시간보다 이른 경우 경고 메시지 표시
                    Toast.makeText(timetable.this, "종료 시간이 시작 시간보다 이릅니다. 다시 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 시간표 셀에 정보를 표시하는 메서드 호출
                    displayInfoToCell(day, roomNumber, subjectName, startTime, endTime);
                    dialog.dismiss(); // 팝업 창 닫기
                }
            }
        });
    }

    // 시작 시간과 종료 시간을 비교하여 종료 시간이 더 이른지 확인하는 메서드
    private boolean isEndTimeBeforeStartTime(String startTime, String endTime) {
        // 시간을 "HH:MM" 형식에서 분리하여 각각의 시(hour)와 분(minute)으로 나눔
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");
        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);

        // 종료 시간이 시작 시간보다 이른지 판단
        if (endHour < startHour) {
            return true;
        } else if (endHour == startHour && endMinute <= startMinute) {
            return true;
        }
        return false;
    }

    // 시간표 셀에 정보를 표시하는 메서드
    private void displayInfoToCell(String day, String room, String subject, String startTime, String endTime) {
        // 해당하는 요일과 시간에 따라 시간표에 정보 표시하는 로직을 작성하세요.
        // 예: findViewById(R.id.some_view).setText(subject + " (" + room + ")");
    }
}

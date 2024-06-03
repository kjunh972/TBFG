package com.tbfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;

public class Reservation extends AppCompatActivity {

    private boolean isReserved = false;
    private String selectedSeat = "";
    private Button lastClickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reservation.this, Time.class));
            }
        });

        // 좌석 버튼 배열
        Button[] seatButtons = new Button[]{
                findViewById(R.id.seat1Button), findViewById(R.id.seat2Button), findViewById(R.id.seat3Button),
                findViewById(R.id.seat4Button), findViewById(R.id.seat5Button), findViewById(R.id.seat6Button),
                findViewById(R.id.seat7Button), findViewById(R.id.seat8Button), findViewById(R.id.seat9Button),
                findViewById(R.id.seat10Button), findViewById(R.id.seat11Button), findViewById(R.id.seat12Button),
                findViewById(R.id.seat13Button), findViewById(R.id.seat14Button), findViewById(R.id.seat15Button),
                findViewById(R.id.seat16Button), findViewById(R.id.seat17Button), findViewById(R.id.seat18Button),
                findViewById(R.id.seat19Button), findViewById(R.id.seat20Button), findViewById(R.id.seat21Button),
                findViewById(R.id.seat22Button), findViewById(R.id.seat23Button), findViewById(R.id.seat24Button)
        };

        // 좌석 버튼에 대한 클릭 이벤트 처리
        for (int i = 0; i < seatButtons.length; i++) {
            final Button seatButton = seatButtons[i];
            final int seatNumber = i + 1;
            seatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 이미 예약된 좌석인지 확인
                    if (isReserved) {
                        // 이미 예약된 좌석이면 클릭 이벤트 무시
                        Toast.makeText(Reservation.this, "이미 선택된 좌석입니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    selectedSeat = "Seat " + seatNumber;
                    reserveSeat();
                    // 선택된 좌석의 배경색 변경
                    seatButton.setBackgroundColor(getResources().getColor(R.color.sky_blue));
                    lastClickedButton = seatButton;
                }
            });
        }
    }

    private void reserveSeat() {
        isReserved = true;
        Toast.makeText(this, selectedSeat + "이(가) 예약되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public void onBackButtonClick(View view) {
        if (isReserved) {
            Toast.makeText(this, selectedSeat + "이(가) 예약된 좌석입니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "예약이 안 된 좌석입니다.", Toast.LENGTH_SHORT).show();
        }
        onBackPressed();
    }
}

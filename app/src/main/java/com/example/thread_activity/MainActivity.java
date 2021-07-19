package com.example.thread_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//쓰레드란 백그라운드에서 작동할 수 있는 기능을 말한다.
//홈버튼을 눌렀을 때 라던지
public class MainActivity extends AppCompatActivity {

    Button btn_start,btn_stop;
    Thread thread;
    boolean isThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //쓰레드 시작
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = true;
                thread = new Thread(){
                  public void run(){
                      while(isThread){
                          //처음엔 sleep만 적고 try catch문으로 바꾸기
                          try {
                              sleep(5000);//1000msec = 1sec
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                          handler.sendEmptyMessage(0);//5초마다 이게 실행된다
                      }
                  }

                };
                thread.start();
            }
        });

        btn_stop=(Button)findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = false;
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(),"핸들러",Toast.LENGTH_LONG).show();
        }
    };
}
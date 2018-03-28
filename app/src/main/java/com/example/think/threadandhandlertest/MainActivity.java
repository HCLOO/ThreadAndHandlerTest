package com.example.think.threadandhandlertest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    Handler handler;
    Thread thread;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler=new MyHandler();
                thread=new MyThread();
                thread.start();
            }
        });
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            //休眠2s后再运行
            try {
                thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg=new Message();
            msg.what=1;
            //延迟2s后再运行
            handler.sendMessageDelayed(msg,2000);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    ++i;
                    tv.setText("线程通信功能实现"+i+"次!");
                    break;
                    default:break;
            }
        }
    }
}

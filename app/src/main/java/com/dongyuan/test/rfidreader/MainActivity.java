package com.dongyuan.test.rfidreader;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.speedata.libuhf.IUHFService;
import com.speedata.libuhf.UHFManager;



public class MainActivity extends AppCompatActivity {

    private IUHFService iuhfService;
    private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.iuhfService= UHFManager.getUHFService(MainActivity.this);
        this.iuhfService.OpenDev();

        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=!flag;
                if(flag) {
                    MainActivity.this.iuhfService.inventory_start(new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if(msg.what==1)
                            System.out.print(msg.obj);
                        }
                    });
                }else{
                    iuhfService.inventory_stop();
                }

            }
        });

    }
}

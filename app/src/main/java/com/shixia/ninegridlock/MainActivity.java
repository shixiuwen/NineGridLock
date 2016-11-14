package com.shixia.ninegridlock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.shixia.ninegridlock.view.NinePointLockView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NinePointLockView nplvLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nplvLockView = (NinePointLockView) findViewById(R.id.nplv_lock_view);

        nplvLockView.setOnDrawLickPathListener(new NinePointLockView.OnDrawLockPathListener() {
            @Override
            public void onDrawPathFinish(List<Integer> pathList) {
                if (pathList != null) {
                    StringBuilder builder = new StringBuilder();
                    for (Integer integer : pathList) {
                        builder.append(integer);
                    }
                    Toast.makeText(MainActivity.this, "连接的点序列为：" + builder.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "请至少连接4个点", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

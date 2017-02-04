package com.shixia.ninegridlock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.shixia.ninegridlock.view.NinePointLockView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NinePointLockView nplvLockView;
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nplvLockView = (NinePointLockView) findViewById(R.id.nplv_lock_view);

        nplvLockView.setOnDrawLickPathListener(new NinePointLockView.OnDrawLockPathListener() {
            @Override
            public boolean onDrawPathFinish(List<Integer> pathList) {
                if (pathList != null) {
                    StringBuilder builder = new StringBuilder();
                    for (Integer integer : pathList) {
                        builder.append(integer);
                    }
                    //同上一次密码比对
                    if (TextUtils.isEmpty(password)) {      //本地未存储密码，本次操作为新录入密码
                        Toast.makeText(MainActivity.this, "连接的点序列为：" + builder.toString(), Toast.LENGTH_SHORT).show();
                        password = builder.toString();
                        return true;
                    } else {        //本地存储了密码，进行匹配
                        if (!TextUtils.equals(password, builder.toString())) {  //密码匹配失败，显示错误路径，本地密码置空
                            Toast.makeText(MainActivity.this, "密码错误，已清空密码，请重置，当前路径：" + builder.toString(), Toast.LENGTH_SHORT).show();
                            password = null;
                            return false;
                        } else {    //密码匹配成功
                            Toast.makeText(MainActivity.this, "密码正确" + builder.toString(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请至少连接4个点", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });

    }
}

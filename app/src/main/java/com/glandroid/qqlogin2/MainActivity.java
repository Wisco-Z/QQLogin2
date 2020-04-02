package com.glandroid.qqlogin2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText et_qqnumber;
    private EditText et_passwd;
    private CheckBox cb_remember;

    /**
     * 1.定义一个共享参数(存放数据方便的api)
     */
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2.通过上下文得到一个共享参数的实例对象
        sp = this.getSharedPreferences("config", this.MODE_PRIVATE);

        et_qqnumber = (EditText) findViewById(R.id.et_qqnumber);
        et_passwd = (EditText) findViewById(R.id.et_passwd);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);
        restoreInfo();
    }

    /**
     * 从sp文件当中读取信息
     */
    private void restoreInfo() {
        String qq = sp.getString("qq", "");
        String password = sp.getString("password", "");
        et_qqnumber.setText(qq);
        et_passwd.setText(password);
    }

    /**
     * 登录按钮的点击事件
     *
     * @param view
     */
    public void login(View view) {
        String qq = et_qqnumber.getText().toString().trim();
        String password = et_passwd.getText().toString().trim();

        if (TextUtils.isEmpty(qq) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // 判断是否需要记录用户名和密码
            if (cb_remember.isChecked()) {
                // 被选中状态，需要记录用户名和密码
                // 3.将数据保存到sp文件中
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("qq", qq);
                editor.putString("password", password);
                editor.commit();// 提交数据，类似关闭流，事务
            }
            // 登录操作，模拟登录，数据应该提交给服务器比较是否正确
            if ("10000".equals(qq) && "123456".equals(password)) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

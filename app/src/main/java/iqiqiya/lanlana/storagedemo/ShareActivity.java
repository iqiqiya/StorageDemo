package iqiqiya.lanlana.storagedemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import iqiqiya.lanlana.sharedpreferences.R;

/**
 * Author: iqiqiya
 * Date: 2019/10/14
 * Time: 19:33
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class ShareActivity extends AppCompatActivity {

    private EditText accEdt;
    private EditText pwdEdt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initUI();
    }
    private void initUI(){
        accEdt = findViewById(R.id.acc_edt);
        pwdEdt = findViewById(R.id.pwd_edt);
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.获取两个输入框的内容
                String account = accEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();

                if (account.equals("iqiqiya") && pwd.equals("123456")){
                    //111获取SharedPreference对象(参数1：文件名  参数2：模式)
                    SharedPreferences sharedPreferences = getSharedPreferences("myshare",MODE_PRIVATE);
                    //222获取Editer对象
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //333存储信息
                    editor.putString("account",account);
                    editor.putString("pwd",pwd);
                    //444指定提交操作
                    editor.commit();

                    Toast.makeText(ShareActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ShareActivity.this,"账号或密码错误，请重试",Toast.LENGTH_SHORT).show();
                }
                //2.验证
                    //2.1验证成功，存储信息到SharePreference
                    //2.2验证失败，弹出提示
            }
        });
    }
}

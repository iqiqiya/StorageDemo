package iqiqiya.lanlana.storagedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import iqiqiya.lanlana.StroageDemo.R;

/**
 * Author: iqiqiya
 * Date: 2019/10/14
 * Time: 20:21
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class ExternalActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText infoEdt;
    private TextView textView;
    private String TAG = "test";
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        initUI();
    }

    private void initUI(){
        infoEdt = findViewById(R.id.info_edit);
        textView = findViewById(R.id.textView);
        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.read_btn).setOnClickListener(this);

        //进入界面直接开始动态请求权限
        int permisson = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permisson!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            //TODO
        }
    }

    @Override
    public void onClick(View v) {

        //获取存储位置
        path = Environment.getExternalStorageDirectory().getPath() + "/iqiqiya.txt";
        Log.e(TAG, path);
        switch (v.getId()) {
            case R.id.save_btn:
                Log.d("lujing", "走到这里");
                File f = new File(path);
                try {
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(path, true);
                    String str = infoEdt.getText().toString();
                    fos.write(str.getBytes());
                    fos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                break;
            case R.id.read_btn:
                try {
                    FileInputStream fis = new FileInputStream(path);
                    byte[] bytes = new byte[1024];
                    try {
                        int len = fis.read(bytes);
                        String str2 = new String(bytes,0,len);
                        textView.setText(str2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

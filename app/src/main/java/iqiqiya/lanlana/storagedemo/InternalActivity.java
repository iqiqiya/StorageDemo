package iqiqiya.lanlana.storagedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import iqiqiya.lanlana.StroageDemo.R;

/**
 * Author: iqiqiya
 * Date: 2019/10/14
 * Time: 20:22
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class InternalActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt;
    private TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        initUI();
    }
    private void initUI(){
        edt = findViewById(R.id.iedit_text);
        txt = findViewById(R.id.itext_view);
        findViewById(R.id.iread_btn).setOnClickListener(this);
        findViewById(R.id.isave_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //getFilesDir() --> data/data/包名/files
        //getCacheDir() --> data/data/包名/cache
        File file = new File(getFilesDir(),"iqiqiya1.txt");
        switch (v.getId()){
            case R.id.isave_btn:
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(edt.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iread_btn:
                try{
                    FileInputStream fis = new FileInputStream(file);
                    byte[] bytes = new byte[1024];
                    try {
                        int len = fis.read(bytes);
                        String str2 = new String(bytes,0,len);
                        txt.setText(str2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                break;
        }
    }
}

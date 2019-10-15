package iqiqiya.lanlana.storagedemo;

import androidx.appcompat.app.AppCompatActivity;
import iqiqiya.lanlana.StroageDemo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI(){

        findViewById(R.id.share_btn).setOnClickListener(this);
        findViewById(R.id.external_btn).setOnClickListener(this);
        findViewById(R.id.internal_btn).setOnClickListener(this);
    }

    public void onClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.share_btn:
                intent.setClass(getApplicationContext(),ShareActivity.class);
                break;
            case R.id.external_btn:
                intent.setClass(getApplicationContext(),ExternalActivity.class);
                break;
            case R.id.internal_btn:
                intent.setClass(getApplicationContext(),InternalActivity.class);
                break;
        }
        startActivity(intent);
    }
}

package com.baidu.tool;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.didichuxing.tool.R;

public class ToolsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tool);
        Toast.makeText(this, getString(R.string.tool), Toast.LENGTH_SHORT).show();
    }
}
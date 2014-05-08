package com.example.DrawFractal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button buttonStart;
    EditText editText;
    LinearLayout container;
    int parameters[];
    InputMethodManager inputManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialization();
    }

    private void initialization() {
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        container = (LinearLayout) findViewById(R.id.container);
        parameters = new int[3];
        getParameters();
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void start() {
        String getFromEditText = editText.getText().toString();
        if (getFromEditText.length() == 1 && Integer.parseInt(getFromEditText) != 0) {
            if (container.getRootView() != null) {
                container.removeAllViewsInLayout();
            }
            parameters[2] = Integer.parseInt(getFromEditText);
            container.addView(new DrawView(getApplicationContext(), parameters));
            editText.setText("");
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            editText.setText("");
            Toast.makeText(getApplicationContext(), "from 1 to 9 only", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void getParameters() {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < 13) {
            Display display = getWindowManager().getDefaultDisplay();
            parameters[0] = display.getWidth();
            parameters[1] = display.getHeight();
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            parameters[0] = size.x;
            parameters[1] = size.y;
        }
    }

}
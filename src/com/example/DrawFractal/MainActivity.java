package com.example.DrawFractal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button buttonStart;
    EditText editText;
    int number;
    LinearLayout container;
    int parameters[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        container = (LinearLayout) findViewById(R.id.container);
        parameters= new int[3];
    }

    private void start() {
        if (editText.getText().toString().length() > 0 && TextUtils.isDigitsOnly(editText.getText().toString().trim())) {
            number = Integer.parseInt(editText.getText().toString().trim());
            if (number > 0 && number < 10) {
                if (container.getRootView() != null) {
                    container.removeAllViewsInLayout();
                }
                getParameters();
                container.addView(new DrawView(getApplicationContext(), parameters));
                editText.setText("");
            } else {
                editText.setText("");
                Toast.makeText(getApplicationContext(), "from 1 to 9 only", Toast.LENGTH_SHORT).show();
            }
        } else {
            editText.setText("");
            Toast.makeText(getApplicationContext(), "only integers", Toast.LENGTH_SHORT).show();
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
        parameters[2]= number;
    }

}
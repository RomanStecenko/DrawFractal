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
import android.widget.*;

public class MainActivity extends Activity {
    LinearLayout container;
    int parameters[];
    InputMethodManager inputManager;
    SeekBar seekbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialization();
    }

    private void initialization() {
        container = (LinearLayout) findViewById(R.id.container);
        parameters = new int[3];
        getParameters();
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(8);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                clear();
                if (seekBar.getProgress() >=1){
                    parameters[2] = seekBar.getProgress();
                    container.addView(new DrawView(getApplicationContext(), parameters));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void clear(){
        if (container.getRootView() != null) {
            container.removeAllViewsInLayout();
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
package com.mixstack.shellvideo.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.mixstack.shellvideo.HomeActivity;
import com.mixstack.shellvideo.R;

public class SplashActivity extends AppCompatActivity {

    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            this.finish();
            return;
        }
        setContentView(R.layout.activity_splash);

        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.setStatusBarColor(Color.TRANSPARENT);

        timer = new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) { }

            @Override
            public void onFinish() {
                startAty();
            }
        };
    }

    public void startAty() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.start();
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        timer = null;
        super.onDestroy();
    }
}

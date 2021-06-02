package com.mixstack.shellvideo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.mixstack.shellvideo.R;
import com.mixstack.shellvideo.ui.fragment.HomeFragment;
import com.mixstack.shellvideo.ui.fragment.LiveFragment;
import com.mixstack.shellvideo.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout flContainer;
    private BottomBarLayout bblNavigation;
    private BottomBarItem bbiHome;
    private BottomBarItem bbiLive;
    private BottomBarItem bbiUser;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        flContainer = findViewById(R.id.fl_container);
        bblNavigation = findViewById(R.id.bbl_navigation);
        bbiHome = findViewById(R.id.bbi_home);
        bbiLive = findViewById(R.id.bbi_live);
        bbiUser = findViewById(R.id.bbi_user);
    }

    private void initData() {
        Fragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        Fragment liveFragment = new LiveFragment();
        fragmentList.add(liveFragment);
        Fragment userFragment = new UserFragment();
        fragmentList.add(userFragment);
        changeFragment(0);
    }

    private void initListener() {
        bblNavigation.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
                changeFragment(currentPosition);
            }
        });
    }

    private void changeFragment(int currentPosition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, fragmentList.get(currentPosition));
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

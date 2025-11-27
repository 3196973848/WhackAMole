package com.linbochuan.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到布局中的按钮
        Button playButton = findViewById(R.id.btn_play);
        Button leaderboardButton = findViewById(R.id.btn_leaderboard);

        // 设置按钮点击监听器
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPlay();
            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLeaderboard();
            }
        });
    }

    // 处理开始游戏按钮点击
    public void onClickPlay() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    // 处理排行榜按钮点击
    public void onClickLeaderboard() {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}
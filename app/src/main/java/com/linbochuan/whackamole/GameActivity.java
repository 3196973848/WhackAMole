package com.linbochuan.whackamole;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private ArrayList<ImageView> moleHoles;
    private TextView tvTimeText;
    private TextView tvScoreText;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // 初始化视图
        initializeViews();

        // 初始化地鼠洞
        initializeMoleHoles();
    }

    private void initializeViews() {
        // 找到时间和分数文本框
        tvTimeText = findViewById(R.id.tv_time_text);
        tvScoreText = findViewById(R.id.tv_score_text);

        // 初始化分数显示
        updateScoreDisplay();
    }

    private void initializeMoleHoles() {
        moleHoles = new ArrayList<>();

        // 添加所有地鼠洞到列表
        moleHoles.add(findViewById(R.id.iv_mole_01));
        moleHoles.add(findViewById(R.id.iv_mole_02));
        moleHoles.add(findViewById(R.id.iv_mole_03));
        moleHoles.add(findViewById(R.id.iv_mole_04));
        moleHoles.add(findViewById(R.id.iv_mole_05));
        moleHoles.add(findViewById(R.id.iv_mole_06));
        moleHoles.add(findViewById(R.id.iv_mole_07));
        moleHoles.add(findViewById(R.id.iv_mole_08));
        moleHoles.add(findViewById(R.id.iv_mole_09));

        // 设置点击监听器
        for (int i = 0; i < moleHoles.size(); i++) {
            final int holeIndex = i;
            moleHoles.get(i).setOnClickListener(v -> {
                // 处理点击地鼠的逻辑
                onMoleClicked(holeIndex);
            });
        }
    }

    private void onMoleClicked(int holeIndex) {
        // 临时测试：点击地鼠洞增加分数
        score++;
        updateScoreDisplay();

        // 这里稍后会添加真正的游戏逻辑
        System.out.println("Mole hole " + (holeIndex + 1) + " clicked! Score: " + score);
    }

    private void updateScoreDisplay() {
        tvScoreText.setText("Score: " + score);
    }
}
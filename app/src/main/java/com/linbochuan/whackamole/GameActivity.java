package com.linbochuan.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ArrayList<mole> moles;
    private TextView tvTimeText;
    private TextView tvScoreText;
    private int score = 0;
    private boolean isGameRunning = false;

    // 游戏参数
    private static final long GAME_DURATION = 30000; // 30秒
    private static final long MOLE_DISPLAY_TIME = 1000; // 地鼠显示1秒
    private static final long MOLE_SPAWN_INTERVAL = 800; // 地鼠生成间隔0.8秒

    private Handler moleHandler;
    private Runnable moleRunnable;
    private CountDownTimer gameTimer;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // 初始化组件
        initializeViews();
        initializeMoles();
        startGame();
    }

    private void initializeViews() {
        tvTimeText = findViewById(R.id.tv_time_text);
        tvScoreText = findViewById(R.id.tv_score_text);
        updateScoreDisplay();
    }

    private void initializeMoles() {
        moles = new ArrayList<>();
        random = new Random();

        // 初始化所有地鼠
        int[] moleIds = {
                R.id.iv_mole_01, R.id.iv_mole_02, R.id.iv_mole_03,
                R.id.iv_mole_04, R.id.iv_mole_05, R.id.iv_mole_06,
                R.id.iv_mole_07, R.id.iv_mole_08, R.id.iv_mole_09
        };

        for (int i = 0; i < moleIds.length; i++) {
            ImageView moleView = findViewById(moleIds[i]);
            mole mole = new mole(i, moleView);
            moles.add(mole);

            // 设置点击监听器
            final int moleIndex = i;
            moleView.setOnClickListener(v -> {
                onMoleClicked(moleIndex);
            });
        }
    }

    private void startGame() {
        isGameRunning = true;
        score = 0;
        updateScoreDisplay();

        // 启动游戏计时器
        startGameTimer();

        // 启动地鼠生成循环
        startMoleSpawning();
    }

    private void startGameTimer() {
        gameTimer = new CountDownTimer(GAME_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 更新剩余时间显示
                int secondsLeft = (int) (millisUntilFinished / 1000);
                tvTimeText.setText("Time: " + secondsLeft);
            }

            @Override
            public void onFinish() {
                // 游戏结束
                endGame();
            }
        };
        gameTimer.start();
    }

    private void startMoleSpawning() {
        moleHandler = new Handler();
        moleRunnable = new Runnable() {
            @Override
            public void run() {
                if (isGameRunning) {
                    spawnRandomMole();
                    moleHandler.postDelayed(this, MOLE_SPAWN_INTERVAL);
                }
            }
        };
        moleHandler.post(moleRunnable);
    }

    private void spawnRandomMole() {
        // 隐藏所有当前可见的地鼠
        hideAllMoles();

        // 随机选择一个地鼠显示
        int randomIndex = random.nextInt(moles.size());
        mole mole = moles.get(randomIndex);
        mole.setVisible(true);

        // 设置定时隐藏这个地鼠
        moleHandler.postDelayed(() -> {
            if (mole.isVisible()) {
                mole.setVisible(false);
            }
        }, MOLE_DISPLAY_TIME);
    }

    private void hideAllMoles() {
        for (mole mole : moles) {
            if (mole.isVisible()) {
                mole.setVisible(false);
            }
        }
    }

    private void onMoleClicked(int moleIndex) {
        if (!isGameRunning) return;

        mole clickedMole = moles.get(moleIndex);
        if (clickedMole.isVisible()) {
            // 成功击中地鼠
            score++;
            updateScoreDisplay();
            clickedMole.setVisible(false); // 立即隐藏被击中的地鼠
        }
    }

    private void updateScoreDisplay() {
        tvScoreText.setText("Score: " + score);
    }

    private void endGame() {
        isGameRunning = false;

        // 停止地鼠生成
        if (moleHandler != null && moleRunnable != null) {
            moleHandler.removeCallbacks(moleRunnable);
        }

        // 停止游戏计时器
        if (gameTimer != null) {
            gameTimer.cancel();
        }

        // 隐藏所有地鼠
        hideAllMoles();

        // 跳转到PlayerActivity并传递分数
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("FINAL_SCORE", score);
        startActivity(intent);
        finish(); // 结束当前Activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 清理资源
        if (moleHandler != null && moleRunnable != null) {
            moleHandler.removeCallbacks(moleRunnable);
        }
        if (gameTimer != null) {
            gameTimer.cancel();
        }
    }
}
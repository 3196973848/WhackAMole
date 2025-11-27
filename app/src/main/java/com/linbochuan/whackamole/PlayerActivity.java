package com.linbochuan.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {

    private TextView tvPlayerScore;
    private EditText etPlayerName;
    private RadioGroup rgAvatar;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // 初始化视图
        initializeViews();

        // 获取从GameActivity传递过来的分数（暂时用模拟数据）
        getScoreFromIntent();
    }

    private void initializeViews() {
        // 找到所有视图组件
        tvPlayerScore = findViewById(R.id.tv_playerscore);
        etPlayerName = findViewById(R.id.et_playername);
        rgAvatar = findViewById(R.id.rg_avatar);

        // 设置提交按钮点击监听器
        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClicked();
            }
        });

        // 默认选择第一个单选按钮
        rgAvatar.check(R.id.rb_grey);
    }

    private void getScoreFromIntent() {
        // 从GameActivity获取真实分数
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("FINAL_SCORE")) {
            finalScore = intent.getIntExtra("FINAL_SCORE", 0);
        } else {
            finalScore = 0; // 默认值
        }
        tvPlayerScore.setText("Score: " + finalScore);
    }

    public void onSubmitClicked() {
        // 获取玩家姓名
        String playerName = etPlayerName.getText().toString().trim();

        // 检查姓名是否为空
        if (playerName.isEmpty()) {
            etPlayerName.setError("Please enter your name");
            return;
        }

        // 获取选择的头像颜色
        int selectedAvatarId = rgAvatar.getCheckedRadioButtonId();
        String avatarColor = getAvatarColor(selectedAvatarId);

        // 创建玩家对象并保存到排行榜
        Player newPlayer = new Player(playerName, avatarColor, finalScore);
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.updateLeaderboard(newPlayer);

        // 打印信息
        System.out.println("Player saved: " + playerName +
                ", Score: " + finalScore +
                ", Avatar: " + avatarColor);

        // 跳转回MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // 结束当前Activity
    }

    private String getAvatarColor(int radioButtonId) {
        if (radioButtonId == R.id.rb_grey) {
            return "Grey";
        } else if (radioButtonId == R.id.rb_blue) {
            return "Blue";
        } else if (radioButtonId == R.id.rb_orange) {
            return "Orange";
        } else if (radioButtonId == R.id.rb_green) {
            return "Green";
        } else if (radioButtonId == R.id.rb_purple) {
            return "Purple";
        } else if (radioButtonId == R.id.rb_pink) {
            return "Pink";
        } else {
            return "Grey";
        }
    }
        }


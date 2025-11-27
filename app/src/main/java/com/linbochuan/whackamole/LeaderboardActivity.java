package com.linbochuan.whackamole;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    private LinearLayout leaderboardContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // 初始化视图
        initializeViews();

        // 加载排行榜数据
        loadLeaderboardData();
    }

    private void initializeViews() {
        // 找到排行榜内容区域
        leaderboardContent = findViewById(R.id.ll_leaderboard_content);

        // 设置返回按钮
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回MainActivity
            }
        });
    }

    private void loadLeaderboardData() {
        // 清空现有内容
        leaderboardContent.removeAllViews();

        // 获取排行榜数据
        Leaderboard leaderboard = Leaderboard.getInstance();
        ArrayList<Player> players = leaderboard.getLeaderboard();

        // 显示排行榜数据
        displayLeaderboard(players);
    }

    private void displayLeaderboard(ArrayList<Player> players) {
        if (players.isEmpty()) {
            // 如果没有数据，显示提示信息
            TextView emptyText = new TextView(this);
            emptyText.setText("No scores yet! Play the game to get on the leaderboard.");
            emptyText.setGravity(View.TEXT_ALIGNMENT_CENTER);
            emptyText.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            leaderboardContent.addView(emptyText);
            return;
        }

        // 显示每个玩家的分数
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            addPlayerToLeaderboard(i + 1, player);
        }
    }

    private void addPlayerToLeaderboard(int rank, Player player) {
        // 创建每个玩家条目的布局
        LinearLayout playerLayout = new LinearLayout(this);
        playerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        playerLayout.setOrientation(LinearLayout.HORIZONTAL);
        playerLayout.setPadding(0, 20, 0, 20);

        // 排名文本
        TextView rankText = new TextView(this);
        rankText.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));
        rankText.setText(rank + ".");
        rankText.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        rankText.setPadding(10, 0, 10, 0);

        // 头像颜色指示器
        View avatarView = new View(this);
        LinearLayout.LayoutParams avatarParams = new LinearLayout.LayoutParams(
                50,  // 宽度
                50   // 高度
        );
        avatarParams.setMargins(10, 0, 10, 0);
        avatarView.setLayoutParams(avatarParams);
        avatarView.setBackgroundColor(getAvatarColor(player.getPlayerAvatarColor()));

        // 玩家姓名
        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2
        ));
        nameText.setText(player.getPlayerName());
        nameText.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        nameText.setPadding(10, 0, 10, 0);

        // 玩家分数
        TextView scoreText = new TextView(this);
        scoreText.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));
        scoreText.setText("Score: " + player.getPlayerScore());
        scoreText.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        scoreText.setPadding(10, 0, 10, 0);
        scoreText.setGravity(View.TEXT_ALIGNMENT_TEXT_END);

        // 添加所有视图到玩家布局
        playerLayout.addView(rankText);
        playerLayout.addView(avatarView);
        playerLayout.addView(nameText);
        playerLayout.addView(scoreText);

        // 添加玩家布局到排行榜内容区域
        leaderboardContent.addView(playerLayout);

        // 添加分隔线（除了最后一个）
        if (rank < Leaderboard.getInstance().getLeaderboard().size()) {
            View divider = new View(this);
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2  // 高度
            );
            dividerParams.setMargins(0, 10, 0, 10);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(Color.LTGRAY);
            leaderboardContent.addView(divider);
        }
    }

    private int getAvatarColor(String colorName) {
        switch (colorName.toLowerCase()) {
            case "grey": return Color.GRAY;
            case "blue": return Color.BLUE;
            case "orange": return Color.parseColor("#FFA500"); // 橙色
            case "green": return Color.GREEN;
            case "purple": return Color.parseColor("#6750A4"); // 紫色
            case "pink": return Color.parseColor("#FFC0CB"); // 粉色
            default: return Color.GRAY;
        }
    }
}
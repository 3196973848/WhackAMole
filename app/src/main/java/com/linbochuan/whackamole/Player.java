package com.linbochuan.whackamole;

public class Player {
    private String playerName;
    private String playerAvatarColor;
    private int playerScore;

    public Player(String name, String avatarColor, int score) {
        this.playerName = name;
        this.playerAvatarColor = avatarColor;
        this.playerScore = score;
    }

    // 获取玩家姓名
    public String getPlayerName() {
        return playerName;
    }

    // 获取玩家头像颜色
    public String getPlayerAvatarColor() {
        return playerAvatarColor;
    }

    // 获取玩家分数
    public int getPlayerScore() {
        return playerScore;
    }
}
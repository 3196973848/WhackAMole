package com.linbochuan.whackamole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {
    private static Leaderboard leaderboardInstance;
    private ArrayList<Player> leaderboard;
    private final int MAX_LEADERBOARD_SIZE = 5;

    // 私有构造函数（单例模式）
    private Leaderboard() {
        leaderboard = new ArrayList<>();
        // 添加测试数据
        leaderboard.add(new Player("Judy", "Blue", 16));
        leaderboard.add(new Player("Judy", "Pink", 7));
        leaderboard.add(new Player("Mahroor", "Green", 5));
        leaderboard.add(new Player("Mahroor", "Orange", 3));
        leaderboard.add(new Player("Mahroor", "Grey", 1));
    }

    // 获取单例实例
    public static Leaderboard getInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }

    // 更新排行榜
    public void updateLeaderboard(Player newPlayer) {
        leaderboard.add(newPlayer);

        // 简单的冒泡排序（从高到低）
        for (int i = 0; i < leaderboard.size() - 1; i++) {
            for (int j = 0; j < leaderboard.size() - i - 1; j++) {
                Player p1 = leaderboard.get(j);
                Player p2 = leaderboard.get(j + 1);

                if (p1.getPlayerScore() < p2.getPlayerScore()) {
                    // 交换位置
                    leaderboard.set(j, p2);
                    leaderboard.set(j + 1, p1);
                }
            }
        }

        // 如果超过最大大小，移除最低分
        if (leaderboard.size() > MAX_LEADERBOARD_SIZE) {
            ArrayList<Player> newList = new ArrayList<>();
            for (int i = 0; i < MAX_LEADERBOARD_SIZE; i++) {
                newList.add(leaderboard.get(i));
            }
            leaderboard = newList;
        }
    }

    // 获取排行榜
    public ArrayList<Player> getLeaderboard() {
        return new ArrayList<>(leaderboard); // 返回副本
    }
}
package com.linbochuan.whackamole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {
    private static Leaderboard leaderboardInstance;
    private ArrayList<Player> leaderboard;
    private final int MAX_LEADERBOARD_SIZE = 5;

    private Leaderboard() {
        leaderboard = new ArrayList<>();
        // 添加测试数据
        leaderboard.add(new Player("Judy", "Blue", 16));
        leaderboard.add(new Player("Judy", "Pink", 7));
        leaderboard.add(new Player("Mahroor", "Green", 5));
        leaderboard.add(new Player("Mahroor", "Orange", 3));
        leaderboard.add(new Player("Mahroor", "Grey", 1));
    }

    public static Leaderboard getInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }

    public void updateLeaderboard(Player newPlayer) {
        leaderboard.add(newPlayer);

        // 使用简单的排序方法
        Collections.sort(leaderboard, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                // 按分数从高到低排序
                if (p1.getPlayerScore() > p2.getPlayerScore()) {
                    return -1;
                } else if (p1.getPlayerScore() < p2.getPlayerScore()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // 如果超过最大大小，移除最低分
        if (leaderboard.size() > MAX_LEADERBOARD_SIZE) {
            ArrayList<Player> newList = new ArrayList<>();
            for (int i = 0; i < MAX_LEADERBOARD_SIZE; i++) {
                newList.add(leaderboard.get(i));
            }
            leaderboard = newList;
        }
    }

    public ArrayList<Player> getLeaderboard() {
        return new ArrayList<>(leaderboard);
    }
}
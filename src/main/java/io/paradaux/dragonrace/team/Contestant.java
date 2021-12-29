package io.paradaux.dragonrace.team;

import org.bukkit.entity.Player;

public class Contestant {

    private final Player player;
    private int score;

    public Contestant(Player player) {
        this.player = player;
        this.score = 0;
    }

    public void incrementScore() {
        this.score++;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void resetScore() {
        this.score--;
    }

    public int getScore() {
        return this.score;
    }

    public Player getPlayer() {
        return player;
    }

}

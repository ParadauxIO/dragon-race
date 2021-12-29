package io.paradaux.dragonrace.team;

import io.paradaux.dragonrace.adventure.AdventureImpl;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ScoreboardManager {

    private static ScoreboardManager instance;
    private final org.bukkit.scoreboard.ScoreboardManager manager;
    private final Scoreboard teamsBoard;
    private final Scoreboard displayBoard;
    private final Objective display;

    public static ScoreboardManager getInstance() { return instance; }

    public ScoreboardManager() {
        this.manager = Bukkit.getScoreboardManager();
        this.teamsBoard = this.manager.getNewScoreboard();
        this.displayBoard = this.manager.getNewScoreboard();

        Component name = AdventureImpl.getInstance().getMiniMessage().parse("<gradient:#5e4fa2:#f79459:red>Kill The Enderdragon!</gradient>");
        this.display = this.displayBoard.registerNewObjective("display", "dummy", name);

        instance = this;
    }

    public Team addTeam(String name) {
        return teamsBoard.registerNewTeam(name);
    }

    public void createScoreboard() {

    }


    public void build(Player player) {
        Collection<ETeam> teams = TeamManager.getTeams();

        Contestant c = TeamManager.findContestant(player);

        // player-specific
        Score playerDeaths = display.getScore("» Deaths");
        playerDeaths.setScore(100);

        Team playerDeathsValue = displayBoard.registerNewTeam("playerDeathsValue");
        playerDeathsValue.addEntry(randomColor() + "" + randomColor());

        Score playerTeam = display.getScore("» Your Team");
        playerTeam.setScore(98);

        Team playerTeamValue = displayBoard.registerNewTeam("playerTeamValue");
        playerTeamValue.addEntry(randomColor() + "" + randomColor());

        Score teamsHeader = display.getScore("» Teams");
        teamsHeader.setScore(98);

        // list teams
        for (ETeam t : teams) {
            Team t2 = displayBoard.registerNewTeam(t.getName());

        }

        // list all players
//        for (ETeam t : teams) {
//            for (Contestant c : t.getContestants()) {
//
//            }
//        }

    }


    private ChatColor randomColor() {
        Random r = new Random();
        ChatColor[] colors = ChatColor.values();
        return ChatColor.values()[r.nextInt(ChatColor.values().length)-1];
    }
}

package io.paradaux.dragonrace.command;

import io.paradaux.dragonrace.team.Contestant;
import io.paradaux.dragonrace.team.ETeam;
import io.paradaux.dragonrace.team.ScoreboardManager;
import io.paradaux.dragonrace.team.TeamManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.inventivetalent.glow.GlowAPI;
import org.jetbrains.annotations.NotNull;

public class EventCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("<team/player/score>");
        }

        switch (args[0]) {
            case "team" -> {
                if (args[1].equalsIgnoreCase("add")) {
                    TeamManager.addTeam(args[2], Integer.parseInt(args[3], 16), args[4]);
                } else if (args[1].equalsIgnoreCase("remove")) {
                    TeamManager.removeTeam(args[2]);
                } else {
                    sender.sendMessage("<remove/add> <team name> <hex color> <glowing color>");
                }
            } case "player" -> {
                if (args[1].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayer(args[3]);
                    TeamManager.registerPlayer(args[2], target);
                    sender.sendMessage(Component.text("Added " + args[3] + " to team: " + args[2]).color(TextColor.color(0xB33D39)));
                } else if (args[1].equalsIgnoreCase("remove")) {
                    sender.sendMessage(Component.text("Removed " + args[3] + " from team: " + args[2]).color(TextColor.color(0xB33D39)));
                    TeamManager.deregisterPlayer(args[2], Bukkit.getPlayer(args[3]));
                } else {
                    sender.sendMessage("<remove/add> <team> <player>");
                }
            } case "scores" -> {
                if (args[1].equalsIgnoreCase("resetall")) {
                    for (ETeam t : TeamManager.getTeams()) {
                        for (Contestant c : t.getContestants()) {
                            c.resetScore();
                        }
                    }
                } else if (args[1].equalsIgnoreCase("reset")) {
                    TeamManager.findContestant(Bukkit.getPlayer(args[2])).resetScore();
                } else if (args[1].equalsIgnoreCase("add")) {
                    Contestant c = TeamManager.findContestant(Bukkit.getPlayer(args[2]));
                    c.setScore(c.getScore() + Integer.parseInt(args[3]));
                } else if (args[1].equalsIgnoreCase("remove")) {
                    Contestant c = TeamManager.findContestant(Bukkit.getPlayer(args[2]));
                    c.setScore(c.getScore() - Integer.parseInt(args[3]));
                } else if (args[1].equalsIgnoreCase("set")) {
                    Contestant c = TeamManager.findContestant(Bukkit.getPlayer(args[2]));
                    c.setScore(Integer.parseInt(args[3]));
                }
            } case "debug" -> {
                for (ETeam t : TeamManager.getTeams()) {
                    StringBuilder contestants = new StringBuilder();

                    for (int i = 0; i < t.getContestants().size()-1; i++) {
                        contestants.append(t.getContestants().get(i).getPlayer().getName())
                                .append(":")
                                .append(t.getContestants().get(i).getScore())
                                .append(", ");
                    }

                    contestants.append(t.getContestants().get(t.getContestants().size()-1).getPlayer().getName())
                            .append(":")
                            .append(t.getContestants().get(t.getContestants().size()-1).getScore());

                    Component response = Component.text("Team: ")
                            .color(NamedTextColor.RED)
                            .append(Component.text(t.getName()).color(t.getColor()))
                            .append(Component.text("[ " + contestants+ " ]"));

                    sender.sendMessage(response);
                }


            }
        }

        return true;
    }
}

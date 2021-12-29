package io.paradaux.dragonrace.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.inventivetalent.glow.GlowAPI;

import java.util.ArrayList;
import java.util.List;

public class ETeam {

    private final TextComponent nameComponent;
    private final TextColor color;

    private final String name;
    private final GlowAPI.Color glowingColor;
    private final Team team;
    private final ArrayList<Contestant> players;
    private boolean hasWon;

    public ETeam(String name, int color, String glowingColor) {
        this.name = name;
        this.players = new ArrayList<>();

        this.team = ScoreboardManager.getInstance().addTeam(name);
        this.color = TextColor.color(color);
        this.nameComponent = Component.text(name)
                .color(this.color);

        this.glowingColor = GlowAPI.Color.valueOf(glowingColor);

        this.team.prefix(nameComponent);
        this.team.displayName(nameComponent);

        this.team.setCanSeeFriendlyInvisibles(true);
        this.team.setAllowFriendlyFire(false);
    }

    public List<Contestant> getContestants() {
        return players;
    }

    public void addContestant(Player player) {
        this.team.addPlayer(player);
        players.add(new Contestant(player));
    }

    public void removeContestant(Player player) {
        this.team.removePlayer(player);
        players.add(new Contestant(player));
    }

    public void destroy() {
        this.team.unregister();
    }

    public GlowAPI.Color getGlowingColor() {
        return glowingColor;
    }

    public String getName() {
        return name;
    }
}

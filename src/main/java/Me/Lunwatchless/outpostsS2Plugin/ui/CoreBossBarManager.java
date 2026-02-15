package Me.Lunwatchless.outpostsS2Plugin.ui;

import Me.Lunwatchless.outpostsS2Plugin.core.OutpostCore;
import org.bukkit.Bukkit;
import org.bukkit.boss.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CoreBossBarManager {

    private final Map<Player, BossBar> bars = new HashMap<>();

    public void show(Player player, OutpostCore core) {
        BossBar bar = bars.computeIfAbsent(player, p ->
                Bukkit.createBossBar(
                        "Outpost Core",
                        BarColor.YELLOW,
                        BarStyle.SOLID
                )
        );

        bar.setProgress(core.getProgress() / 100.0);
        bar.addPlayer(player);
    }

    public void hide(Player player) {
        BossBar bar = bars.remove(player);
        if (bar != null) {
            bar.removeAll();
        }
    }
}

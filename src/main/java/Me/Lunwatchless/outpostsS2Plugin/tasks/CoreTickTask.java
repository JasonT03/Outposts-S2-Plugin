package Me.Lunwatchless.outpostsS2Plugin.tasks;

import Me.Lunwatchless.outpostsS2Plugin.core.OutpostCore;
import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import Me.Lunwatchless.outpostsS2Plugin.manager.FactionManager;
import Me.Lunwatchless.outpostsS2Plugin.ui.CoreBossBarManager;
import Me.Lunwatchless.outpostsS2Plugin.util.CoreAppearance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoreTickTask implements Runnable {

    private final CoreManager coreManager;
    private final FactionManager factionManager;
    private final CoreBossBarManager barManager;

    private static final double RANGE = 5.0;
    private static final double PROGRESS_SPEED = 5.0; // % per second

    public CoreTickTask(CoreManager coreManager, FactionManager factionManager, CoreBossBarManager barManager) {
        this.coreManager = coreManager;
        this.factionManager = factionManager;
        this.barManager = barManager;
    }

    @Override
    public void run() {

        for (OutpostCore core : coreManager.getAllCores()) {

            boolean playerInRange = false;

            for (Player player : Bukkit.getOnlinePlayers()) {

                if (!player.getWorld().equals(core.getLocation().getWorld())) continue;

                if (player.getLocation().distance(core.getLocation()) <= RANGE) {

                    playerInRange = true;

                    core.setProgress(core.getProgress() + PROGRESS_SPEED);
                    barManager.show(player, core);

                } else {
                    barManager.hide(player);
                }
            }

            if (!playerInRange) {
                core.setProgress(core.getProgress() - PROGRESS_SPEED);
            }

            // Claim check
            if (core.getProgress() >= 100) {

                Player claimer = Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.getWorld().equals(core.getLocation().getWorld()))
                        .filter(p -> p.getLocation().distance(core.getLocation()) <= RANGE)
                        .findFirst()
                        .orElse(null);

                if (claimer == null) return;

                if (!core.isClaimed()) {
                    core.claim(
                            claimer.getUniqueId(),
                            factionManager.getFaction(claimer)
                    );

                    CoreAppearance.applyAppearance(
                            core.getLocation().getBlock(),
                            core.getFaction()
                    );

                    claimer.sendMessage("§aJe hebt een Outpost Core geclaimd!");
                }
            }

        }
    }
}

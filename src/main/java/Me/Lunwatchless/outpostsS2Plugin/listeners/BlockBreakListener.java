package Me.Lunwatchless.outpostsS2Plugin.listeners;

import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final CoreManager coreManager;

    public BlockBreakListener(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (coreManager.isCore(e.getBlock())) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                e.setCancelled(true);
            } else {
                coreManager.removeCore(e.getBlock());
            }
        }
    }
}


package Me.Lunwatchless.outpostsS2Plugin.listeners;

import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    private final CoreManager coreManager;

    public InteractListener(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        if (coreManager.isCore(e.getClickedBlock())) {
            e.setCancelled(true);
        }
    }
}

package Me.Lunwatchless.outpostsS2Plugin.listeners;

import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final CoreManager coreManager;

    public BlockPlaceListener(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().getType() == Material.NETHER_STAR) {
            coreManager.registerCore(e.getBlockPlaced());
        }
    }
}

package Me.Lunwatchless.outpostsS2Plugin.manager;

import Me.Lunwatchless.outpostsS2Plugin.OutpostsS2Plugin;
import Me.Lunwatchless.outpostsS2Plugin.core.CoreType;
import Me.Lunwatchless.outpostsS2Plugin.core.Faction;
import Me.Lunwatchless.outpostsS2Plugin.core.OutpostCore;
import Me.Lunwatchless.outpostsS2Plugin.util.CoreAppearance;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CoreManager {

    private final NamespacedKey coreKey;
    private final Map<Location, OutpostCore> cores = new HashMap<>();

    public CoreManager(OutpostsS2Plugin plugin) {
        coreKey = new NamespacedKey(plugin, "outpost_core");
    }

    public void registerCore(Block block) {

        block.setType(org.bukkit.Material.NOTE_BLOCK);

        if (!(block.getState() instanceof TileState state)) return;
        state.getPersistentDataContainer().set(
                coreKey,
                PersistentDataType.BYTE,
                (byte) 1
        );
        state.update();

        OutpostCore core = new OutpostCore(
                block.getLocation(),
                CoreType.NORMAL
        );

        cores.put(block.getLocation(), core);

        CoreAppearance.applyAppearance(block, core.getFaction());
    }

    public void registerLoadedCore(OutpostCore core) {

        cores.put(core.getLocation(), core);

        Block block = core.getLocation().getBlock();
        block.setType(org.bukkit.Material.NOTE_BLOCK);

        if (!(block.getState() instanceof TileState state)) return;

        state.getPersistentDataContainer().set(
                coreKey,
                PersistentDataType.BYTE,
                (byte) 1
        );
        state.update();

        CoreAppearance.applyAppearance(block, core.getFaction());
    }

    public boolean isCore(Block block) {
        if (!(block.getState() instanceof TileState state)) return false;

        return state.getPersistentDataContainer().has(
                coreKey,
                PersistentDataType.BYTE
        );
    }

    public void removeCore(Block block) {
        cores.remove(block.getLocation());
    }

    public OutpostCore getCore(Block block) {
        return cores.get(block.getLocation());
    }

    public Collection<OutpostCore> getAllCores() {
        return cores.values();
    }
}

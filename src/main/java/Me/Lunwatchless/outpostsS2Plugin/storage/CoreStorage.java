package Me.Lunwatchless.outpostsS2Plugin.storage;

import Me.Lunwatchless.outpostsS2Plugin.OutpostsS2Plugin;
import Me.Lunwatchless.outpostsS2Plugin.core.*;
import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import Me.Lunwatchless.outpostsS2Plugin.util.CoreAppearance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CoreStorage {

    private final File file;
    private final YamlConfiguration config;
    private final CoreManager coreManager;

    public CoreStorage(OutpostsS2Plugin plugin, CoreManager coreManager) {
        this.coreManager = coreManager;

        file = new File(plugin.getDataFolder(), "cores.yml");
        if (!file.exists()) {
            plugin.saveResource("cores.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        config.set("cores", null);

        for (OutpostCore core : coreManager.getAllCores()) {
            Location l = core.getLocation();
            String key = l.getWorld().getName() + ";" +
                    l.getBlockX() + ";" +
                    l.getBlockY() + ";" +
                    l.getBlockZ();

            config.set("cores." + key + ".faction", core.getFaction().name());
            config.set("cores." + key + ".type", core.getType().name());

            if (core.getOwner() != null) {
                config.set("cores." + key + ".owner", core.getOwner().toString());
            } else {
                config.set("cores." + key + ".owner", null);
            }
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!config.contains("cores")) return;

        for (String key : config.getConfigurationSection("cores").getKeys(false)) {

            String[] parts = key.split(";");
            World world = Bukkit.getWorld(parts[0]);
            if (world == null) continue;

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int z = Integer.parseInt(parts[3]);

            Location loc = new Location(world, x, y, z);

            if (loc.getBlock().getType().isAir()) continue;

            Faction faction = Faction.valueOf(
                    config.getString("cores." + key + ".faction")
            );

            CoreType type = CoreType.valueOf(
                    config.getString("cores." + key + ".type")
            );

            OutpostCore core = new OutpostCore(loc, type);

            String ownerStr = config.getString("cores." + key + ".owner");
            if (ownerStr != null) {
                core.setOwner(UUID.fromString(ownerStr));
            }

            core.setFaction(faction);

            coreManager.registerLoadedCore(core);
            CoreAppearance.applyAppearance(loc.getBlock(), faction);
        }
    }
}

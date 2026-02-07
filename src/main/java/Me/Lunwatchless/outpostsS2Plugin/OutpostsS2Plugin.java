package Me.Lunwatchless.outpostsS2Plugin;

import Me.Lunwatchless.outpostsS2Plugin.listeners.*;
import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OutpostsS2Plugin extends JavaPlugin {

    private static OutpostsS2Plugin instance;
    private CoreManager coreManager;

    @Override
    public void onEnable() {
        instance = this;
        coreManager = new CoreManager(this);

        getServer().getPluginManager().registerEvents(
                new BlockPlaceListener(coreManager), this);
        getServer().getPluginManager().registerEvents(
                new BlockBreakListener(coreManager), this);
        getServer().getPluginManager().registerEvents(
                new InteractListener(coreManager), this);
    }

    @Override
    public void onDisable() {
        // later: cores opslaan
    }

    public static OutpostsS2Plugin getInstance() {
        return instance;
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }
}

package Me.Lunwatchless.outpostsS2Plugin;

import Me.Lunwatchless.outpostsS2Plugin.commands.FactionCommand;
import Me.Lunwatchless.outpostsS2Plugin.listeners.*;
import Me.Lunwatchless.outpostsS2Plugin.manager.CoreManager;
import Me.Lunwatchless.outpostsS2Plugin.manager.FactionManager;
import Me.Lunwatchless.outpostsS2Plugin.storage.CoreStorage;
import Me.Lunwatchless.outpostsS2Plugin.tasks.CoreTickTask;
import Me.Lunwatchless.outpostsS2Plugin.ui.CoreBossBarManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OutpostsS2Plugin extends JavaPlugin {

    private static OutpostsS2Plugin instance;
    private CoreManager coreManager;
    private FactionManager factionManager;
    private CoreStorage coreStorage;
    private CoreBossBarManager bossBarManager;

    @Override
    public void onEnable() {
        instance = this;
        coreManager = new CoreManager(this);
        coreStorage = new CoreStorage(this, coreManager);
        factionManager = new FactionManager();
        bossBarManager = new CoreBossBarManager();

        coreStorage.load();

        getServer().getPluginManager().registerEvents(
                new BlockPlaceListener(coreManager), this);
        getServer().getPluginManager().registerEvents(
                new BlockBreakListener(coreManager), this);
        getServer().getPluginManager().registerEvents(
                new InteractListener(coreManager), this);

        getServer().getScheduler().runTaskTimer(
                this,
                new CoreTickTask(coreManager, factionManager, bossBarManager),
                20L,
                20L
        );

        getCommand("faction").setExecutor(
                new FactionCommand(factionManager)
        );
    }

    @Override
    public void onDisable() {
        coreStorage.save();
    }

    public static OutpostsS2Plugin getInstance() {
        return instance;
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public FactionManager getFactionManager() {
        return factionManager;
    }
}

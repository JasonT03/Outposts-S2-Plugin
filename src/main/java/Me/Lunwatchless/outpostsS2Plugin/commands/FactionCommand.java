package Me.Lunwatchless.outpostsS2Plugin.commands;

import Me.Lunwatchless.outpostsS2Plugin.core.Faction;
import Me.Lunwatchless.outpostsS2Plugin.manager.FactionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FactionCommand implements CommandExecutor {

    private final FactionManager factionManager;

    public FactionCommand(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!sender.isOp()) {
            sender.sendMessage("§cGeen permissie.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cGebruik: /faction <set|get> <player> [faction]");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage("§cSpeler niet online.");
            return true;
        }

        if (args[0].equalsIgnoreCase("get")) {
            sender.sendMessage("§eFaction van " + target.getName() +
                    ": §a" + factionManager.getFaction(target));
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 3) {
                sender.sendMessage("§cGeef een faction op.");
                return true;
            }

            try {
                Faction faction = Faction.valueOf(args[2].toUpperCase());
                factionManager.setFaction(target, faction);
                sender.sendMessage("§aFaction aangepast.");
                target.sendMessage("§eJe faction is nu: §a" + faction);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("§cOngeldige faction.");
            }
            return true;
        }

        return true;
    }
}

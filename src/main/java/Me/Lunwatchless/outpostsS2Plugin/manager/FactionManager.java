package Me.Lunwatchless.outpostsS2Plugin.manager;

import Me.Lunwatchless.outpostsS2Plugin.core.Faction;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionManager {

    private final Map<UUID, Faction> playerFactions = new HashMap<>();

    public void setFaction(Player player, Faction faction) {
        playerFactions.put(player.getUniqueId(), faction);
    }

    public Faction getFaction(Player player) {
        return playerFactions.getOrDefault(player.getUniqueId(), Faction.NEUTRAL);
    }

    public void removePlayer(Player player) {
        playerFactions.remove(player.getUniqueId());
    }
}

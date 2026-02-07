package Me.Lunwatchless.outpostsS2Plugin.core;

import org.bukkit.Location;

import java.util.UUID;

public class OutpostCore {

    private final Location location;
    private UUID owner;
    private Faction faction;

    public OutpostCore(Location location) {
        this.location = location;
        this.faction = Faction.NEUTRAL;
        this.owner = null;
    }

    public Location getLocation() {
        return location;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}

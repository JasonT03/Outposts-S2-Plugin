package Me.Lunwatchless.outpostsS2Plugin.core;

import org.bukkit.Location;

import java.util.UUID;

public class OutpostCore {

    private final Location location;
    private UUID owner;
    private Faction faction;
    private CoreType type;
    private double progress = 0.0;

    public OutpostCore(Location location, CoreType type) {
        this.location = location;
        this.type = type;
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

    public CoreType getType() {
        return type;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = Math.max(0, Math.min(110, progress));
    }

    public boolean isClaimed() {
        return owner != null;
    }

    public void claim(UUID owner, Faction faction) {
        this.owner = owner;
        this.faction = faction;
        this.progress = 100.0;
    }

    public void resetToNeutral() {
        this.owner = null;
        this.faction = Faction.NEUTRAL;
        this.progress = 0.0;
    }
}

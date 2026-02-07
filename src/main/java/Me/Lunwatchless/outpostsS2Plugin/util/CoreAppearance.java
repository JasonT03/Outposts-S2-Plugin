package Me.Lunwatchless.outpostsS2Plugin.util;

import Me.Lunwatchless.outpostsS2Plugin.core.Faction;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;

public class CoreAppearance {

    public static void applyAppearance(Block block, Faction faction) {

        block.setType(org.bukkit.Material.NOTE_BLOCK);

        NoteBlock data = (NoteBlock) block.getBlockData();
        data.setInstrument(Instrument.BANJO);

        int note;

        switch (faction) {
            case VILLAGERS -> note = 2;
            case UNDEAD -> note = 3;
            case PILLAGERS -> note = 4;
            case NETHER -> note = 5;
            case END -> note = 6;
            default -> note = 1; // NEUTRAL
        }

        data.setNote(new Note(note));
        block.setBlockData(data);
    }
}


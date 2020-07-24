package xyz.fourthirdskiwidrive.dungeonshud;

import net.minecraft.block.BlockState;

public class ChunkSlice {
    public BlockState[][] blocks = new BlockState[16][16];

    public Boolean isEmpty() {
        for(BlockState[] i : blocks) {
            for(BlockState j : i) {
                if (j == null) continue;
                if (!(j.isAir())) return false;
            }
        }
        return true;
    }
}

package xyz.fourthirdskiwidrive.dungeonshud.map;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSlice;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;

public class DimensionData {
    private static DimensionData instance;
    public static DimensionData getInstance() {
        if (instance == null) {
            instance = new DimensionData();
        }

        return instance;
    }

    public ChunkSlice getChunkSlice(int x, int z, MinecraftClient client) {
        World wc = client.world;
        Chunk chunkZZ = wc.getChunk(x,z, ChunkStatus.FULL, false);
        if(chunkZZ == null) {
            ChunkSliceManager csm = ChunkSliceManager.getInstance();
            try {
                return csm.slices.get(x).get(z);
            } catch (IndexOutOfBoundsException e) {
                ChunkSlice emptyChunkSlice = new ChunkSlice();
                emptyChunkSlice.blocks = new BlockState[16][16];
                return emptyChunkSlice;
            }

        }
        BlockState[][] BlockSlice = new BlockState[16][16];

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                BlockPos blockPos = new BlockPos(i, 70, j);
                BlockState b = chunkZZ.getBlockState(blockPos);
                BlockSlice[i][j] = b;
            }
        }

        ChunkSlice cs = new ChunkSlice();
        cs.blocks = BlockSlice;
        return cs;
    }
}

package xyz.fourthirdskiwidrive.dungeonshud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.world.chunk.Chunk;
import xyz.fourthirdskiwidrive.dungeonshud.map.DimensionData;

import java.util.ArrayList;

public class ChunkSliceManager {
    public ArrayList<ArrayList<ChunkSlice>> slices = new ArrayList<ArrayList<ChunkSlice>>();
    private static ChunkSliceManager instance = null;
    private Boolean hasDoneThisAtLeastOnce = false;

    public int xNeeded = 16;
    public int yNeeded = 16;

    public static ChunkSliceManager getInstance() {
        if(instance == null) instance = new ChunkSliceManager();
        return instance;
    }

    public void update() {
        ArrayList<ArrayList<ChunkSlice>> chunkSlices = new ArrayList<ArrayList<ChunkSlice>>();
        DimensionData dimensionData = DimensionData.getInstance();
        MinecraftClient c = MinecraftClient.getInstance();
        for(int i = 0; i < 16; i++) {
            ArrayList<ChunkSlice> currentChunkRow = new ArrayList<ChunkSlice>();
            for (int j = 0; j < 16; ++j) {
                ChunkSlice cs = dimensionData.getChunkSlice(i, j, c);
                currentChunkRow.add(cs);
            }
            chunkSlices.add(currentChunkRow);
        }

        slices = chunkSlices;
    }

    public void updateSizes() {
        for(int i = this.slices.size() - 1; i >= 0; --i) {
            if (this.slices.get(i).get(0).isEmpty())
                xNeeded = i;
            if (this.slices.get(0).get(i).isEmpty())
                yNeeded = i;
        }
    }
}

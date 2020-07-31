package xyz.fourthirdskiwidrive.dungeonshud.map;

import net.minecraft.data.client.model.Texture;
import net.minecraft.world.chunk.Chunk;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;



public class MapTexturer {
    Texture texture;
    ChunkSliceManager csm = ChunkSliceManager.getInstance();
    private static MapTexturer instance;
    private MapTexturer() {}
    public static MapTexturer getInstance() {
        if(instance == null)
            instance = new MapTexturer();
        return instance;
    }
    void updateTexture() {

    }
}

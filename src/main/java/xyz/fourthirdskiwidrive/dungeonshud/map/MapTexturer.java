package xyz.fourthirdskiwidrive.dungeonshud.map;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.data.client.model.Texture;
import net.minecraft.world.chunk.Chunk;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;



public class MapTexturer {
    public MapTexture texture;
    private static MapTexturer instance;
    private MapTexturer() {

    }
    public static MapTexturer getInstance() {
        if(instance == null)
            instance = new MapTexturer();
        return instance;
    }
    public void updateTexture(ChunkSliceManager csm) {
        if(texture == null)
            texture = new MapTexture(200, 200);
        texture.updateTextureFromCSM(csm);
    }
}

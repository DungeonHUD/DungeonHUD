package xyz.fourthirdskiwidrive.dungeonshud.map;

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

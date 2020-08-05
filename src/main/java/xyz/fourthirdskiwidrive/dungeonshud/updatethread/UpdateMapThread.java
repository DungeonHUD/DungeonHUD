package xyz.fourthirdskiwidrive.dungeonshud.updatethread;

import net.minecraft.client.MinecraftClient;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;
import xyz.fourthirdskiwidrive.dungeonshud.map.MapTexturer;

public class UpdateMapThread {
    private ChunkSliceManager csm;
    public static int UPDATE_TIME = 500;

    public UpdateMapThread() {
        csm = ChunkSliceManager.getInstance();
    }

    public void update() {
        if(MinecraftClient.getInstance().world != null) {
            csm.update();
            csm.updateSizes();
            MapTexturer.getInstance().updateTexture(csm);
        } else {
            System.out.println("Map update failed: Not in world");
        }
    }


}

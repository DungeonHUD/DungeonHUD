package xyz.fourthirdskiwidrive.dungeonshud.updatethread;

import net.minecraft.client.MinecraftClient;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;
import xyz.fourthirdskiwidrive.dungeonshud.map.MapTexturer;

public class UpdateMapThread {
    ChunkSliceManager csm;
    public static int UPDATE_TIME;

    public UpdateMapThread() {
        csm = ChunkSliceManager.getInstance();
        UPDATE_TIME = 500;
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

package xyz.fourthirdskiwidrive.dungeonshud.updatethread;

import net.minecraft.client.MinecraftClient;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;

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
        } else {
            System.out.println("Map update failed: Not in world");
        }
    }


}

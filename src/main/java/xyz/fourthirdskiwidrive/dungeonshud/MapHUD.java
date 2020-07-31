package xyz.fourthirdskiwidrive.dungeonshud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.Chunk;
import xyz.fourthirdskiwidrive.dungeonshud.map.DimensionData;
import net.minecraft.block.AirBlock;

import java.awt.*;
import java.util.ArrayList;

public class MapHUD extends HUDComponent {
    public static final float BOX_SIZE = 200;
    public MapHUD () {

    }

    //@Deprecated
    public void renderDetailedMap(MatrixStack m, float partial, MinecraftClient client) {

        //TODO: This method is extremely slow. I need to find a new way to render things. I've already moved getting the
        //TODO: blocks from the world to another thread. Perhaps I can create a texture elsewhere and render it like that?
        ArrayList<ArrayList<ChunkSlice>> chunkSlices;

        ChunkSliceManager csm = ChunkSliceManager.getInstance();

        chunkSlices = csm.slices;
        for (int i = 0; i < csm.xNeeded; ++i) {
            for (int j = 0; j < csm.yNeeded; ++j) {
                BlockState[][] blocks = chunkSlices.get(i).get(j).blocks;

                //if(chunkSlices.get(i).get(j).isEmpty()) continue;

                //Draw the map
                for (int k = 0; k < blocks.length; ++k) {
                    for (int l = 0; l < blocks[k].length; ++l) {
                        boolean isAir             = blocks[k][l].isAir();
                        boolean isWitherDoorBlock = blocks[k][l].isOf(Blocks.COAL_BLOCK);
                        boolean isBloodDoorBlock  = blocks[k][l].isOf(Blocks.RED_TERRACOTTA);

                        if(isAir) {
                            fill(m, 20.0F + (20.0F * i) + (1.25F * k), 20.0F + (20.0F * j) + (1.25F * l), 21.25F + (20.0F * i) + (1.25F * k), 21.25F + (20.0F * j) + (1.25F * l), Color.WHITE.getRGB());
                        } else if (isWitherDoorBlock) {
                            fill(m, 20.0F + (20.0F * i) + (1.25F * k), 20.0F + (20.0F * j) + (1.25F * l), 21.25F + (20.0F * i) + (1.25F * k), 21.25F + (20.0F * j) + (1.25F * l), Color.BLACK.getRGB());
                        } else if (isBloodDoorBlock) {
                            fill(m, 20.0F + (20.0F * i) + (1.25F * k), 20.0F + (20.0F * j) + (1.25F * l), 21.25F + (20.0F * i) + (1.25F * k), 21.25F + (20.0F * j) + (1.25F * l), Color.RED.getRGB());
                        } else {
                            fill(m, 20.0F + (20.0F * i) + (1.25F * k), 20.0F + (20.0F * j) + (1.25F * l), 21.25F + (20.0F * i) + (1.25F * k), 21.25F + (20.0F * j) + (1.25F * l), Color.GRAY.getRGB());
                        }
                    }
                }
            }
        }
    }

    public void renderPlayerLocation(MatrixStack m, float partial, MinecraftClient client) {

    }

    @Override
    public void render(MatrixStack m, float partial, MinecraftClient client) {
        MinecraftClient client2 = MinecraftClient.getInstance();

        renderDetailedMap(m, partial, client2);
        if(client.player != null) {
            Vec3d pos = client.player.getPos();
            drawSprite(pos.x * 1.25 + 20, pos.z * 1.25 + 20, 5, 5, new Identifier("minecraft:textures/item/redstone.png"), client);
        }
    }
}

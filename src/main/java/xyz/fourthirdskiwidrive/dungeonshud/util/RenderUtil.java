package xyz.fourthirdskiwidrive.dungeonshud.util;

import net.minecraft.client.render.BufferBuilder;

public class RenderUtil {
    //public static BufferBuilder buff = Tessellator.getInstance().getBuffer();
    public static void addQuad(BufferBuilder buff, double x, double y, double w, double h, float minU, float minV, float maxU, float maxV) {
        vertex(buff, x + 0, y + 0, 0.0, minU, minV);
        vertex(buff, x + 0, y + h, 0.0, minU, maxV);
        vertex(buff, x + w, y + h, 0.0, maxU, maxV);
        vertex(buff, x + w, y + 0, 0.0, maxU, minV);
    }

    private static void vertex(BufferBuilder buff, double x, double y, double z, float u, float v) {
        buff.vertex(x, y, z).texture(u, v).next();
    }
}

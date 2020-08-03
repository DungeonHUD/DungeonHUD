package xyz.fourthirdskiwidrive.dungeonshud.util;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;

import java.nio.Buffer;

public class RenderUtil {
    //public static BufferBuilder buff = Tessellator.getInstance().getBuffer();
    public static void addQuad(BufferBuilder buff, double x, double y, double w, double h, float minU, float minV, float maxU, float maxV) {
        vertex(buff, x + 0, y + 0, 0.0, minU, minV);
        vertex(buff, x + 0, y + h, 0.0, minU, maxV);
        vertex(buff, x + w, y + h, 0.0, maxU, maxV);
        vertex(buff, x + w, y + 0, 0.0, maxU, minV);
    }

    private static void vertex(BufferBuilder buff, Matrix4f m4f, Matrix3f m3f, VertexConsumer vertexConsumer, float x, float y, float z, float u, float v) {
        vertexConsumer.vertex(m4f, x, y, z).texture(u, v).normal(m3f, 0.0F, 1.0F, 0.0F).next();
    }

    private static void vertex(BufferBuilder buff, double x, double y, double z, float u, float v) {
        buff.vertex(x, y, z).texture(u, v).next();
    }
}

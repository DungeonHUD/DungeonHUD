package xyz.fourthirdskiwidrive.dungeonshud;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;

public abstract class HUDComponent extends DrawableHelper {

    public abstract void render(MatrixStack m, float partial, MinecraftClient client);

    public static void drawBox(MatrixStack matrices, float x1, float y1, float x2, float y2, float border_width) {
        drawVerticalLine(matrices, x1, y1, y2, border_width);
        drawVerticalLine(matrices, x2, y1, y2, border_width);

        drawHorizontalLine(matrices, x1, x2, y1, border_width);
        drawHorizontalLine(matrices, x1, x2, y2, border_width);
    }

    public static void drawFont(MinecraftClient mc, MatrixStack m, String s, float x, float y) {
        mc.textRenderer.draw(m, s, x, y, Color.WHITE.getRGB());
    }

    public static void drawVerticalLine(MatrixStack matrices, float x, float y1, float y2, float thickness) {
        drawVerticalLine(matrices, x, y1, y2, thickness, Color.GRAY);
    }

    public static void drawVerticalLine(MatrixStack matrices, float x, float y1, float y2, float thickness, Color c) {
        if (y2 < y1) {
            float i = y1;
            y1 = y2;
            y2 = i;
        }

        fill(matrices, (float)(x - .5 * thickness), (float)(y1 + .5 * thickness), (float)(x + .5 * thickness), (float)(y2 - .5 * thickness), c.getRGB());
    }

    public static void drawHorizontalLine(MatrixStack matrices, float x1, float x2, float y, float thickness) {
        drawHorizontalLine(matrices, x1, x2, y, thickness, Color.GRAY);
    }
    public static void drawHorizontalLine(MatrixStack matrices, float x1, float x2, float y, float thickness, Color c) {
        if (x2 < x1) {
            float i = x1;
            x1 = x2;
            x2 = i;
        }

        fill(matrices, (float)(x1 - .5 * thickness), (float)(y + .5 * thickness), (float)(x2 + .5 * thickness), (float)(y - .5 * thickness), c.getRGB());
    }

    public static void fill(MatrixStack matrices, float x1, float y1, float x2, float y2) {
        fill(
                matrices.peek().getModel(),
                x1, y1, x2, y2, Color.GRAY.getRGB()
        );
    }

    public static void fill(MatrixStack matrices, float x1, float y1, float x2, float y2, int color) {
        fill(
                matrices.peek().getModel(),
                x1, y1, x2, y2, color
        );
    }

    private static void fill(Matrix4f matrix, float x1, float y1, float x2, float y2, int color) {
        float j;

        if (x1 < x2) {
            j = x1;
            x1 = x2;
            x2 = j;
        }

        if (y1 < y2) {
            j = y1;
            y1 = y2;
            y2 = j;
        }
        float alpha = .5F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, x1, y2, 0.0F).color(r, g, b, alpha).next();
        bufferBuilder.vertex(matrix, x2, y2, 0.0F).color(r, g, b, alpha).next();
        bufferBuilder.vertex(matrix, x2, y1, 0.0F).color(r, g, b, alpha).next();
        bufferBuilder.vertex(matrix, x1, y1, 0.0F).color(r, g, b, alpha).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void drawSprite(double x, double y, int width, int height, Identifier id, MinecraftClient client) {
        ScreenDrawing.texturedRect((int)x, (int)y, width, height, id, 0F, 0F, 1F, 1F, 0xFFFFFFFF);
    }

    // draw rectangle with texture stretched to fill the shape
    public static void drawTexturedRect(MatrixStack m, double x, double y, double w, double h)
    {
        drawTexturedRect(m.peek().getModel(), x, y, w, h, 0.0D, 0.0D, 1.0D, 1.0D);
    }

    // draw rectangle with texture UV coordinates specified (so only part of the
    // texture fills the rectangle).
    public static void drawTexturedRect(Matrix4f matrix, double x, double y, double w, double h, double u1, double v1, double u2, double v2)
    {
        try
        {
            BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
            RenderSystem.enableBlend();
            RenderSystem.enableTexture();
            RenderSystem.defaultBlendFunc();
            bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(matrix, (float)(x    ), (float)(y + h), 0.0F).texture((float)u1, (float)v2).next();
            bufferBuilder.vertex(matrix, (float)(x + w), (float)(y + h), 0.0F).texture((float)u2, (float)v2).next();
            bufferBuilder.vertex(matrix, (float)(x + w), (float)(y    ), 0.0F).texture((float)u2, (float)v1).next();
            bufferBuilder.vertex(matrix, (float)(x    ), (float)(y    ), 0.0F).texture((float)u1, (float)v1).next();
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
        }
        catch (NullPointerException e)
        {
            System.err.println("HUDComponent.drawTexturedRect: null pointer exception");
        }
    }
}

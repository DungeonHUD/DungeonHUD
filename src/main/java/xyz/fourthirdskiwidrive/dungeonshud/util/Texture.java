package xyz.fourthirdskiwidrive.dungeonshud.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.texture.TextureUtil;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class Texture {
    private int id;
    protected final int w;
    protected final int h;
    private final ByteBuffer pixelBuf;
    private byte[] bytes;

    public Texture(int w, int h, int fillColor, int minFilter, int maxFilter, int textureWrap) {
        this.id = -1;
        this.w = w;
        this.h = h;
        bytes = new byte[4 * w * h];
        this.pixelBuf = ByteBuffer.allocateDirect(w * h * 4).order(ByteOrder.nativeOrder());
        this.fillRect(0, 0, w, h, fillColor);
        this.pixelBuf.position(0);
    }

    public void fillRect(int x, int y, int w, int h, int color) {
        for(int j = 0; j < h; ++j) {
            for (int i = 0; i < w; ++i) {
                    setColor(j + x, i + y, color);
            }
        }
    }

    public void setColor(int x, int y, int color) {
        if(x < 0 || x > this.w) return;
        if(y < 0 || y > this.h) return;

        byte a = (byte) (color >> 24);
        byte b = (byte) (color >> 16);
        byte g = (byte) (color >> 8);
        byte r = (byte) (color);

        int index = (x + y * this.w) * 4;

        this.bytes[index] = a;
        this.bytes[index + 1] = b;
        this.bytes[index + 2] = g;
        this.bytes[index + 3] = r;
    }

    public void bind() {

        RenderSystem.bindTexture(this.id);
    }

    public void setTexParameters(int minFilter, int maxFilter, int textureWrap) {
        this.bind();
        RenderSystem.texParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, textureWrap);
        RenderSystem.texParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, textureWrap);
        RenderSystem.texParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
        RenderSystem.texParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, maxFilter);
    }

    public void upload() {
        if(this.id == -1)
            this.id = TextureUtil.generateId();

        this.pixelBuf.clear();
        this.pixelBuf.put(bytes);
        this.pixelBuf.position(0).limit(bytes.length);

        this.bind();
        this.setTexParameters(GL11.GL_NEAREST, GL11.GL_NEAREST, GL12.GL_CLAMP_TO_EDGE);
        RenderSystem.pixelStore(GL11.GL_UNPACK_ROW_LENGTH, 0);
        RenderSystem.pixelStore(GL11.GL_UNPACK_SKIP_PIXELS, 0);
        RenderSystem.pixelStore(GL11.GL_UNPACK_SKIP_ROWS, 0);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.w, this.h, 0, GL11.GL_RGBA, GL12.GL_UNSIGNED_INT_8_8_8_8, this.pixelBuf);
    }
}

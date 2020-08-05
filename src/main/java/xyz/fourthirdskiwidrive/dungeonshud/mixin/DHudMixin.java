package xyz.fourthirdskiwidrive.dungeonshud.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.fourthirdskiwidrive.dungeonshud.HUDRenderer;

@Mixin(InGameHud.class)
public class DHudMixin {
	private final HUDRenderer hud = new HUDRenderer();

	@Shadow
	private MinecraftClient client;

	@Inject(method = "render", at = @At("RETURN"))
	private void render(MatrixStack ms, float partial, CallbackInfo info) {
		hud.render(ms, partial, client);
	}
}

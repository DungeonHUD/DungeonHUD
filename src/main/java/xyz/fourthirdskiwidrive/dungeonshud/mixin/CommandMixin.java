package xyz.fourthirdskiwidrive.dungeonshud.mixin;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.CommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.text.Text;
import net.minecraft.client.network.ClientCommandSource;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.fourthirdskiwidrive.dungeonshud.gui.ConfigurationGui;

@Mixin(ClientPlayerEntity.class)
public abstract class CommandMixin implements CommandSource {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onChatMessage(String msg, CallbackInfo info) {
        client = MinecraftClient.getInstance();
        boolean cancel = false;
        if(!(msg.startsWith("?"))) return;


        info.cancel();
        System.out.printf("Intercepted ? command message again: %s", msg);
        if(msg.startsWith("?cfg")) {
            System.out.println("Executing ?cfg command");
            cancel = true;

        }

        if(cancel) info.cancel();
    }
}

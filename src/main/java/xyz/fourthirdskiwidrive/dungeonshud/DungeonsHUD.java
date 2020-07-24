package xyz.fourthirdskiwidrive.dungeonshud;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import xyz.fourthirdskiwidrive.dungeonshud.gui.ConfigurationGui;
import xyz.fourthirdskiwidrive.dungeonshud.gui.ConfigurationScreen;

public class DungeonsHUD implements ModInitializer {
	public static KeyBinding menuKeyBinding;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		menuKeyBinding = new KeyBinding(
				"key.examplemod.spook", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_PERIOD, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		);

		KeyBindingHelper.registerKeyBinding(menuKeyBinding);

		ClientTickCallback.EVENT.register(client -> {
			while (menuKeyBinding.wasPressed())
				MinecraftClient.getInstance().openScreen(new ConfigurationScreen(new ConfigurationGui()));
		});

		System.out.println("Hello Fabric world!");
	}
}

package xyz.fourthirdskiwidrive.dungeonshud;

import net.fabricmc.api.ModInitializer;
import xyz.fourthirdskiwidrive.dungeonshud.updatethread.UpdateMapThread;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DungeonsHUD implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		UpdateMapThread mapThread = new UpdateMapThread();
		ActionListener mapThreadUpdateTrigger = e -> mapThread.update();

		Timer timer = new Timer(500, mapThreadUpdateTrigger);
		timer.setRepeats(true);
		timer.start();
	}
}

package xyz.fourthirdskiwidrive.dungeonshud.gui;

import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;

import net.minecraft.util.Identifier;


public class ConfigurationGui extends LightweightGuiDescription {
    public ConfigurationGui() {
        System.out.println("Loading configuration GUI");
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);

        WLabel label = new WLabel("DungeonsHUD Configuration");
        root.add(label, 20, 20);

        root.validate(this);
    }
}
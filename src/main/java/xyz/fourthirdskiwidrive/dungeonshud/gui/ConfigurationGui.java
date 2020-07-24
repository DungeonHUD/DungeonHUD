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

        WSprite icon = new WSprite(new Identifier("minecraft:textures/item/redstone.png"));
        root.add(icon, 0, 2, 1, 1);

        WButton button = new WButton(new TranslatableText("gui.examplemod.examplebutton"));
        root.add(button, 0, 3, 4, 1);

        WLabel label = new WLabel(new LiteralText("Test"), 0xFFFFFF);
        root.add(label, 0, 4, 2, 1);

        root.validate(this);
    }
}
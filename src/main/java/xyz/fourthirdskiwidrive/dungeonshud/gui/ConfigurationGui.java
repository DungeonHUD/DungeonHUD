package xyz.fourthirdskiwidrive.dungeonshud.gui;

import io.github.cottonmc.cotton.gui.widget.data.Axis;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.text.LiteralText;


public class ConfigurationGui extends LightweightGuiDescription {
    public static int opacityValue = 0x77ffffff;
    public ConfigurationGui() {
        System.out.println("Loading configuration GUI");
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(256, 240);

        WLabel label = new WLabel("DungeonsHUD Configuration");
        root.add(label, 20, 20);

        WLabeledSlider opacity = new WLabeledSlider(0, 0xff, Axis.HORIZONTAL) {
            @Override
            public WWidget onMouseUp(int x, int y, int button) {
                opacityValue = (this.value << 24) + 0xffffff;
                return this;
            }
        };
        opacity.setLabel(new LiteralText("Opacity"));
        opacity.setValue(opacityValue >> 24);

        root.add(opacity, 100, 100, 240, 40);

        root.validate(this);
    }
}
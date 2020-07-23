package xyz.fourthirdskiwidrive.dungeonshud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class HUDRenderer extends HUDComponent {
    private final HUDComponent[] components =
            new HUDComponent[] {
                new MapHUD()
            };

    @Override
    public void render(MatrixStack m, float partial, MinecraftClient client) {

        try {
            m.push();
            for(HUDComponent component : components) {
                component.render(m, partial, client);
            }
            m.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

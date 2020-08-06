package xyz.fourthirdskiwidrive.dungeonshud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;

public class HUDRenderer extends HUDComponent {
    private final HUDComponent[] components =
            new HUDComponent[] {
                new MapHUD()
            };

    @Override
    public void render(MatrixStack m, float partial, MinecraftClient client) {
        if(client.player.inventory.getStack(8).getItem() == Items.FILLED_MAP) {
            try {
                m.push();
                for (HUDComponent component : components) {
                    component.render(m, partial, client);
                }
                m.pop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

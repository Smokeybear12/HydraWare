package club.auth.hydraware.gui;

import club.auth.hydraware.HydraWare;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public interface IComponent {

    static void fillRect(Rectangle rect, Color color) {
        Gui.drawRect(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, color.getRGB());
    }

    static void drawString(String text, Point pos, Color color) {
        GlStateManager.enableTexture2D();
        HydraWare.fontManager.drawStringWithShadow(text, pos.x, pos.y, color.getRGB());
        GlStateManager.disableTexture2D();
    }

    void draw();

    void handleButton(int btn);

    void keyTyped(int key, char ch);

    int getAbsoluteHeight();

    void addChild(IComponent component);

    Rectangle getRect();

    void setRect(Rectangle rect);

}

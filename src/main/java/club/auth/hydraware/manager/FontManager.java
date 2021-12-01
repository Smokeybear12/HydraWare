package club.auth.hydraware.manager;

import club.auth.hydraware.util.CFontRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class FontManager {

    private final CFontRenderer cFontRenderer = new CFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);

    private final Minecraft mc = Minecraft.getMinecraft();
    private boolean customFont = true;

    public void setCustomFont(boolean customFont) {
        this.customFont = customFont;
    }

    public void drawStringWithShadow(final String text, final float x, final float y, final int color) {
        if (customFont) {
            cFontRenderer.drawStringWithShadow(text, x, y, color);
            return;
        }
        mc.fontRenderer.drawStringWithShadow(text, x, y, color);
    }

    public int getStringWidth(final String text) {
        if (customFont) {
            return cFontRenderer.getStringWidth(text);
        }
        return mc.fontRenderer.getStringWidth(text);
    }
}

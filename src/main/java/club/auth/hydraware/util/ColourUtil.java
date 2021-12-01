package club.auth.hydraware.util;

import java.awt.*;

public class ColourUtil implements Global {

    public static int shadeColour(int color, int precent) {
        int r = (((color & 0xFF0000) >> 16) * (100 + precent) / 100);
        int g = (((color & 0xFF00) >> 8) * (100 + precent) / 100);
        int b = ((color & 0xFF) * (100 + precent) / 100);
        return new Color(r, g, b).hashCode();
    }

    public static Color getColour() {
        return Colour.fromHSB((System.currentTimeMillis() % (360 * 32)) / (360f * 32), 1, 1);
    }

    public static Color getFurtherColour(int offset) {
        return Colour.fromHSB(((System.currentTimeMillis() + offset) % (360 * 32)) / (360f * 32), 1, 1);
    }
}

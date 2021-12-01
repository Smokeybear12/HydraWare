package club.auth.hydraware.module.modules.main;

import club.auth.hydraware.HydraWare;
import club.auth.hydraware.module.Module;
import org.lwjgl.input.Keyboard;

public class GUI extends Module {

    public GUI() {
        super("GUI", "Displays Gui screen.", Keyboard.KEY_RSHIFT, Category.MAIN);
    }

    public void onEnable() {
        mc.displayGuiScreen(HydraWare.instance.clickGui);
        toggle();
    }
}

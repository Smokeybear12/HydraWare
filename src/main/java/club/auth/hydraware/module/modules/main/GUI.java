package club.auth.hydraware.module.modules.main;

import club.auth.hydraware.HydraWare;
import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class GUI extends Module {

    public GUI() {
        super("GUI","Displays Gui screen.", Keyboard.KEY_RSHIFT, Category.MAIN);
    }

    public void onEnable() {
        mc.displayGuiScreen(HydraWare.instance.clickGui);
        toggle();
    }
}

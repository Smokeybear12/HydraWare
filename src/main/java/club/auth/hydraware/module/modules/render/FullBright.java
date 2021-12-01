package club.auth.hydraware.module.modules.render;

import club.auth.hydraware.module.Module;

public class FullBright extends Module {
    public FullBright() {super("FullBright","Turns up brightness to see in the dark.",0,Category.RENDER);}

    public void update() {
        mc.gameSettings.gammaSetting = 100;
    }

    public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}

package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingInteger;
import club.auth.hydraware.setting.settings.SettingMode;

import java.util.ArrayList;

public class AntiVoid extends Module {
    public AntiVoid() {
        super("AntiVoid","",0,Category.PLAYER);
    }

    @Override
    public void update() {
        if (mc.player.posY <= .5) {
            mc.player.moveVertical = 10;
            mc.player.jump();
        } else {
            mc.player.moveVertical = 0;
        }
    }

    @Override
    public void onDisable() {
        mc.player.moveVertical = 0;
    }
}

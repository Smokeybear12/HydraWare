package club.auth.hydraware.module.modules.movement;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingMode;
import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;

public class Sprint extends Module {

    ArrayList<String> modes = new ArrayList<String>();
    SettingMode mode = this.register("Mode", modes, "Rage");

    public Sprint() {
        super("Sprint", "Automatic sprints.", 0, Category.MOVEMENT);
        modes.add("Legit");
        modes.add("Rage");
    }

    public void onDisable() {
        if (mc.world != null) {
            mc.player.setSprinting(false);
        }
    }

    public void update() {
        if (mode.getValue().equals("Legit")) {
            if ((mc.gameSettings.keyBindForward.isKeyDown()) && !(mc.player.isSneaking()) && !(mc.player.isHandActive()) && !(mc.player.collidedHorizontally) && mc.currentScreen == null && !(mc.player.getFoodStats().getFoodLevel() <= 6f)) {
                mc.player.setSprinting(true);
            }
        }
        if (mode.getValue().equals("Rage")) {
            if ((mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()) && !(mc.player.isSneaking()) && !(mc.player.collidedHorizontally) && !(mc.player.getFoodStats().getFoodLevel() <= 6f)) {
                mc.player.setSprinting(true);
            }
        }
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
    }
}

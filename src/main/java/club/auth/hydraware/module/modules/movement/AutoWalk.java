package club.auth.hydraware.module.modules.movement;

import club.auth.hydraware.module.Module;
import net.minecraft.client.settings.KeyBinding;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk","Presses the move forward key.",0,Category.MOVEMENT);
    }

    @Override
    public void update() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
    }
}

package club.auth.hydraware.module.modules.movement;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingDouble;

public class ReverseStep extends Module {
    public ReverseStep() {
        super("ReverseStep", "",0,Category.MOVEMENT);
    }

    SettingDouble speed = this.register("Speed",5.0F, 0.0F, 20.0F);

    @Override
    public void update() {
        if (mc.player.isInWater() || mc.player.isInLava()) {
            return;
        }
        if (mc.player.onGround) {
            mc.player.motionY -= speed.getValue() / 10;
        }
    }
}

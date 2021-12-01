package club.auth.hydraware.module.modules.movement;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import club.auth.hydraware.setting.settings.SettingDouble;

public class Step extends Module {
    SettingDouble height = this.register("Height", 2.5, 0.5, 2.0);
    SettingBoolean entity = this.register("EntityStep", false);
    public Step() {
        super("Step", "Step up blocks.", 0, Category.MOVEMENT);
    }

    public void update() {
        if (entity.getValue() && mc.player.getRidingEntity() != null) {
            mc.player.getRidingEntity().stepHeight = (float) height.getValue();
        }
        mc.player.stepHeight = (float) height.getValue();
    }

    public void onDisable() {
        mc.player.stepHeight = 0.5f;
    }
}

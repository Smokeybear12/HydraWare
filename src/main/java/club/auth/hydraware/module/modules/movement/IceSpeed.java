package club.auth.hydraware.module.modules.movement;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingDouble;
import net.minecraft.init.Blocks;

public class IceSpeed extends Module {
    public IceSpeed() {
        super("IceSpeed", "", 0,Category.MOVEMENT);
    }

    @Override
    public void update() {
        Blocks.ICE.slipperiness = 0.4F;
        Blocks.PACKED_ICE.slipperiness = 0.4F;
        Blocks.FROSTED_ICE.slipperiness = 0.4F;
    }

    @Override
    public void onDisable() {
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }
}

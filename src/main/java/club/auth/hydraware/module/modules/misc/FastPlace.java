package club.auth.hydraware.module.modules.misc;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;

public class FastPlace extends Module {
    SettingBoolean everything = register("Everything", false);
    SettingBoolean blocks = register("Blocks", false);
    SettingBoolean crystals = register("Crystals", false);
    SettingBoolean exp = register("Exp", false);
    public FastPlace() {
        super("FastPlace", "", 0, Category.MISC);
    }

    @Override
    public void update() {
        if (everything.getValue()) {
            mc.rightClickDelayTimer = 0;
        }
        if (exp.getValue() && mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle || mc.player.getHeldItemOffhand().getItem() instanceof ItemExpBottle) {
            mc.rightClickDelayTimer = 0;
        }
        if (blocks.getValue() && mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock || mc.player.getHeldItemOffhand().getItem() instanceof ItemBlock) {
            mc.rightClickDelayTimer = 0;
        }
        if (crystals.getValue() && mc.player.getHeldItemMainhand().getItem() instanceof ItemEndCrystal || mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal) {
            mc.rightClickDelayTimer = 0;
        }
    }
}

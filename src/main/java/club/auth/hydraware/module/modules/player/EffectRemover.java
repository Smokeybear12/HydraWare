package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import net.minecraft.potion.Potion;

import java.util.Objects;

public class EffectRemover extends Module {
    public EffectRemover() {
        super("EffectRemover","Removes the effects from you.",0,Category.PLAYER);
    }

    SettingBoolean levitation = register("Levitation",true);
    SettingBoolean jumpBoost = register("JumpBoost",true);

    public void update() {
        if (levitation.getValue()) {
            if (mc.player.isPotionActive((Potion) Objects.requireNonNull(Potion.getPotionFromResourceLocation("levitation")))) {
                mc.player.removeActivePotionEffect(Potion.getPotionFromResourceLocation("levitation"));
            }
        }
        if (jumpBoost.getValue()) {
            if (mc.player.isPotionActive((Potion) Objects.requireNonNull(Potion.getPotionFromResourceLocation("jump_boost")))) {
                mc.player.removeActivePotionEffect(Potion.getPotionFromResourceLocation("jump_boost"));
            }
        }
    }
}

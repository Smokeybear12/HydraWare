package club.auth.hydraware.module.modules.combat;

import club.auth.hydraware.module.Module;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;

public class SilentEXP extends Module {
    int prvSlot;

    public SilentEXP() {
        super("SilentEXP", "", 0, Category.COMBAT);
    }

    @Override
    public void update() {
        if (mc.currentScreen == null) {
            prvSlot = mc.player.inventory.currentItem;
            mc.player.connection.sendPacket(new CPacketHeldItemChange(findExpInHotbar()));
            mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            mc.player.inventory.currentItem = prvSlot;
            mc.player.connection.sendPacket(new CPacketHeldItemChange(prvSlot));
        }
    }

    private int findExpInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}

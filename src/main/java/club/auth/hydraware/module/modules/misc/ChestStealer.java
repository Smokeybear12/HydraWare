package club.auth.hydraware.module.modules.misc;

import club.auth.hydraware.module.Module;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;

public class ChestStealer extends Module {
    public ChestStealer() {
        super("ChestStealer", "", 0, Category.MISC);
    }

    @Override
    public void update() {
        if (mc.player.openContainer instanceof ContainerChest) {
            ContainerChest chest = (ContainerChest) mc.player.openContainer;
            for (int items = 0; items < chest.getLowerChestInventory().getSizeInventory(); ++items) {
                ItemStack stack = chest.getLowerChestInventory().getStackInSlot(items);
                mc.playerController.windowClick(chest.windowId, items, 0, ClickType.QUICK_MOVE, ChestStealer.mc.player);
                if (this.isChestEmpty(chest)) {
                    mc.player.closeScreen();
                }
            }
        }
    }

    private boolean isChestEmpty(ContainerChest chest) {
        for (int items = 0; items < chest.getLowerChestInventory().getSizeInventory(); ++items) {
            ItemStack slot = chest.getLowerChestInventory().getStackInSlot(items);
            return false;
        }
        return true;
    }
}

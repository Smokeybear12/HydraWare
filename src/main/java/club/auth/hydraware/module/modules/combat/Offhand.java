package club.auth.hydraware.module.modules.combat;

//fucking remove the comment lmfao just kidding dont take it seriously btw this is Auth and made half of this yesterday, 03-09-2021

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;

public class Offhand extends Module {
    public Offhand() {
        super("OffHand", "", 0, Category.COMBAT);
    }

    SettingBoolean swordGap = register("SwordGap",true);
    SettingBoolean soft = register("Soft", false);

    private boolean dragging = false;
    private int totems = 0;

	// never click more than once per tick if you're making something that uses the inventory
    public void update() {
        if(mc.currentScreen instanceof GuiContainer && !(mc.currentScreen instanceof GuiInventory)) return;
        EntityPlayerSP player = mc.player;
        if(player == null) return;

		// click on an empty slot if we're dragging an item when we're not supposed to
        if(!player.inventory.getItemStack().isEmpty() && !dragging){
            for(int i = 0;i < 45; i++){
                if(player.inventory.getStackInSlot(i).isEmpty() || player.inventory.getStackInSlot(i).getItem() == Items.AIR){
                    int slot = i < 9 ? i + 36 : i;
                    mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, player);
                    return;
                }
            }
        }

		// get amount of totems in inventory
        totems = 0;
        for(ItemStack stack : player.inventory.mainInventory){
            if(stack.getItem() == Items.TOTEM_OF_UNDYING)
                totems += stack.getCount();
        }

        if(player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING){
            totems += player.getHeldItemOffhand().getCount();
            return;
        }

        if(soft.getValue() && !player.getHeldItemOffhand().isEmpty()) return;

		// click on the offhand slot if we're dragging a totem
        if(dragging){
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, player);
            dragging = false;
            return;
        }

		// look for a totem and grab it
        for(int i = 0; i < 45; i++){
            if(player.inventory.getStackInSlot(i).getItem() == Items.TOTEM_OF_UNDYING){
                int slot = i < 9 ? i + 36 : i;
                mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, player);
                dragging = true;
                return;
            }
        }
    }
}

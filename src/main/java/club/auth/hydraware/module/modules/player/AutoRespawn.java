package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.command.Messages;
import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import net.minecraft.client.gui.GuiGameOver;

public class AutoRespawn extends Module {
    SettingBoolean coords = this.register("DeathCoords", true);

    public AutoRespawn() {
        super("AutoRespawn", "Removes the death screen.", 0, Category.PLAYER);
    }

    @Override
    public void update() {
        super.update();
        if (mc.currentScreen instanceof GuiGameOver) {
            mc.player.respawnPlayer();
            mc.displayGuiScreen(null);
        }
        if (coords.getValue() && mc.currentScreen instanceof GuiGameOver) {
            Messages.sendClientMessage("You have died at x" + (int) mc.player.posX + " y" + (int) mc.player.posY + " z" + (int) mc.player.posZ);
        }
    }
}

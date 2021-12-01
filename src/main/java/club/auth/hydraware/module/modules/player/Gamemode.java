package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.module.Module;
import net.minecraft.world.GameType;

public class Gamemode extends Module {
    public Gamemode() {
        super("Gamemode", "Fake gamemode 1.", 0, Category.PLAYER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.playerController.setGameType(GameType.CREATIVE);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.playerController.setGameType(GameType.SURVIVAL);
    }
}

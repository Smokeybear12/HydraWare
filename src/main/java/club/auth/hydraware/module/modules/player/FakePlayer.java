package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.module.Module;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.world.GameType;

import java.util.UUID;

public class FakePlayer extends Module {
    private EntityOtherPlayerMP bot;

    public FakePlayer() {
        super("FakePlayer", "Spawns a bot", 0, Category.PLAYER);
    }

    public void onEnable() {
        if (mc.player.isDead) {
            disable();
            return;
        }
        bot = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("1ccf59f9-5afd-4b8f-9d33-c540c294d096"), "Blaze"));
        bot.copyLocationAndAnglesFrom(mc.player);
        bot.rotationYawHead = mc.player.rotationYawHead;
        bot.rotationYaw = mc.player.rotationYaw;
        bot.rotationPitch = mc.player.rotationPitch;
        bot.setGameType(GameType.SURVIVAL);
        bot.setHealth(20);
        mc.world.addEntityToWorld(-1337, bot);
        bot.onLivingUpdate();
    }

    public void onDisable() {
        if (mc.world != null) {
            mc.world.removeEntityFromWorld(-1337);
        }
    }
}

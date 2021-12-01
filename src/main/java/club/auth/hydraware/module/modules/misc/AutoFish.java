package club.auth.hydraware.module.modules.misc;

import club.auth.hydraware.event.events.PacketEvent;
import club.auth.hydraware.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.EnumHand;

public class AutoFish extends Module {
    public AutoFish() {
        super("AutoFish", "Fishes automatically.", 0, Category.MISC);
    }

    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();
            if (packet.getSound().equals(SoundEvents.ENTITY_BOBBER_SPLASH)) {
                if (mc.player.getHeldItemMainhand().getItem() instanceof ItemFishingRod) {
                    mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                    mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                if (mc.player.getHeldItemOffhand().getItem() instanceof ItemFishingRod) {
                    mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.OFF_HAND));
                    mc.player.swingArm(EnumHand.OFF_HAND);
                    mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.OFF_HAND));
                    mc.player.swingArm(EnumHand.OFF_HAND);
                }
            }
        }
    });
}


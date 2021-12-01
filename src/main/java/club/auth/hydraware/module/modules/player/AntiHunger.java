package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.event.events.PacketEvent;
import club.auth.hydraware.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketPlayer;

public class AntiHunger extends Module {
    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer player = (CPacketPlayer) event.getPacket();
            double differenceY = mc.player.posY - mc.player.lastTickPosY;
            boolean groundCheck = differenceY == 0D;
            if ((groundCheck) && (!mc.playerController.isHittingBlock)) {
                mc.player.onGround = true;
            }
        }
    });

    public AntiHunger() {
        super("AntiHunger", "Causes you to not lose hunger even while jumping.", 0, Category.PLAYER);
    }
}

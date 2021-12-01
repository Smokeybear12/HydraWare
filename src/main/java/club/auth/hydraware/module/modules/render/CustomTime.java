package club.auth.hydraware.module.modules.render;

import club.auth.hydraware.event.events.PacketEvent;
import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingDouble;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketTimeUpdate;

public class CustomTime extends Module {
    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            event.cancel();
        }
    });
    long time = 0;

    SettingDouble clientTime = this.register("Time", 18000L, 0L, 23992L);

    public CustomTime() {
        super("CustomTime", "Allows you to change game time.", 0, Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.time = mc.world.getWorldTime();
    }

    @Override
    public void update() {
        mc.world.setWorldTime((long) clientTime.getValue());
    }

    @Override
    public void onDisable() {
        mc.world.setWorldTime(this.time);
    }
}

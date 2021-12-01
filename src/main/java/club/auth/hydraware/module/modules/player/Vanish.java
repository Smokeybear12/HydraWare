package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketVehicleMove;

import java.util.Objects;

public class Vanish extends Module {
    public Vanish() {
        super("Vanish","",0,Category.PLAYER);
    }

    Entity entity;

    @Override
    public void onEnable() {
        if (mc.player.getRidingEntity() != null) {
            entity = mc.player.getRidingEntity();
            mc.player.dismountRidingEntity();
            mc.world.removeEntity(entity);
        }
    }

    @Override
    public void update() {
        if (nullCheck()) {
            disable();
            return;
        }
        if (entity != null) {
            try {
                entity.posX = mc.player.posX;
                entity.posY = mc.player.posY;
                entity.posZ = mc.player.posZ;
                Objects.requireNonNull(mc.getConnection()).sendPacket(new CPacketVehicleMove(entity));
            } catch (Exception ignored) {}
        }
    }
}

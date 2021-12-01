package club.auth.hydraware.manager;

import club.auth.hydraware.util.Global;
import net.minecraft.network.play.client.CPacketPlayer;

public class PositionManager implements Global {

    public void updatePosition() {
        double x = PositionManager.mc.player.posX;
        double y = PositionManager.mc.player.posY;
        double z = PositionManager.mc.player.posZ;
        boolean onground = PositionManager.mc.player.onGround;
    }

    public void setPositionPacket(double x, double y, double z, boolean onGround, boolean setPos, boolean noLagBack) {
        PositionManager.mc.player.connection.sendPacket(new CPacketPlayer.Position(x, y, z, onGround));
        if (setPos) {
            PositionManager.mc.player.setPosition(x, y, z);
            if (noLagBack) {
                this.updatePosition();
            }
        }
    }
}

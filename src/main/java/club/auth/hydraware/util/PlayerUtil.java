package club.auth.hydraware.util;

import net.minecraft.util.math.BlockPos;

public class PlayerUtil implements Global{

    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }

    public static BlockPos getPlayerPos(double pY) {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY + pY), Math.floor(mc.player.posZ));
    }
}

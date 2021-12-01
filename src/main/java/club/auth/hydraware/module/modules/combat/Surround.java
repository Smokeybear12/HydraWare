package club.auth.hydraware.module.modules.combat;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import club.auth.hydraware.util.BlockUtil;
import club.auth.hydraware.util.InventoryUtil;
import club.auth.hydraware.util.PlayerUtil;
import net.minecraft.block.BlockObsidian;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class Surround extends Module {
    private static final BlockPos[] surroundPos = new BlockPos[]{
            new BlockPos(0, -1, 0),
            new BlockPos(1, -1, 0),
            new BlockPos(-1, -1, 0),
            new BlockPos(0, -1, 1),
            new BlockPos(0, -1, -1),

            new BlockPos(1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0, 0, 1),
            new BlockPos(0, 0, -1),
    };
    SettingBoolean smart = register("Smart", false);
    SettingBoolean center = register("Center", true);
    SettingBoolean rotate = register("Rotate", false);
    double posY;
    private BlockPos startPos;
    private ArrayList<BlockPos> retryPos;

    public Surround() {
        super("Surround", "", 0, Category.COMBAT);
    }

    @Override
    public void onEnable() {
        if (nullCheck()) return;
        startPos = PlayerUtil.getPlayerPos();
        retryPos = new ArrayList<>();
        if (center.getValue()) {
            double y = mc.player.getPosition().getY();
            double x = mc.player.getPosition().getX();
            double z = mc.player.getPosition().getZ();

            Vec3d plusPlus = new Vec3d(x + 0.5, y, z + 0.5);
            Vec3d plusMinus = new Vec3d(x + 0.5, y, z - 0.5);
            Vec3d minusMinus = new Vec3d(x - 0.5, y, z - 0.5);
            Vec3d minusPlus = new Vec3d(x - 0.5, y, z + 0.5);
            if (getDst(plusPlus) < getDst(plusMinus) && getDst(plusPlus) < getDst(minusMinus) && getDst(plusPlus) < getDst(minusPlus)) {
                x = mc.player.getPosition().getX() + 0.5;
                z = mc.player.getPosition().getZ() + 0.5;
                centerPlayer(x, y, z);
            }
            if (getDst(plusMinus) < getDst(plusPlus) && getDst(plusMinus) < getDst(minusMinus) && getDst(plusMinus) < getDst(minusPlus)) {
                x = mc.player.getPosition().getX() + 0.5;
                z = mc.player.getPosition().getZ() - 0.5;
                centerPlayer(x, y, z);
            }
            if (getDst(minusMinus) < getDst(plusPlus) && getDst(minusMinus) < getDst(plusMinus) && getDst(minusMinus) < getDst(minusPlus)) {
                x = mc.player.getPosition().getX() - 0.5;
                z = mc.player.getPosition().getZ() - 0.5;
                centerPlayer(x, y, z);
            }
            if (getDst(minusPlus) < getDst(plusPlus) && getDst(minusPlus) < getDst(plusMinus) && getDst(minusPlus) < getDst(minusMinus)) {
                x = mc.player.getPosition().getX() - 0.5;
                z = mc.player.getPosition().getZ() + 0.5;
                centerPlayer(x, y, z);
            }
        }
        posY = mc.player.posY;
    }

    @Override
    public void update() {
        if (nullCheck()) {
            this.toggle();
            return;
        }
        if (posY < mc.player.posY) {
            this.toggle();
            return;
        }
        if (startPos != null) {
            if (!startPos.equals(PlayerUtil.getPlayerPos())) {
                this.toggle();
                return;
            }
        }
        if (!retryPos.isEmpty() && retryPos.size() < surroundPos.length && smart.getValue()) {
            for (final BlockPos pos : retryPos) {
                final BlockPos newPos = addPos(pos);
                if (BlockUtil.isPositionPlaceable(newPos, false) < 2) continue;
                final int slot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
                if (slot == -1) this.toggle();
                if (BlockUtil.placeBlock(newPos, slot, rotate.getValue(), rotate.getValue())) {
                    retryPos.remove(newPos);
                }
            }
            return;
        }

        for (final BlockPos pos : surroundPos) {
            final BlockPos newPos = addPos(pos);
            if (BlockUtil.isPositionPlaceable(newPos, false) < 2) continue;
            final int slot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            if (slot == -1) this.toggle();
            if (!BlockUtil.placeBlock(newPos, slot, rotate.getValue(), rotate.getValue())) {
                retryPos.add(newPos);
            }
        }
    }

    private BlockPos addPos(final BlockPos pos) {
        final BlockPos pPos = PlayerUtil.getPlayerPos(0.2);
        return new BlockPos(pPos.getX() + pos.getX(), pPos.getY() + pos.getY(), pPos.getZ() + pos.getZ());
    }

    private double getDst(final Vec3d vec) {
        return mc.player.getPositionVector().distanceTo(vec);
    }

    private void centerPlayer(double x, double y, double z) {
        mc.player.connection.sendPacket(new CPacketPlayer.Position(x, y, z, true));
        mc.player.setPosition(x, y, z);
    }
}


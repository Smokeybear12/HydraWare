package club.auth.hydraware.module.modules.player;

import club.auth.hydraware.command.Messages;
import club.auth.hydraware.manager.FriendsManager;
import club.auth.hydraware.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;

public class MCF extends Module {
    @EventHandler
    private final Listener<InputEvent.MouseInputEvent> listener = new Listener<>(event -> {
        if (mc.objectMouseOver.typeOfHit.equals(RayTraceResult.Type.ENTITY) && mc.objectMouseOver.entityHit instanceof EntityPlayer && Mouse.isButtonDown(2)) {
            if (FriendsManager.isFriend(mc.objectMouseOver.entityHit.getName())) {
                FriendsManager.removeFriend(mc.objectMouseOver.entityHit.getName());
                Messages.sendClientMessage("removed friend: " + mc.objectMouseOver.entityHit.getName());
            } else {
                FriendsManager.addFriend(mc.objectMouseOver.entityHit.getName());
                Messages.sendSilentMessage("added friend: " + mc.objectMouseOver.entityHit.getName());
            }
        }
    });

    public MCF() {
        super("MCF", "Middle click friend.", 0, Category.PLAYER);
    }
}
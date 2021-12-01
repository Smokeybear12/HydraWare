package club.auth.hydraware.module.modules.main;

import com.mojang.realmsclient.gui.ChatFormatting;
import club.auth.hydraware.HydraWare;
import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import club.auth.hydraware.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Hud extends Module {
    public Hud() {
        super("Hud","Its Hud Does it Need A Explanation?", 0,Category.MAIN);
    }

    SettingBoolean csgowatermark = this.register("CSGOWatermark",true);
    SettingBoolean watermark = this.register("Watermark",true);
    SettingBoolean greeter = this.register("Greeter",true);
    SettingBoolean ping = this.register("Ping",true);
    SettingBoolean fps = this.register("Fps",true);

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            if (csgowatermark.getValue()) {
                String ping = HydraWare.serverManager.getPing() + "ms ";
                String server = mc.isSingleplayer() ? "singleplayer" : Objects.requireNonNull(mc.getCurrentServerData()).serverIP;
                String time = new SimpleDateFormat("k:mm").format(new Date());
                String name = mc.player.getName();
                String text = " " + HydraWare.NAME + " | " + name + " | " + time + " | " + server + " | " + ping;
                float width = (float) (HydraWare.fontManager.getStringWidth(text) + 6);
                int height = 20;
                int posX = 2;
                int posY = 2;
                RenderUtil.drawRect(posX, posY, (float) posX + width + 2.0F, posY + height, (new Color(5, 5, 5, 255)).getRGB());
                RenderUtil.drawBorderedRect((double) posX + 0.5D, (double) posY + 0.5D, (double) ((float) posX + width) + 1.5D, (double) (posY + height) - 0.5D, 0.5D, (new Color(40, 40, 40, 255)).getRGB(), (new Color(60, 60, 60, 255)).getRGB(), true);
                RenderUtil.drawBorderedRect(posX + 2, posY + 2, (float) posX + width, posY + height - 2, 0.5D, (new Color(22, 22, 22, 255)).getRGB(), (new Color(60, 60, 60, 255)).getRGB(), true);
                RenderUtil.drawRect((double) posX + 2.5D, (double) posY + 2.5D, (double) ((float) posX + width) - 0.5D, (double) posY + 4.5D, (new Color(9, 9, 9, 255)).getRGB());
                RenderUtil.drawGradientSideways(4.0D, posY + 3, 4.0F + width / 3.0F, posY + 4, (new Color(81, 149, 219, 255)).getRGB(), (new Color(180, 49, 218, 255)).getRGB());
                RenderUtil.drawGradientSideways(4.0F + width / 3.0F, posY + 3, 4.0F + width / 3.0F * 2.0F, posY + 4, (new Color(180, 49, 218, 255)).getRGB(), (new Color(236, 93, 128, 255)).getRGB());
                RenderUtil.drawGradientSideways(4.0F + width / 3.0F * 2.0F, posY + 3, width / 3.0F * 3.0F + 1.0F, posY + 4, (new Color(236, 93, 128, 255)).getRGB(), (new Color(235, 255, 0, 255)).getRGB());
                HydraWare.fontManager.drawStringWithShadow(text, (float) (4 + posX), (float) (8 + posY), -1);
            }
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (greeter.getValue()) {
                HydraWare.fontManager.drawStringWithShadow("Welcome to Hydra Ware, " + mc.player.getName(), 250, 2, 102 - 153);
            }
            int i = -8;
            if (watermark.getValue()) {
                i = i + 10;
                HydraWare.fontManager.drawStringWithShadow(HydraWare.NAME_VERSION, 2, i, 102 - 153);
            }
            if (ping.getValue()) {
                i = i + 10;
                String ping = "Ping " + HydraWare.serverManager.getPing();
                HydraWare.fontManager.drawStringWithShadow(ping, 2, i, 102 - 153);
            }
            if (fps.getValue()) {
                i = i + 10;
                String fps = "FPS " + Minecraft.debugFPS;
                HydraWare.fontManager.drawStringWithShadow(fps, 2, i, 102 - 153);
            }
        }
    }
}

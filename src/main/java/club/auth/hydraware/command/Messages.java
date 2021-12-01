package club.auth.hydraware.command;

import club.auth.hydraware.HydraWare;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class Messages {

    public static void sendPlayerMessage(String... message) {
        for (String m : message) Minecraft.getMinecraft().player.sendChatMessage(m);
    }

    public static void sendSilentMessage(String... message) {
        for (String m : message) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(m));
        }
    }

    public static void sendClientMessage(String... message) {
        for (String m : message) {
            String prefix = ChatFormatting.BLACK + "[ " + HydraWare.NAME + " ] " + (ChatFormatting.RESET);
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(prefix + m));
        }
    }
}

package club.auth.hydraware.module.modules.main;

import club.auth.hydraware.HydraWare;
import club.auth.hydraware.module.Module;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIBlur extends Module {
    public GUIBlur() {
        super("GUIBlur", "", 0, Category.MAIN);
    }

    public void onDisable() {
        if (mc.world != null) {
            mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    public void update() {
        if (mc.world != null) {
            if (mc.currentScreen == HydraWare.instance.clickGui) {// Checks for the gui
                if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer) {
                    if (mc.entityRenderer.getShaderGroup() != null) {
                        mc.entityRenderer.getShaderGroup().deleteShaderGroup();// This shit is just here so it doesnt crash// SUPER SECRET SHADER
                    }
                    try {
                        mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));// Adds shader// no its a super secret shader built in minecraft
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (mc.entityRenderer.getShaderGroup() != null && mc.currentScreen == null) {
                    mc.entityRenderer.getShaderGroup().deleteShaderGroup();// This shit is just here so it doesnt crash
                }
            } else {
                if (mc.entityRenderer.getShaderGroup() != null) {
                    mc.entityRenderer.getShaderGroup().deleteShaderGroup();// This shit is just here so it doesnt crash// its not SALGACk, its FROM MINECRAFT,  ITS FR
                }
            }
        }
    }
}

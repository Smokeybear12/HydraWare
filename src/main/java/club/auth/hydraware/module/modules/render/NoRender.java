package club.auth.hydraware.module.modules.render;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.settings.SettingBoolean;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoRender extends Module {
    SettingBoolean weather = register("Weather", false);
    SettingBoolean viewBobbing = register("ViewBobbing", false);
    SettingBoolean items = register("Items", false);
    SettingBoolean overlay = register("Overlay", false);
    public NoRender() {
        super("NoRender", "No render things", 0, Category.RENDER);
    }

    @Override
    public void update() {
        if ((weather.getValue()) && mc.world.isRaining()) {
            mc.world.setRainStrength(0);
        }
        if (items.getValue()) {
            mc.world.loadedEntityList.stream().filter(EntityItem.class::isInstance).map(EntityItem.class::cast).forEach(Entity::setDead);
        }
    }

    @Override
    public void onEnable() {
        if (viewBobbing.getValue()) {
            mc.gameSettings.viewBobbing = false;
        }
    }

    @Override
    public void onDisable() {
        if (viewBobbing.getValue()) {
            mc.gameSettings.viewBobbing = true;
        }
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        if (overlay.getValue()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEvent(RenderGameOverlayEvent event) {
        if (overlay.getValue() && event.getType().equals(RenderGameOverlayEvent.ElementType.HELMET) || event.getType().equals(RenderGameOverlayEvent.ElementType.PORTAL)) {
            event.setCanceled(true);
        }
    }
}

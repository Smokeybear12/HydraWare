package club.auth.hydraware.gui;

import club.auth.hydraware.HydraWare;
import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.Setting;
import club.auth.hydraware.setting.settings.SettingBoolean;
import club.auth.hydraware.setting.settings.SettingDouble;
import club.auth.hydraware.setting.settings.SettingInteger;
import club.auth.hydraware.setting.settings.SettingMode;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Screen extends GuiScreen {

    public static final Point MOUSE = new Point();
    private final List<IComponent> PANELS = new ArrayList<>();

    public Screen() {
        Point offset = new Point(10, 10);
        for (Module.Category cat : Module.Category.values()) {
            PanelComponent panel = new PanelComponent(new Rectangle(new Point(offset), new Dimension(100, 10)), cat.name());
            HydraWare.instance.moduleManager.getModsInCategory(cat).forEach(m -> {
                ModuleComponent module = new ModuleComponent(m);
                HydraWare.instance.settingsManager.getSettingsInMod(m).forEach(s -> {
                    switch (s.getType()) {
                        case INTEGER:
                            module.addChild(new SliderInteger((SettingInteger) s));
                            break;
                        case BOOLEAN:
                            module.addChild(new BooleanComponent((SettingBoolean) s));
                            break;
                        case DOUBLE:
                            module.addChild(new SliderDouble((SettingDouble) s));
                            break;
                        case MODE:
                            module.addChild(new ModeComponent((SettingMode) s));
                            break;
                    }
                });
                module.addChild(new KeybindComponent(m));
                panel.addChild(module);
            });
            PANELS.add(panel);
            offset.translate(120, 0);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        MOUSE.setLocation(mouseX, mouseY);
        PANELS.forEach(IComponent::draw);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        MOUSE.setLocation(mouseX, mouseY);
        PANELS.forEach(p -> p.handleButton(mouseButton));
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        MOUSE.setLocation(mouseX, mouseY);
        PANELS.forEach(p -> p.handleButton(-1));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == 1) mc.displayGuiScreen(null);
        PANELS.forEach(p -> p.keyTyped(keyCode, typedChar));
    }
}

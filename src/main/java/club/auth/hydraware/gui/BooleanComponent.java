package club.auth.hydraware.gui;

import club.auth.hydraware.setting.settings.SettingBoolean;

import java.awt.*;

public class BooleanComponent extends AbstractComponent {

    private final SettingBoolean setting;

    public BooleanComponent(SettingBoolean setting) {
        super(new Rectangle());
        this.setting = setting;
    }

    @Override
    public void draw() {
        IComponent.fillRect(rect, setting.getValue() ? new Color(110, 110, 191, 170) : new Color(78, 65, 119, 120));
        IComponent.drawString(setting.getName(), new Point(rect.x + 1, rect.y + 2), Color.WHITE);
    }

    @Override
    public void handleButton(int btn) {
        if (rect.contains(Screen.MOUSE) && btn != -1) {
            setting.setValue(!setting.getValue());
        }
    }
}

package club.auth.hydraware.gui;

import club.auth.hydraware.setting.settings.SettingMode;

import java.awt.*;

public class ModeComponent extends AbstractComponent {

    private final SettingMode setting;

    public ModeComponent(SettingMode setting) {
        super(new Rectangle());
        this.setting = setting;
    }

    @Override
    public void draw() {
        IComponent.fillRect(rect, new Color(20, 20, 20, 120));
        IComponent.fillRect(new Rectangle(rect.x, rect.y, 12, rect.height), new Color(42, 28, 50, 200));
        IComponent.fillRect(new Rectangle(rect.x + rect.width - 12, rect.y, 12, rect.height), new Color(42, 28, 50, 200));
        IComponent.drawString(setting.getName() + ": " + setting.getValue(), new Point(rect.x + 20, rect.y + 2), Color.WHITE);
    }

    @Override
    public void handleButton(int btn) {
        if (rect.contains(Screen.MOUSE)) {
            if (btn == 0) setting.increment();
            else if (btn == 1) setting.decrement();
        }
    }
}

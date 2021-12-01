package club.auth.hydraware.gui;

import club.auth.hydraware.setting.settings.SettingDouble;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class SliderDouble extends AbstractComponent {

    private final SettingDouble setting;
    private boolean sliding = false;

    public SliderDouble(SettingDouble setting) {
        super(new Rectangle());
        this.setting = setting;
    }

    @Override
    public void draw() {
        double Multiplier = (setting.getValue() - setting.getMin()) / (setting.getMax() - setting.getMin());
        IComponent.fillRect(rect, new Color(70, 70, 70, 140));
        IComponent.fillRect(new Rectangle(rect.x, rect.y, (int) (rect.width * Multiplier), rect.height), new Color(42, 28, 50, 200));
        IComponent.drawString(setting.getName() + ": " + setting.getValue(), new Point(rect.x + 1, rect.y + 2), Color.WHITE);
        if (sliding) {
            double diff = MathHelper.clamp((Screen.MOUSE.getX() - rect.getX()) / (rect.getWidth() - 1D), 0D, 1D);
            setting.setValue((int) ((setting.getMax() - setting.getMin()) * diff + setting.getMin()));
        }
    }

    @Override
    public void handleButton(int btn) {
        if (btn != -1 && rect.contains(Screen.MOUSE)) sliding = true;
        else if (sliding) sliding = false;
    }
}

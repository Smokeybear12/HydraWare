package club.auth.hydraware.gui;

import club.auth.hydraware.module.Module;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class KeybindComponent extends AbstractComponent {

    private final Module module;
    private boolean listening = false;

    public KeybindComponent(Module module) {
        super(new Rectangle());
        this.module = module;
    }

    @Override
    public void draw() {
        IComponent.fillRect(rect, listening ? new Color(110, 110, 191, 170) : new Color(78, 65, 119, 120));
        IComponent.drawString("Bind: " + (listening ? "..." : Keyboard.getKeyName(module.getKey())), new Point(rect.x + 1, rect.y + 2), Color.WHITE);
    }

    @Override
    public void handleButton(int btn) {
        if (rect.contains(Screen.MOUSE) && btn != -1) {
            listening = !listening;
        }
    }

    @Override
    public void keyTyped(int key, char ch) {
        if (listening) {
            if (key == Keyboard.KEY_BACK || key == Keyboard.KEY_DELETE) {
                module.setKey(0);
                return;
            }
            module.setKey(key);
            listening = false;
        }
    }
}

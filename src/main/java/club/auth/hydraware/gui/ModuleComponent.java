package club.auth.hydraware.gui;

import club.auth.hydraware.module.Module;

import java.awt.*;

public class ModuleComponent extends AbstractComponent {

    private final Module module;
    private final ContainerComponent container = new ContainerComponent(2, 2, 4);
    private boolean opened = false;

    public ModuleComponent(Module module) {
        super(new Rectangle());
        this.module = module;
    }

    @Override
    public void draw() {
        IComponent.fillRect(rect, module.isToggled() ? new Color(110, 110, 191, 170) : new Color(78, 65, 119, 120));
        IComponent.drawString(module.getName(), new Point(rect.x + 1, rect.y + 2), Color.WHITE);
        if (opened) {
            container.setRect(rect);
            container.draw();
        }
    }

    @Override
    public void handleButton(int btn) {
        if (rect.contains(Screen.MOUSE)) {
            if (btn == 0) {
                module.toggle();
            } else if (btn == 1) {
                opened = !opened;
            }
        }
        if (opened) container.handleButton(btn);
    }

    @Override
    public void keyTyped(int key, char ch) {
        if (opened) container.keyTyped(key, ch);
    }

    @Override
    public int getAbsoluteHeight() {
        return rect.height + (opened ? container.getAbsoluteHeight() : 0);
    }

    @Override
    public void addChild(IComponent component) {
        container.addChild(component);
    }
}

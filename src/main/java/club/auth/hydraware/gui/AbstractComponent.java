package club.auth.hydraware.gui;

import java.awt.*;

public abstract class AbstractComponent implements IComponent {

    protected final Rectangle rect;

    public AbstractComponent(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void handleButton(int btn) {

    }

    @Override
    public void keyTyped(int key, char ch) {

    }

    @Override
    public int getAbsoluteHeight() {
        return rect.height;
    }

    @Override
    public void addChild(IComponent component) {

    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void setRect(Rectangle rect1) {
        rect.setRect(rect1);
    }
}

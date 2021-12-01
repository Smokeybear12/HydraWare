package club.auth.hydraware.event.events;

import club.auth.hydraware.event.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class PushEvent extends Event {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public double x;
    public double y;
    public double z;
    public Entity entity;

    public PushEvent(Entity entity, double x, double y, double z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


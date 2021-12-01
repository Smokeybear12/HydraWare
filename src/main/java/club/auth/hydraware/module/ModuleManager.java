package club.auth.hydraware.module;

import club.auth.hydraware.module.modules.combat.*;
import club.auth.hydraware.module.modules.main.GUI;
import club.auth.hydraware.module.modules.main.GUIBlur;
import club.auth.hydraware.module.modules.main.Hud;
import club.auth.hydraware.module.modules.misc.AutoFish;
import club.auth.hydraware.module.modules.misc.ChestStealer;
import club.auth.hydraware.module.modules.misc.FastPlace;
import club.auth.hydraware.module.modules.movement.*;
import club.auth.hydraware.module.modules.player.*;
import club.auth.hydraware.module.modules.render.CustomTime;
import club.auth.hydraware.module.modules.render.FullBright;
import club.auth.hydraware.module.modules.render.NoRender;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ModuleManager {

    public ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<Module>();
        MinecraftForge.EVENT_BUS.register(this);
        init();
    }

    public void init() {
        modules.add(new Offhand());
        modules.add(new AutoArmor());
        modules.add(new AutoRespawn());
        modules.add(new AntiHunger());
        modules.add(new AutoWalk());
        modules.add(new AntiVoid());
        modules.add(new AutoFish());
        modules.add(new GUI());
//        modules.add(new HauseCry());
        modules.add(new FakePlayer());
        modules.add(new IceSpeed());
        modules.add(new ReverseStep());
        modules.add(new Step());
        modules.add(new Sprint());
        modules.add(new Velocity());
        modules.add(new FullBright());
        modules.add(new Gamemode());
        modules.add(new CustomTime());
        modules.add(new Quiver());
        modules.add(new NoRender());
        modules.add(new FastPlace());
        modules.add(new ChestStealer());
        modules.add(new EntitySpeed());
        modules.add(new Vanish());
        modules.add(new MCP());
        modules.add(new KillAura());
        modules.add(new Hud());
        modules.add(new Replenish());
        modules.add(new SilentEXP());
        modules.add(new MCF());
        modules.add(new Surround());
        modules.add(new GUIBlur());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Module> getModsInCategory(Module.Category cat) {
        ArrayList<Module> mods = new ArrayList<Module>();
        for (Module m : modules) {
            if (m.getCategory() == cat) {
                mods.add(m);
            }
        }
        return mods;
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            for (Module m : modules) {
                if (m.getKey() == Keyboard.getEventKey()) {
                    m.toggle();
                }
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().world == null)
            return;
        for (Module m : modules) {
            if (m.isToggled()) {
                m.update();
            }
        }
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        for (Module m : modules) {
            if (m.isToggled()) {
                m.render();
            }
        }
    }
}

package club.auth.hydraware;

import com.google.common.base.Stopwatch;
import club.auth.hydraware.event.EventProcessor;
import club.auth.hydraware.gui.Screen;
import club.auth.hydraware.manager.*;
import club.auth.hydraware.module.Module;
import club.auth.hydraware.module.ModuleManager;
import club.auth.hydraware.setting.SettingsManager;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;

@Mod(modid = HydraWare.MODID, name = HydraWare.NAME, version = HydraWare.VERSION)
public class HydraWare
{
    public static final String MODID = "hydraware";
    public static final String NAME = "HydraWare";
    public static final String VERSION = "1.0.0";
    public static final String NAME_VERSION = "HydraWare - 1.0.0";

    @Mod.Instance
    public static HydraWare instance = new HydraWare();
    public static final EventManager EVENT_BUS = new EventManager();

    public static ModuleManager moduleManager;
    public static SettingsManager settingsManager;
    public static FontManager fontManager;
    public static ServerManager serverManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;

    public Screen clickGui;
    

    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(instance);
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        fontManager = new FontManager();
        serverManager = new ServerManager();
        rotationManager = new RotationManager();
        positionManager = new PositionManager();
        new EventProcessor();
        clickGui = new Screen();

        // $$$
        Stopwatch watch = Stopwatch.createStarted();
        ConfigManager.load();
        System.out.printf("AuthX Is God -Everyone");
        System.out.printf("i made ISIS Cry. -AuthX");
        System.out.printf("HydraWare load config took %sms", watch.stop());
      
        // $$$
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Display.setTitle("HydraWare | 1.0.0 | <3");
    }
}

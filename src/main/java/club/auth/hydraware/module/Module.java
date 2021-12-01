package club.auth.hydraware.module;

import club.auth.hydraware.HydraWare;
import club.auth.hydraware.command.Messages;
import club.auth.hydraware.setting.settings.SettingBoolean;
import club.auth.hydraware.setting.settings.SettingDouble;
import club.auth.hydraware.setting.settings.SettingInteger;
import club.auth.hydraware.setting.settings.SettingMode;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class Module {

    public static final Minecraft mc = Minecraft.getMinecraft();

    public String name;
    public String description;
    public int key;
    public Category category;
    public boolean toggled;

    public Module(String name, String description, int key, Category category) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.category = category;
    }

    public void enable() {
        HydraWare.EVENT_BUS.subscribe(this);
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
        Messages.sendClientMessage(ChatFormatting.BOLD + getName() + ChatFormatting.GREEN + " enabled.");
    }

    public void disable() {
        HydraWare.EVENT_BUS.unsubscribe(this);
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
        Messages.sendClientMessage(ChatFormatting.BOLD + getName() + ChatFormatting.RED + " disabled.");
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) enable();
        else disable();
    }

    public void update() {
    }

    public void render() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onToggle() {
    }

    public void onLogin() {
    }

    public void onLogout() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public SettingBoolean register(String name, boolean value) {
        SettingBoolean set = new SettingBoolean(name, this, value);
        HydraWare.instance.settingsManager.settings.add(set);
        return set;
    }

    public SettingMode register(String name, ArrayList<String> values, String value) {
        SettingMode set = new SettingMode(name, this, values, value);
        HydraWare.instance.settingsManager.settings.add(set);
        return set;
    }

    public SettingInteger register(String name, int value, int min, int max) {
        SettingInteger set = new SettingInteger(name, this, value, min, max);
        HydraWare.instance.settingsManager.settings.add(set);
        return set;
    }

    public SettingDouble register(String name, double value, double min, double max) {
        SettingDouble set = new SettingDouble(name, this, (int) value, (int) min, (int) max);
        HydraWare.instance.settingsManager.settings.add(set);
        return set;
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public enum Category {
        MAIN,
        COMBAT,
        MOVEMENT,
        RENDER,
        MISC,
        PLAYER
    }
}

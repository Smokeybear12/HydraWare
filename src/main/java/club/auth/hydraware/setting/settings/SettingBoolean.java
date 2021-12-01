package club.auth.hydraware.setting.settings;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.setting.Setting;

public class SettingBoolean extends Setting {
    public boolean value;

    public SettingBoolean(String name, Module mod, Boolean value) {
        this.name = name;
        this.mod = mod;
        this.value = value;
        this.type = Type.BOOLEAN;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}

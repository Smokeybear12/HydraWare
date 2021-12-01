package club.auth.hydraware.mixin.mixins;

import club.auth.hydraware.manager.ConfigManager;
import com.google.common.base.Stopwatch;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void onShutdown(CallbackInfo ci) {
        Stopwatch watch = Stopwatch.createStarted();
        ConfigManager.save();
        System.out.printf("hydraware save config took %sms", watch.stop());
        // ci.cancel(); // ez :troll:
    }

}

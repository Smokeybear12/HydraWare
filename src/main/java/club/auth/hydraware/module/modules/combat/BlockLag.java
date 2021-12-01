package club.auth.hydraware.module.modules.combat;

import club.auth.hydraware.module.Module;
import club.auth.hydraware.util.Wrapper;

/**
 * @author Auth
 * half the code is scaffold xD
 */

public class BlockLag extends Module {
    public BlockLag() {
        super("Burrow", "Its just burrow in the name of blocklag.", 0, Category.COMBAT);
    }

    @Override
    public void onEnable() {
        //Scaffold scaffold = (Scaffold) BlazeWare.getInstance().moduleManager.getModuleByName("Scaffold");
        //scaffold.enable();
        Wrapper.getPlayer().jump();
        --Wrapper.getPlayer().motionY;
    }

    //@Override
    //public void onDisable(){
    //Scaffold scaffold = (Scaffold) BlazeWare.getInstance().moduleManager.getModuleByName("Scaffold");
    //if (scaffold.isEnabled()) scaffold.disable();
}
//}

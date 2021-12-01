package club.auth.hydraware.capes;

import club.auth.hydraware.util.Wrapper;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CapeUtil {

    public static ArrayList<String> lines;

    public static List<ResourceLocation> capeStuff = new ArrayList<ResourceLocation>();

    static {
        try {
            capeStuff.add(Wrapper.mc.getTextureManager().getDynamicTextureLocation("assets/textures", new DynamicTexture(ImageIO.read(new URL("https://imgur.com/a/W84LYTB")))));
            capeStuff.add(Wrapper.mc.getTextureManager().getDynamicTextureLocation("assets/textures", new DynamicTexture(ImageIO.read(new URL("https://imgur.com/a/W84LYTB")))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        CapeUtil.lines = new ArrayList<String>();
    }

    public static void getUsersCape() {
        try {
            final URL url = new URL("https://pastebin.com/raw/ksdL26BT");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                CapeUtil.lines.add(line);
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isCapeUser(final String name) {
        return CapeUtil.lines.contains(name);
    }

}

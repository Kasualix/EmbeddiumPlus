package me.srrapero720.embeddiumplus;

import me.srrapero720.dynamiclights.LambDynLights;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EmbeddiumPlus.ID)
public class EmbeddiumPlus {
    public static final String ID = "embeddiumplus";
    public static final Logger LOGGER = LogManager.getLogger();

    public EmbeddiumPlus() {
        EmbPlusConfig.load();
        if (FMLLoader.getDist().isClient()) {
            LambDynLights.init();
        }
    }
}
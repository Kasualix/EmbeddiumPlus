package me.srrapero720.embeddiumplus.features.frame_overlay;

import me.srrapero720.embeddiumplus.EmbeddiumPlus;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderGuiOverlayEvent;

@Mod.EventBusSubscriber(modid = EmbeddiumPlus.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DebugOverlayImprovements {
    @SubscribeEvent
    public static void onRenderDebugText(RenderGuiOverlayEvent.Pre event) {
        if (!event.getOverlay().id().getPath().equals("debug_text")) return;

        // cancel rendering text if chart is displaying
        var minecraft = Minecraft.getInstance();
        if (minecraft.getDebugOverlay().renderFpsCharts)
            event.setCanceled(true);
    }
}
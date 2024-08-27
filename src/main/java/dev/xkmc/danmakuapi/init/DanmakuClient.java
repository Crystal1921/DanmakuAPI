package dev.xkmc.danmakuapi.init;

import com.github.tartaricacid.touhoulittlemaid.TouhouLittleMaid;
import dev.xkmc.danmakuapi.presets.model.PresetModels;
import dev.xkmc.danmakuapi.presets.touhoulittlemaid.TLMRenderHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(value = Dist.CLIENT, modid = DanmakuAPI.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DanmakuClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		if (ModList.get().isLoaded(TouhouLittleMaid.MOD_ID)) {
			NeoForge.EVENT_BUS.register(TLMRenderHandler.class);
		}
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(PresetModels.RUMIA_LAYER, PresetModels::createRumia);
		event.registerLayerDefinition(PresetModels.REIMU_LAYER, PresetModels::createReimu);
	}

	@SubscribeEvent
	public static void addLayer(EntityRenderersEvent.AddLayers event) {
		if (ModList.get().isLoaded(TouhouLittleMaid.MOD_ID)) {
			TLMRenderHandler.addLayers(event);
		}
	}

}

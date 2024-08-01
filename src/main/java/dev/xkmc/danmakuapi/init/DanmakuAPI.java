package dev.xkmc.danmakuapi.init;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import dev.xkmc.danmakuapi.init.data.YHDamageTypes;
import dev.xkmc.danmakuapi.init.data.YHLangData;
import dev.xkmc.danmakuapi.init.registrate.YHDanmaku;
import dev.xkmc.danmakuapi.init.registrate.YHEntities;
import dev.xkmc.danmakuapi.init.registrate.YHItems;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(DanmakuAPI.MODID)
@EventBusSubscriber(modid = DanmakuAPI.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DanmakuAPI {

	public static final String MODID = "danmaku_api";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final SimpleEntry<CreativeModeTab> TAB =
			REGISTRATE.buildModCreativeTab("danmaku_api", "Danmaku API",
					e -> e.icon(YHItems.REIMU_SPELL::asStack));

	public DanmakuAPI() {
		YHItems.register();
		YHDanmaku.register();
		YHEntities.register();

		REGISTRATE.addDataGenerator(ProviderType.LANG, YHLangData::genLang);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gatherData(GatherDataEvent event) {
		new YHDamageTypes(REGISTRATE).generate();

	}

	public static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(MODID, id);
	}
}

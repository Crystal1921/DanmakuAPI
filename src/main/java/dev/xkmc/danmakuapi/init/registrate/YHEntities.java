package dev.xkmc.danmakuapi.init.registrate;

import com.tterrag.registrate.util.entry.EntityEntry;
import dev.xkmc.danmakuapi.entity.danmaku.ItemDanmakuEntity;
import dev.xkmc.danmakuapi.entity.danmaku.ItemDanmakuRenderer;
import dev.xkmc.danmakuapi.entity.danmaku.ItemLaserEntity;
import dev.xkmc.danmakuapi.entity.danmaku.ItemLaserRenderer;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import net.minecraft.world.entity.MobCategory;

public class YHEntities {


	public static final EntityEntry<ItemDanmakuEntity> ITEM_DANMAKU;
	public static final EntityEntry<ItemLaserEntity> ITEM_LASER;

	static {

		ITEM_DANMAKU = DanmakuAPI.REGISTRATE
				.<ItemDanmakuEntity>entity("item_danmaku", ItemDanmakuEntity::new, MobCategory.MISC)
				.properties(e -> e.sized(0.4f, 0.4f).clientTrackingRange(4).updateInterval(1 << 16))
				.renderer(() -> ItemDanmakuRenderer::new)
				.register();

		ITEM_LASER = DanmakuAPI.REGISTRATE
				.<ItemLaserEntity>entity("item_laser", ItemLaserEntity::new, MobCategory.MISC)
				.properties(e -> e.sized(0.4f, 0.4f).clientTrackingRange(4).updateInterval(1 << 16))
				.renderer(() -> ItemLaserRenderer::new)
				.register();


	}

	public static void register() {
	}

}

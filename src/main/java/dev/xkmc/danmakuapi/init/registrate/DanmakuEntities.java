package dev.xkmc.danmakuapi.init.registrate;

import com.tterrag.registrate.util.entry.EntityEntry;
import dev.xkmc.danmakuapi.content.entity.ItemBulletEntity;
import dev.xkmc.danmakuapi.content.entity.ItemBulletRenderer;
import dev.xkmc.danmakuapi.content.entity.ItemLaserEntity;
import dev.xkmc.danmakuapi.content.entity.ItemLaserRenderer;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import net.minecraft.world.entity.MobCategory;

public class DanmakuEntities {


	public static final EntityEntry<ItemBulletEntity> ITEM_DANMAKU;
	public static final EntityEntry<ItemLaserEntity> ITEM_LASER;

	static {

		ITEM_DANMAKU = DanmakuAPI.REGISTRATE
				.<ItemBulletEntity>entity("item_danmaku", ItemBulletEntity::new, MobCategory.MISC)
				.properties(e -> e.sized(0.4f, 0.4f).clientTrackingRange(4).updateInterval(1 << 16))
				.renderer(() -> ItemBulletRenderer::new)
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

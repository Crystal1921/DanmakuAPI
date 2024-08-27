package dev.xkmc.danmakuapi.init.registrate;

import com.tterrag.registrate.util.entry.EntityEntry;
import dev.xkmc.danmakuapi.content.entity.ItemBulletEntity;
import dev.xkmc.danmakuapi.content.entity.ItemBulletRenderer;
import dev.xkmc.danmakuapi.content.entity.ItemLaserEntity;
import dev.xkmc.danmakuapi.content.entity.ItemLaserRenderer;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.danmakuapi.presets.model.GeneralYoukaiRenderer;
import dev.xkmc.danmakuapi.presets.youkai.BossYoukaiEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;

public class DanmakuEntities {


	public static final EntityEntry<ItemBulletEntity> ITEM_DANMAKU;
	public static final EntityEntry<ItemLaserEntity> ITEM_LASER;

	public static final EntityEntry<BossYoukaiEntity> GENERAL_YOUKAI;

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

		GENERAL_YOUKAI = DanmakuAPI.REGISTRATE
				.entity("youkai", BossYoukaiEntity::new, MobCategory.MONSTER)
				.properties(e -> e.sized(0.4F, 1.8f).clientTrackingRange(10))
				.attributes(BossYoukaiEntity::createAttributes)
				.renderer(() -> GeneralYoukaiRenderer::new)
				.spawnEgg(0xa93937, 0xfaf5f2).tab(DanmakuAPI.TAB.key()).build()
				.loot((pvd, type) -> pvd.add(type, LootTable.lootTable()))
				.register();

	}

	public static void register() {
	}

}

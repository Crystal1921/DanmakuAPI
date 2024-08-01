package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.api.IDanmakuEntity;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2damagetracker.init.data.DamageTypeAndTagsGen;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.Tags;

public class DanmakuDamageTypes extends DamageTypeAndTagsGen {

	public static final ResourceKey<DamageType> DANMAKU = createDamage("danmaku");
	public static final ResourceKey<DamageType> ABYSSAL = createDamage("abyssal_danmaku");

	public static final TagKey<DamageType> DANMAKU_TYPE = TagKey.create(Registries.DAMAGE_TYPE, DanmakuAPI.loc("danmaku"));

	private static final DamageTypeTagGroup DANMAKU_GROUP = DamageTypeTagGroup.of(DamageTypeTags.NO_IMPACT,
			Tags.DamageTypes.IS_MAGIC, DamageTypeTags.BYPASSES_ARMOR, DamageTypeTags.BYPASSES_COOLDOWN, DANMAKU_TYPE);

	public DanmakuDamageTypes(L2Registrate reg) {
		super(reg);
		new DamageTypeHolder(DANMAKU, new DamageType("danmaku", 0.1f))
				.add(DANMAKU_GROUP);
		new DamageTypeHolder(ABYSSAL, new DamageType("abyssal_danmaku", 0.1f))
				.add(DANMAKU_GROUP).add(L2DamageTypes.BYPASS_MAGIC);
	}

	private static ResourceKey<DamageType> createDamage(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, DanmakuAPI.loc(id));
	}

	public static DamageSource danmaku(IDanmakuEntity self) {
		return new DamageSource(self.self().level().registryAccess()
				.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DANMAKU), self.self(),
				self.self().getOwner());
	}

	public static DamageSource abyssal(IDanmakuEntity self) {
		return new DamageSource(self.self().level().registryAccess()
				.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ABYSSAL), self.self(),
				self.self().getOwner());
	}


}

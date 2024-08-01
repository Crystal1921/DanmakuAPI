package dev.xkmc.danmakuapi.api;

import net.minecraft.world.entity.LivingEntity;

public interface IYoukaiEntity {

	boolean shouldHurt(LivingEntity le);

	void onDanmakuHit(LivingEntity target, IDanmakuEntity bullet);

}

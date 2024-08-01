package dev.xkmc.danmakuapi.api;

import dev.xkmc.danmakuapi.entity.danmaku.IYHDanmaku;
import net.minecraft.world.entity.LivingEntity;

public interface IYoukaiEntity {

	boolean shouldHurt(LivingEntity le);

	void onDanmakuHit(LivingEntity target, IYHDanmaku bullet);

}

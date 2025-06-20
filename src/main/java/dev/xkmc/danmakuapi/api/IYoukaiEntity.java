package dev.xkmc.danmakuapi.api;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public interface IYoukaiEntity {

	boolean shouldHurt(LivingEntity le);

	void onDanmakuHit(LivingEntity target, IDanmakuEntity bullet);

	void onDanmakuImmune(LivingEntity target, IDanmakuEntity iDanmakuEntity, DamageSource source);

	default void danmakuHitTarget(IDanmakuEntity self, DamageSource source, LivingEntity target) {
		float hp = target.getHealth();
		boolean immune = !target.hurt(source, self.damage(target));
		float ahp = target.getHealth();
		if (ahp >= hp && ahp > 0) immune = true;
		onDanmakuHit(target, self);
		if (immune) {
			onDanmakuImmune(target, self, source);
		}
	}

	boolean isTarget(LivingEntity e);

	AABB getBoundingBoxForDanmaku();

}

package dev.xkmc.danmakuapi.spell.spellcard;

import dev.xkmc.danmakuapi.api.DanmakuBullet;
import dev.xkmc.danmakuapi.api.DanmakuLaser;
import dev.xkmc.danmakuapi.entity.danmaku.IYHDanmaku;
import dev.xkmc.danmakuapi.entity.danmaku.ItemDanmakuEntity;
import dev.xkmc.danmakuapi.entity.danmaku.ItemLaserEntity;
import dev.xkmc.danmakuapi.init.data.YHDamageTypes;
import dev.xkmc.fastprojectileapi.entity.SimplifiedProjectile;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public interface CardHolder {

	Vec3 center();

	Vec3 forward();

	@Nullable
	Vec3 target();

	RandomSource random();

	ItemDanmakuEntity prepareDanmaku(int life, Vec3 vec, DanmakuBullet type, DyeColor color);

	ItemLaserEntity prepareLaser(int life, Vec3 pos, Vec3 vec, int len, DanmakuLaser type, DyeColor color);

	void shoot(SimplifiedProjectile danmaku);

	LivingEntity self();

	default DamageSource getDanmakuDamageSource(IYHDanmaku danmaku) {
		return YHDamageTypes.danmaku(danmaku);
	}

	@Nullable
	Vec3 targetVelocity();

}

package dev.xkmc.danmakuapi.content.spell.spellcard;

import dev.xkmc.danmakuapi.api.DanmakuBullet;
import dev.xkmc.danmakuapi.api.DanmakuLaser;
import dev.xkmc.danmakuapi.api.IDanmakuEntity;
import dev.xkmc.danmakuapi.content.entity.ItemBulletEntity;
import dev.xkmc.danmakuapi.content.entity.ItemLaserEntity;
import dev.xkmc.danmakuapi.init.data.DanmakuDamageTypes;
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

	ItemBulletEntity prepareDanmaku(int life, Vec3 vec, DanmakuBullet type, DyeColor color);

	ItemLaserEntity prepareLaser(int life, Vec3 pos, Vec3 vec, int len, DanmakuLaser type, DyeColor color);

	void shoot(SimplifiedProjectile danmaku);

	LivingEntity self();

	default DamageSource getDanmakuDamageSource(IDanmakuEntity danmaku) {
		return DanmakuDamageTypes.danmaku(danmaku);
	}

	@Nullable
	Vec3 targetVelocity();

}

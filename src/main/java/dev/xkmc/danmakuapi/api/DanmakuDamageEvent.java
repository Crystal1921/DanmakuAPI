package dev.xkmc.danmakuapi.api;

import dev.xkmc.danmakuapi.entity.danmaku.IYHDanmaku;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;

public class DanmakuDamageEvent extends Event {

	private final LivingEntity user;
	private final IYHDanmaku bullet;

	private DamageSource source;

	public DanmakuDamageEvent(LivingEntity user, DamageSource source, IYHDanmaku bullet) {
		this.user = user;
		this.source = source;
		this.bullet = bullet;
	}

	public LivingEntity getUser() {
		return user;
	}

	public IYHDanmaku getBullet() {
		return bullet;
	}

	public DamageSource getSource() {
		return source;
	}

	public void setSource(DamageSource source) {
		this.source = source;
	}

}

package dev.xkmc.danmakuapi.api;

import dev.xkmc.danmakuapi.init.data.DanmakuDamageTypes;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.fastprojectileapi.entity.SimplifiedProjectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;

public interface IDanmakuEntity {

	float damage(Entity target);

	default SimplifiedProjectile self() {
		return (SimplifiedProjectile) this;
	}

	default boolean shouldHurt(@Nullable Entity owner, Entity e) {
		if (owner == null) return false;
		if (owner instanceof IYoukaiEntity youkai) {
			if (e instanceof LivingEntity le) {
				return youkai.shouldHurt(le);
			}
			return false;
		}
		return true;
	}

	default void hurtTarget(EntityHitResult result) {
		if (self().level().isClientSide) return;
		var e = result.getEntity();
		if (e instanceof LivingEntity le) {
			if (le.hurtTime > 0) {
				DamageSource source = le.getLastDamageSource();
				if (source != null && source.getDirectEntity() instanceof IDanmakuEntity) {
					return;
				}
			}
		}
		DamageSource dmgType = DanmakuDamageTypes.danmaku(this);
		if (self().getOwner() instanceof CardHolder youkai) {
			dmgType = youkai.getDanmakuDamageSource(this);
		}
		if (self().getOwner() instanceof LivingEntity le) {
			var event = new DanmakuDamageEvent(le, dmgType, this);
			NeoForge.EVENT_BUS.post(event);
			dmgType = event.getSource();
		}
		if (!e.hurt(dmgType, damage(e))) return;
		LivingEntity target = null;
		while (e instanceof PartEntity<?> pe) {
			e = pe.getParent();
		}
		if (e instanceof LivingEntity le) target = le;
		if (target != null) {
			if (self().getOwner() instanceof IYoukaiEntity youkai) {
				youkai.onDanmakuHit(target, this);
			}
		}
	}

}

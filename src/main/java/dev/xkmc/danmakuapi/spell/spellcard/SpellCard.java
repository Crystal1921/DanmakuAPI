package dev.xkmc.danmakuapi.spell.spellcard;

import dev.xkmc.danmakuapi.entity.danmaku.DanmakuCommander;
import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

@SerialClass
public class SpellCard implements DanmakuCommander {

	public void tick(CardHolder holder) {
	}

	@Override
	public ProjectileMovement move(int code, int tickCount, Vec3 vec) {
		return ProjectileMovement.of(vec);
	}

	public void reset() {
	}

	public void hurt(CardHolder holder, DamageSource source, float amount) {
	}

}

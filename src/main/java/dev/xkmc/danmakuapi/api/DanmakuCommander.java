package dev.xkmc.danmakuapi.api;

import dev.xkmc.danmakuapi.init.data.DanmakuDamageTypes;
import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

public interface DanmakuCommander {

	ProjectileMovement move(int code, int tickCount, Vec3 vec);

	default DamageSource getDanmakuDamageSource(IDanmakuEntity danmaku){
		return DanmakuDamageTypes.danmaku(danmaku);
	}

}

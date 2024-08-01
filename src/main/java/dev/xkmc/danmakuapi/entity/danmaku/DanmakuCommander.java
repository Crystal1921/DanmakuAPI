package dev.xkmc.danmakuapi.entity.danmaku;

import dev.xkmc.danmakuapi.init.data.YHDamageTypes;
import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

public interface DanmakuCommander {

	ProjectileMovement move(int code, int tickCount, Vec3 vec);

	default DamageSource getDanmakuDamageSource(IYHDanmaku danmaku){
		return YHDamageTypes.danmaku(danmaku);
	}

}

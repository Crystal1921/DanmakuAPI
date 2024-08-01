package dev.xkmc.danmakuapi.content.spell.mover;

import dev.xkmc.danmakuapi.api.IDanmakuEntity;
import net.minecraft.world.phys.Vec3;

public record MoverInfo(int tick, Vec3 prevPos, Vec3 prevVel, IDanmakuEntity self) {

	public MoverInfo offsetTime(int i) {
		return new MoverInfo(tick + i, prevPos, prevVel, self);
	}

}

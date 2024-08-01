package dev.xkmc.danmakuapi.spell.mover;

import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import dev.xkmc.l2serial.serialization.marker.SerialClass;

@SerialClass
public abstract class DanmakuMover {

	public abstract ProjectileMovement move(MoverInfo info);

}

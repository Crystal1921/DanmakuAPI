package dev.xkmc.danmakuapi.content.spell.mover;

import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

@SerialClass
public class AttachedMover extends TargetPosMover {

	@Override
	public Vec3 pos(MoverInfo info) {
		var e = info.self();
		if (e.self().getOwner() instanceof CardHolder holder) {
			return holder.center();
		} else if (e.asTraceable().getOwner() instanceof Player player) {
			return player.position().add(0, player.getBbHeight() / 2, 0);
		}
		return info.prevPos();
	}

	@Override
	public ProjectileMovement move(MoverInfo info) {
		return new ProjectileMovement(pos(info).subtract(info.prevPos()), info.self().self().rot());
	}
}

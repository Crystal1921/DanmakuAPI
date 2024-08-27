package dev.xkmc.danmakuapi.presets.game.koishi;

import dev.xkmc.danmakuapi.content.entity.DanmakuHelper;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import dev.xkmc.danmakuapi.content.spell.mover.AttachedMover;
import dev.xkmc.danmakuapi.content.spell.spellcard.ActualSpellCard;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;

@SerialClass
public class Polygraph extends ActualSpellCard {

	@Override
	public void tick(CardHolder holder) {
		super.tick(holder);
		var o0 = DanmakuHelper.getOrientation(new Vec3(0, 0, 1));
		var forward = holder.forward();
		int n = 8;
		int step = 40;
		double angle = Math.PI * 2 / n / step;
		double ver = Mth.atan2(forward.y(), forward.horizontalDistance());
		Vec3 pos = holder.center();
		for (int i = 0; i < n; i++) {
			var laser = holder.prepareLaser(step, pos,
					o0.rotate(angle * tick + Math.PI * 2 / n * i, ver), 40, DanmakuItems.Laser.LASER,
					i % 2 == 0 ? DyeColor.RED : DyeColor.BLUE);
			laser.setupTime(0, 0, step, 0);
			laser.mover = new AttachedMover();
			holder.shoot(laser);
		}
	}

}

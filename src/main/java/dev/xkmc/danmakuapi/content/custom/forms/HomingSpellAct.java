package dev.xkmc.danmakuapi.content.custom.forms;

import dev.xkmc.danmakuapi.content.custom.data.HomingSpellFormData;
import dev.xkmc.danmakuapi.content.entity.DanmakuHelper;
import dev.xkmc.danmakuapi.content.spell.mover.CompositeMover;
import dev.xkmc.danmakuapi.content.spell.mover.RectMover;
import dev.xkmc.danmakuapi.content.spell.spellcard.ActualSpellCard;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.danmakuapi.content.spell.spellcard.Ticker;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.world.phys.Vec3;

@SerialClass
public class HomingSpellAct<C extends ActualSpellCard> extends ISpellAct<HomingSpellFormData, C> {

	@SerialField
	private HomingSpellFormData data;

	@SerialField
	private int step, tick;

	@Override
	public void init(HomingSpellFormData data) {
		super.init(data);
		this.data = data;
	}

	@Override
	public boolean tick(CardHolder holder, C card) {
		var o = DanmakuHelper.getOrientation(holder.forward());
		var form = data.form();
		int n = form.branches();
		var rand = holder.random();
		while (tick >= step * form.delay() && step < form.steps()) {
			for (int i = 0; i < n; i++) {
				double ax = (i - (n - 1) * 0.5) * form.branchAngle() +
						(step - (form.steps() - 1) * 0.5) * form.stepAngle();
				double ay = (step - (form.steps() - 1) * 0.5) * form.stepVerticalAngle();
				double rx = (rand.nextDouble() * 2 - 1) * form.randomizedAngle();
				double ry = (rand.nextDouble() * 2 - 1) * form.randomizedAngle();
				var dir = o.rotateDegrees(ax + rx, ay + ry);
				int time = data.turnTime();
				var e = data.base().prepare(holder, dir, data.speed(), time);
				e.mover = new RectMover(holder.center(),
						dir.scale(data.speed() * 2),
						dir.scale(-data.speed() * 2 / time));
				holder.shoot(e);
				addTicker(new Stage().setup(holder.center().add(dir.scale(data.speed() * time)), time));
			}
			step++;
		}
		tick++;
		return step >= form.steps() & super.tick(holder, card);
	}

	@SerialClass
	public static class Stage extends Ticker<HomingSpellAct<?>> {

		@SerialField
		private Vec3 pos = Vec3.ZERO;

		private Stage setup(Vec3 pos, int time) {
			this.pos = pos;
			this.tick -= time;
			return this;
		}

		@Override
		public boolean tick(CardHolder holder, HomingSpellAct<?> card) {
			super.tick(holder, card);
			if (tick <= 0) return false;
			var target = holder.target();
			if (target == null) return true;
			var data = card.data;
			var dir = target.subtract(pos).normalize();
			var e = data.base().prepare(holder, dir, data.speed());
			e.setPos(pos);
			var mover = new CompositeMover();
			int time = data.turnTime();
			mover.add(time, new RectMover(pos, Vec3.ZERO, dir.scale(data.speed() * 2 / time)));
			mover.add(20, new RectMover(pos.add(dir.scale(data.speed() * time)), dir.scale(data.speed()), Vec3.ZERO));
			e.mover = mover;
			holder.shoot(e);
			return true;
		}

	}

}

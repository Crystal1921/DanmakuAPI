package dev.xkmc.danmakuapi.spell.mover;

import dev.xkmc.fastprojectileapi.entity.ProjectileMovement;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;

import java.util.ArrayList;

@SerialClass
public class CompositeMover extends DanmakuMover {

	@SerialField
	private final ArrayList<Entry> list = new ArrayList<>();

	@SerialField
	private int total = 0, index = 0;

	public CompositeMover() {

	}

	public CompositeMover add(int duration, DanmakuMover mover) {
		list.add(new Entry(total, mover));
		total += duration;
		return this;
	}

	@Override
	public ProjectileMovement move(MoverInfo info) {
		if (index < list.size() - 1) {
			if (list.get(index + 1).subtract <= info.tick()) {
				index++;
			}
		}
		var ent = list.get(index);
		return ent.mover.move(info.offsetTime(-ent.subtract));
	}

	public record Entry(int subtract, DanmakuMover mover) {

	}

}

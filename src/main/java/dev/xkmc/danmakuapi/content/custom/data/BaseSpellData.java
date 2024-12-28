package dev.xkmc.danmakuapi.content.custom.data;

import dev.xkmc.danmakuapi.content.custom.annotation.ArgRange;
import dev.xkmc.danmakuapi.content.entity.ItemBulletEntity;
import dev.xkmc.danmakuapi.content.spell.spellcard.CardHolder;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

public record BaseSpellData(
		@ArgRange
		DanmakuItems.Bullet bullet,
		@ArgRange
		DyeColor color,
		@ArgRange(base = 4, factor = 32)
		double range,
		@ArgRange
		double randomizedRange
) {

	public static final BaseSpellData DEF = new BaseSpellData(
			DanmakuItems.Bullet.CIRCLE, DyeColor.RED, 80, 0.1
	);

	public Item getAmmoCost() {
		return bullet.get(color).get();
	}

	public ItemBulletEntity prepare(CardHolder holder, Vec3 dir, double speed) {
		double r = range * (1 + (holder.random().nextDouble() * 2 - 1) * randomizedRange);
		int time = Math.min(100, speed < 0.01 ? 80 : (int) Math.ceil(r / speed));
		return prepare(holder, dir, speed, time);
	}

	public ItemBulletEntity prepare(CardHolder holder, Vec3 dir, double speed, int time) {
		var ans = holder.prepareDanmaku(time, dir.scale(speed), bullet, color);
		ans.setPos(holder.center());
		return ans;
	}

}

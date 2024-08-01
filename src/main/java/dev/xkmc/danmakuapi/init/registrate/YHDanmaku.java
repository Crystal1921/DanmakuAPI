package dev.xkmc.danmakuapi.init.registrate;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.danmakuapi.api.DanmakuBullet;
import dev.xkmc.danmakuapi.api.DanmakuLaser;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.danmakuapi.init.data.YHTagGen;
import dev.xkmc.danmakuapi.item.danmaku.DanmakuItem;
import dev.xkmc.danmakuapi.item.danmaku.LaserItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Locale;

public class YHDanmaku {

	public enum Bullet implements DanmakuBullet {
		CIRCLE(1, 4), BALL(1, 4),
		MENTOS(2, 6), BUBBLE(4, 8),
		BUTTERFLY(1, 4),
		;

		public final String name;
		public final TagKey<Item> tag;
		public final float size;
		private final int damage;

		Bullet(float size, int damage) {
			this.size = size;
			this.damage = damage;
			name = name().toLowerCase(Locale.ROOT);
			tag = YHTagGen.item(name + "_danmaku");
		}

		public ItemEntry<DanmakuItem> get(DyeColor color) {
			return YHDanmaku.DANMAKU[ordinal()][color.ordinal()];
		}

		public int damage() {
			return damage;
		}

		public boolean bypass() {
			return size > 1;
		}

		@Override
		public String getName() {
			return name;
		}


	}

	public enum Laser implements DanmakuLaser {
		LASER(1, 4);

		public final String name;
		public final TagKey<Item> tag;
		public final float size;
		private final int damage;

		Laser(float size, int damage) {
			this.size = size;
			this.damage = damage;
			name = name().toLowerCase(Locale.ROOT);
			tag = YHTagGen.item(name);
		}

		public ItemEntry<LaserItem> get(DyeColor color) {
			return YHDanmaku.LASER[ordinal()][color.ordinal()];
		}

		public int damage() {
			return damage;
		}

	}

	private static final ItemEntry<DanmakuItem>[][] DANMAKU;

	private static final ItemEntry<LaserItem>[][] LASER;

	static {
		DANMAKU = new ItemEntry[Bullet.values().length][DyeColor.values().length];
		for (var t : Bullet.values()) {
			for (var e : DyeColor.values()) {
				var ent = DanmakuAPI.REGISTRATE
						.item(e.getName() + "_" + t.name + "_danmaku", p -> new DanmakuItem(p.rarity(Rarity.RARE), t, e, t.size))
						.model((ctx, pvd) -> pvd.generated(ctx,
								pvd.modLoc("item/danmaku/" + t.name),
								pvd.modLoc("item/danmaku/" + t.name + "_overlay")))
						.color(() -> () -> (stack, i) -> ((DanmakuItem) stack.getItem()).getDanmakuColor(stack, i))
						.tag(t.tag)
						.register();
				DANMAKU[t.ordinal()][e.ordinal()] = ent;
			}
		}

		LASER = new ItemEntry[Laser.values().length][DyeColor.values().length];
		for (var t : Laser.values()) {
			for (var e : DyeColor.values()) {
				var ent = DanmakuAPI.REGISTRATE
						.item(e.getName() + "_" + t.name, p -> new LaserItem(p.rarity(Rarity.RARE), t, e, 1))
						.model((ctx, pvd) -> pvd.generated(ctx,
								pvd.modLoc("item/danmaku/" + t.name),
								pvd.modLoc("item/danmaku/" + t.name + "_overlay")))
						.color(() -> () -> (stack, i) -> ((LaserItem) stack.getItem()).getDanmakuColor(stack, i))
						.tag(t.tag)
						.register();
				LASER[t.ordinal()][e.ordinal()] = ent;
			}
		}
	}

	public static void register() {
	}

}

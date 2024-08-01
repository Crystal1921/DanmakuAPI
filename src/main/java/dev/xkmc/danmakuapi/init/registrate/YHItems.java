package dev.xkmc.danmakuapi.init.registrate;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.danmakuapi.item.danmaku.SpellItem;
import dev.xkmc.danmakuapi.spell.game.reimu.ReimuSpell;
import net.minecraft.world.item.DyeColor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class YHItems {

	private static final Set<String> SMALL_WORDS = Set.of("of", "the", "with", "in");

	public static String toEnglishName(String internalName) {
		return Arrays.stream(internalName.split("_"))
				.map(e -> SMALL_WORDS.contains(e) ? e : StringUtils.capitalize(e))
				.collect(Collectors.joining(" "));
	}

	public static final ItemEntry<SpellItem> REIMU_SPELL;

	static {

		// gears
		{
			REIMU_SPELL = DanmakuAPI.REGISTRATE
					.item("spell_reimu", p -> new SpellItem(
							p.stacksTo(1), ReimuSpell::new, true,
							() -> YHDanmaku.Bullet.CIRCLE.get(DyeColor.RED).get()))
					.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/curio/" + ctx.getName())))
					.lang("Reimu's Spellcard \"Innate Dream\"")
					.register();
		}

	}

	public static void register() {
	}

}

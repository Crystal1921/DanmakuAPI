package dev.xkmc.danmakuapi.init.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import javax.annotation.Nullable;
import java.util.Locale;

public enum DanmakuLang {
	DANMAKU_DAMAGE("tooltip.danmaku_damage", "Deals %s damage on hit", 1, ChatFormatting.BLUE),
	DANMAKU_BYPASS("tooltip.danmaku_bypass", "Bypasses entities", 0, ChatFormatting.DARK_AQUA),
	SPELL_TARGET("tooltip.spell_target", "Requires targeting an entity to activate", 0, ChatFormatting.RED),
	SPELL_COST("tooltip.spell_cost", "Costs 1 %s", 1, ChatFormatting.GRAY),
	;

	private final String key, def;
	private final int arg;
	private final ChatFormatting format;


	DanmakuLang(String key, String def, int arg, @Nullable ChatFormatting format) {
		this.key = DanmakuAPI.MODID + "." + key;
		this.def = def;
		this.arg = arg;
		this.format = format;
	}

	public static String asId(String name) {
		return name.toLowerCase(Locale.ROOT);
	}

	public MutableComponent get(Object... args) {
		if (args.length != arg)
			throw new IllegalArgumentException("for " + name() + ": expect " + arg + " parameters, got " + args.length);
		MutableComponent ans = Component.translatable(key, args);
		if (format != null) {
			return ans.withStyle(format);
		}
		return ans;
	}

	public String key() {
		return key;
	}

	public static void genLang(RegistrateLangProvider pvd) {
		for (DanmakuLang lang : DanmakuLang.values()) {
			pvd.add(lang.key, lang.def);
		}
		pvd.add("death.attack.danmaku", "%s lost the danmaku battle");
		pvd.add("death.attack.danmaku.player", "%s lost the danmaku battle to %s");
		pvd.add("death.attack.abyssal_danmaku", "%s lost the danmaku battle");
		pvd.add("death.attack.abyssal_danmaku.player", "%s lost the danmaku battle to %s");


	}


}

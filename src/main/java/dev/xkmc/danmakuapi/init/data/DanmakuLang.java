package dev.xkmc.danmakuapi.init.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.DyeColor;

import javax.annotation.Nullable;
import java.util.Locale;

public enum DanmakuLang {
	DANMAKU_DAMAGE("tooltip.danmaku_damage", "Deals %s damage on hit", 1, ChatFormatting.BLUE),
	DANMAKU_BYPASS("tooltip.danmaku_bypass", "Bypasses entities", 0, ChatFormatting.DARK_AQUA),
	SPELL_TARGET("tooltip.spell_target", "Requires targeting an entity to activate", 0, ChatFormatting.RED),
	SPELL_COST("tooltip.spell_cost", "Costs %s %s", 2, ChatFormatting.GRAY),

	EDITOR_RESET("custom_spell.reset", "Reset", 0, null),
	INVALID_TIME("custom_spell.invalid_time", "Max duration of %s allowed. Current duration: %s", 2, ChatFormatting.RED);

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

		pvd.add(DanmakuAPI.MODID + ".custom_spell.bullet.title", "Bullet Type");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.color.title", "Bullet Color");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.branches", "Branch Count");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.branches.desc", "Number of branches of bullets to shoot");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.steps", "Step Count");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.steps.desc", "Number of bullets per branch");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.delay", "Step Delay");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.delay.desc", "Delay in ticks per step");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.range", "Bullet Range");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.range.desc", "Distance for bullet to fly before vanishing");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.randomizedRange", "Range Variation");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.randomizedRange.desc", "Variation of bullet range in percentage, plus or minus");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.branchAngle", "Branch Angle Offset");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.branchAngle.desc", "Horizontal angle difference between adjacent branches, in degree");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.stepAngle", "Step Angle Offset");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.stepAngle.desc", "Horizontal angle difference between adjacent steps, in degree");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.stepVerticalAngle", "Step Vertical Offset");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.stepVerticalAngle.desc", "Vertical angle difference between adjacent steps, in degree");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.randomizedAngle", "Angle Variation");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.randomizedAngle.desc", "Variation of bullet direction in degree, both horizontal and vertical, plus or minus");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.speed", "Bullet Speed");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.speed.desc", "Bullet speed in block per tick");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.speedFirst", "First Step Speed");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.speedFirst.desc", "Bullet speed in block per tick for first step");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.speedLast", "Last Step Speed");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.speedLast.desc", "Bullet speed in block per tick for last step");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.randomizedSpeed", "Speed Variation");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.randomizedSpeed.desc", "Variation of bullet speed in percentage, plus or minus");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.turnTime", "Turn Time");
		pvd.add(DanmakuAPI.MODID + ".custom_spell.turnTime.desc", "Time in tick after which bullet will redirect toward target");


		for (var e : DanmakuItems.Bullet.values()) {
			var name = e.name().toLowerCase(Locale.ROOT);
			pvd.add(DanmakuAPI.MODID + ".custom_spell.bullet." + name,
					RegistrateLangProvider.toEnglishName(name));
		}

		for (var e : DyeColor.values()) {
			var name = e.getName();
			pvd.add(DanmakuAPI.MODID + ".custom_spell.color." + name,
					RegistrateLangProvider.toEnglishName(name));
		}


	}


}

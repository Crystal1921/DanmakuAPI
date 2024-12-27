package dev.xkmc.danmakuapi.content.render;

import java.util.Locale;

public enum DisplayType {
	SOLID, TRANSPARENT, ADDITIVE;

	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}
}

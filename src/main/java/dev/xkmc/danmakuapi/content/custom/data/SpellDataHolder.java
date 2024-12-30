package dev.xkmc.danmakuapi.content.custom.data;

import dev.xkmc.l2serial.util.Wrappers;

public record SpellDataHolder(Record data) {

	public ISpellFormData<?> cast() {
		return Wrappers.cast(data);
	}

}

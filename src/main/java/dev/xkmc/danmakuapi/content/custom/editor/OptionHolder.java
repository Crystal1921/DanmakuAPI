package dev.xkmc.danmakuapi.content.custom.editor;

import net.minecraft.client.OptionInstance;

public interface OptionHolder<T> extends ValueProvider<T> {

	OptionInstance<T> option();

	T get();

	void reset();

}

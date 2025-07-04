package dev.xkmc.danmakuapi.content.custom.editor;

import dev.xkmc.danmakuapi.content.custom.annotation.ArgGroup;

import java.util.ArrayList;
import java.util.List;

public record OptionGroup<T>(ArgGroup group, List<OptionPair> pairs) implements ValueProvider<T> {

	@Override
	public T get() throws Exception {
		List<Object> args = new ArrayList<>();
		for (var e : pairs) {
			args.add(e.option().get());
		}
		return group.make(args);
	}

}

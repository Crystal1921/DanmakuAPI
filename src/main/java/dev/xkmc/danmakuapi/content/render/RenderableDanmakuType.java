package dev.xkmc.danmakuapi.content.render;

import dev.xkmc.fastprojectileapi.render.RenderableProjectileType;

public interface RenderableDanmakuType<T extends RenderableDanmakuType<T, I>, I> extends RenderableProjectileType<T, I> {

	@Override
	default int order() {
		return display().ordinal();
	}

	DisplayType display();

}
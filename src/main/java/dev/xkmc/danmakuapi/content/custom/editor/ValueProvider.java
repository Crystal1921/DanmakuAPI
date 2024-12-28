package dev.xkmc.danmakuapi.content.custom.editor;

public interface ValueProvider<T> {

	T get() throws Exception;

}

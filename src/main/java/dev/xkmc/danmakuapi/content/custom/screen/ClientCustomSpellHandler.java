package dev.xkmc.danmakuapi.content.custom.screen;

import dev.xkmc.danmakuapi.content.custom.data.ISpellFormData;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import net.minecraft.client.Minecraft;

public class ClientCustomSpellHandler {

	public static void open(ISpellFormData<?> data) {
		Minecraft.getInstance().setScreen(new EditorScreen(data));
	}

	public static void sendToPlayer(ISpellFormData<?> val) {
		DanmakuAPI.HANDLER.toServer(new SpellSetToServer(val.cast()));

	}

}

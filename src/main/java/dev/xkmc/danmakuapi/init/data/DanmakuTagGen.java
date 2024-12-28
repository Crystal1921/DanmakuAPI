package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DanmakuTagGen {

	public static final TagKey<Item> CUSTOM_SPELL = item("custom_spell");
	public static final TagKey<Item> PRESET_SPELL = item("preset_spell");

	public static TagKey<Item> item(String id) {
		return ItemTags.create(DanmakuAPI.loc(id));
	}

}

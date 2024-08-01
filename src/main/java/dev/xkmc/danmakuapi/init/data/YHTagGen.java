package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class YHTagGen {

	public static TagKey<Item> item(String id) {
		return ItemTags.create(DanmakuAPI.loc(id));
	}

}

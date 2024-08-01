package dev.xkmc.danmakuapi.api;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.xkmc.danmakuapi.content.item.DanmakuItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

public interface DanmakuBullet {
	int damage();

	boolean bypass();

	String getName();

	ItemProviderEntry<Item, ? extends DanmakuItem> get(DyeColor color);

}

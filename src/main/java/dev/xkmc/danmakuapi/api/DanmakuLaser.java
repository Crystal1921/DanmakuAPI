package dev.xkmc.danmakuapi.api;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import dev.xkmc.danmakuapi.item.danmaku.LaserItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

public interface DanmakuLaser {

	int damage();

	ItemProviderEntry<Item, ? extends LaserItem> get(DyeColor color);

}

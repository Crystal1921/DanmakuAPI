package dev.xkmc.danmakuapi.init.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.function.BiFunction;

public class DanmakuRecipeGen {

	public static void genRecipes(RegistrateRecipeProvider pvd) {

		// danmaku
		{
			for (var e : DyeColor.values()) {
				Item dye = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(e.getName() + "_dye"));
				for (var t : DanmakuItems.Bullet.values()) {
					var danmaku = t.get(e).get();
					unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, danmaku, 8)::unlockedBy, danmaku)
							.pattern("AAA").pattern("ABA").pattern("AAA")
							.define('A', t.tag)
							.define('B', dye)
							.save(pvd);

				}
				for (var t : DanmakuItems.Laser.values()) {
					var danmaku = t.get(e).get();
					unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, danmaku, 8)::unlockedBy, danmaku)
							.pattern("AAA").pattern("ABA").pattern("AAA")
							.define('A', t.tag)
							.define('B', dye)
							.save(pvd);
				}
			}
		}

	}

	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, Criterion<InventoryChangeTrigger.TriggerInstance>, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCriterion(pvd));
	}

}

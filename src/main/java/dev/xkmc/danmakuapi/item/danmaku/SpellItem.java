package dev.xkmc.danmakuapi.item.danmaku;

import dev.xkmc.danmakuapi.init.data.YHLangData;
import dev.xkmc.danmakuapi.init.data.YHModConfig;
import dev.xkmc.danmakuapi.spell.item.ItemSpell;
import dev.xkmc.danmakuapi.spell.item.SpellContainer;
import dev.xkmc.l2library.content.raytrace.IGlowingTarget;
import dev.xkmc.l2library.content.raytrace.RayTraceUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SpellItem extends ProjectileWeaponItem implements IGlowingTarget {

	private final Supplier<ItemSpell> spell;
	private final boolean requireTarget;
	private final Supplier<Item> pred;

	public SpellItem(Properties prop, Supplier<ItemSpell> spell, boolean requireTarget, Supplier<Item> pred) {
		super(prop);
		this.spell = spell;
		this.requireTarget = requireTarget;
		this.pred = pred;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		ItemStack ammo = player.getProjectile(stack);
		boolean canUse = !ammo.isEmpty();
		LivingEntity target = RayTraceUtil.serverGetTarget(player);
		if (target == null && requireTarget)
			return InteractionResultHolder.fail(stack);
		if (!player.getAbilities().instabuild && !canUse)
			return InteractionResultHolder.fail(stack);
		if (player instanceof ServerPlayer sp) {
			if (!player.getAbilities().instabuild)
				ammo.shrink(1);
			SpellContainer.castSpell(sp, spell, target);
			int cooldown = YHModConfig.SERVER.playerSpellCooldown.get();
			sp.getCooldowns().addCooldown(this, cooldown);
		}
		return InteractionResultHolder.consume(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> list, TooltipFlag flag) {
		list.add(YHLangData.SPELL_COST.get(pred.get().getName(pred.get().getDefaultInstance())));
		if (requireTarget) {
			list.add(YHLangData.SPELL_TARGET.get());
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity user, int slot, boolean sel) {
		if (user instanceof Player player && level.isClientSide && sel) {
			RayTraceUtil.clientUpdateTarget(player, 64);
		}
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return e -> e.is(pred.get());
	}

	@Override
	public int getDefaultProjectileRange() {
		return 40;
	}

	@Override
	protected void shootProjectile(LivingEntity le, Projectile projectile, int i, float v, float v1, float v2, @Nullable LivingEntity target) {

	}

	@Override
	public int getDistance(ItemStack itemStack) {
		return 64;
	}

}

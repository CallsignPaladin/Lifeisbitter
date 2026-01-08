package paladin.lifeisbitter.item.tools;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.item.MaterialItems;

import java.util.List;

public class ModToolTiers {
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 800, 7,-1.0f,17, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(MaterialItems.STEEL_INGOT.get())),
            ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "steel"), List.of(Tiers.IRON), List.of()
    );
    public static final Tier NEWIRON = TierSortingRegistry.registerTier(
            new ForgeTier(2, 180, 5,-1.0f,12, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Items.IRON_INGOT)),
            ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "newiron"), List.of(Tiers.IRON), List.of()
    );
}

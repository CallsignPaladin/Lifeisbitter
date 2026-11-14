package paladin.lifeisbitter.item.tools;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.SimpleTier;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.item.AllBasicItems;

import java.util.List;

public class ModToolTiers {
    public static final Tier STEEL = new SimpleTier(
            Tags.Blocks.NEEDS_GOLD_TOOL, 800, 7f, 2f, 17, //TO DO: ZVEDNOUT Atck dmg o float
            () -> Ingredient.of(AllBasicItems.STEEL_INGOT.get())
    );
    public static final Tier NEWIRON = new SimpleTier(
            Tags.Blocks.NEEDS_GOLD_TOOL, 800, 5f, 2f, 17,
            () -> Ingredient.of(AllBasicItems.STEEL_INGOT.get())
    );

    /*
    public static final Tier NEWIRON = TierSortingRegistry.registerTier(
            new ForgeTier(2, 180, 5,-1.0f,12, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Items.IRON_INGOT)),
            ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "newiron"), List.of(Tiers.IRON), List.of()
    );
    */

    /*
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 800, 7,-1.0f,17, Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(AllBasicItems.STEEL_INGOT.get())),
            ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "steel"), List.of(Tiers.IRON), List.of()
    );*/

}

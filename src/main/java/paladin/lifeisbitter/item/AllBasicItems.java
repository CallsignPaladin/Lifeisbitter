package paladin.lifeisbitter.item;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockItem;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import paladin.lifeisbitter.CreativeTabs;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.blocks.burners.CopperBurnerBlockItem;
import paladin.lifeisbitter.blocks.fluid.ModFluids;

import java.util.function.Supplier;

import static paladin.lifeisbitter.Lifeisbitter.REGISTRATE;

public class AllBasicItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Lifeisbitter.MODID);

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STEEL_SHEET = ITEMS.register("steel_sheet",
            () -> new Item(new Item.Properties()));

    public static final Supplier<BlockItem> STEEL_BLOCK_ITEM = ITEMS.register("steel_block",
            () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));

/*
    public static final Supplier<Item> COPPER_BURNER_ITEM = ITEMS.register("copper_burner",
            () -> new BlockItem(ModBlocks.COPPER_BURNER.get(), new Item.Properties()));

 */

    /*
    public static final DeferredItem<BlockItem> COPPER_BURNER_ITEM =
            ITEMS.register(
                    "copper_burner",
                    () -> new CopperBurnerBlockItem(
                            ModBlocks.COPPER_BURNER.get(),
                            new Item.Properties()
                    )
            );

     */



    //public static ItemEntry<Item> COPPER_BURNER_ITEM;

    /*
    public static final Supplier<Item> TALL_BLAST_FURNACE_ITEM = ITEMS.register("tall_blast_furnace",
            () -> new BlockItem(ModBlocks.TALL_BLAST_FURNACE.get(), new Item.Properties()));
            TO DO low prio
     */

    public static final DeferredItem<Item> POLISHED_DIAMOND = ITEMS.register("polished_diamond",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SequencedAssemblyItem> INCOMPLETE_DIAMONDSTEEL_SHEET = ITEMS.register("incomplete_diamondsteel_sheet",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final DeferredItem<Item> DIAMONDSTEEL_SHEET = ITEMS.register("diamondsteel_sheet",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SULFUR_DUST = ITEMS.register("sulfur_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NITRE = ITEMS.register("nitre",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOLID_FUEL = ITEMS.register("solid_fuel",
            () -> new Item(new Item.Properties()));

    /*
    public static final DeferredItem<Item> COKE = ITEMS.register("coke",
            () -> new Item(new Item.Properties()));

     */

    public static final DeferredItem<Item> LIMESTONE_DUST = ITEMS.register("limestone_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> USED_FILTER = ITEMS.register("used_filter",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> INFESTED_PULP = ITEMS.register("infested_pulp",
            () -> new Item(new Item.Properties()));

    public static ItemEntry<Item> COKE;


    public static final ItemEntry<CopperBurnerBlockItem> COPPER_BURNER;



    static {
        COKE = REGISTRATE.item("coke", Item::new).register();

        COPPER_BURNER = REGISTRATE.item("copper_burner", (properties) -> new CopperBurnerBlockItem(ModBlocks.COPPER_BURNER.get(), properties))
                //.model(AssetLookup.customBlockItemModel("blaze_burner", "block"))
                .register();
    }



    public static final DeferredItem<BucketItem> URINE_BUCKET = ITEMS.register("urine_bucket", ()-> new BucketItem(ModFluids.SOURCE_URINE.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredItem<BucketItem> ROCKET_FUEL_BUCKET = ITEMS.register("rocket_fuel_bucket", ()-> new BucketItem(ModFluids.SOURCE_ROCKET_FUEL.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
/*

    // FLUID STORAGE
    public static ItemEntry<BucketItem> URINE_BUCKET = Lifeisbitter.REGISTRATE.item("urine_bucket",
                    properties -> new BucketItem(
                            ModFluids.SOURCE_URINE,
                            properties.stacksTo(1)
                                    .craftRemainder(Items.BUCKET)
                    ))
            .tab(CreativeTabs.MAIN_TAB.getKey())
            .register();

    /*
    public static final RegistryObject<Item> URINE_BUCKET = ITEMS.register("urine_bucket",
            () -> new BucketItem(ModFluids.SOURCE_URINE, (new Item.Properties()).stacksTo(1)));


    public static final Supplier<Item> ROCKET_FUEL_BUCKET = ITEMS.register("rocket_fuel_bucket",
            () -> new BucketItem(ModFluids.SOURCE_ROCKET_FUEL, (new Item.Properties()).stacksTo(1)));
    */

}

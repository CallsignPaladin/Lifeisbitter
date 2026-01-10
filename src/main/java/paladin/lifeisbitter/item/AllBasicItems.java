package paladin.lifeisbitter.item;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.CreativeTabs;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.item.fluid.ModFluids;

public class AllBasicItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Lifeisbitter.MODID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SHEET = ITEMS.register("steel_sheet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_BLOCK_ITEM = ITEMS.register("steel_block",
            () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> COPPER_BURNER_ITEM = ITEMS.register("copper_burner",
            () -> new BlockItem(ModBlocks.COPPER_BURNER.get(), new Item.Properties()));

    public static final RegistryObject<Item> TALL_BLAST_FURNACE_ITEM = ITEMS.register("tall_blast_furnace",
            () -> new BlockItem(ModBlocks.TALL_BLAST_FURNACE.get(), new Item.Properties()));

    public static final RegistryObject<Item> POLISHED_DIAMOND = ITEMS.register("polished_diamond",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<SequencedAssemblyItem> INCOMPLETE_DIAMONDSTEEL_SHEET = ITEMS.register("incomplete_diamondsteel_sheet",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final RegistryObject<Item> DIAMONDSTEEL_SHEET = ITEMS.register("diamondsteel_sheet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SULFUR_DUST = ITEMS.register("sulfur_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NITRE = ITEMS.register("nitre",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL = ITEMS.register("solid_fuel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COKE = ITEMS.register("coke",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIMESTONE_DUST = ITEMS.register("limestone_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> USED_FILTER = ITEMS.register("used_filter",
            () -> new Item(new Item.Properties()));


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

     */
    public static final RegistryObject<Item> ROCKET_FUEL_BUCKET = ITEMS.register("rocket_fuel_bucket",
            () -> new BucketItem(ModFluids.SOURCE_ROCKET_FUEL, (new Item.Properties()).stacksTo(1)));

}

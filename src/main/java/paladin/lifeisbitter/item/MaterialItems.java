package paladin.lifeisbitter.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.item.fluid.ModFluids;

public class MaterialItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Lifeisbitter.MODID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SHEET = ITEMS.register("steel_sheet",
            () -> new Item(new Item.Properties()));



    public static final RegistryObject<Item> STEEL_BLOCK_ITEM = ITEMS.register("steel_block",
            () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> COPPER_BURNER_ITEM = ITEMS.register("copper_burner",
            () -> new BlockItem(ModBlocks.COPPER_BURNER.get(), new Item.Properties()));

    public static final RegistryObject<Item> POLISHED_DIAMOND = ITEMS.register("polished_diamond",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMONDSTEEL_SHEET = ITEMS.register("diamondsteel_sheet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SULFUR_DUST = ITEMS.register("sulfur_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NITRE = ITEMS.register("nitre",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL = ITEMS.register("solid_fuel",
            () -> new Item(new Item.Properties()));
}

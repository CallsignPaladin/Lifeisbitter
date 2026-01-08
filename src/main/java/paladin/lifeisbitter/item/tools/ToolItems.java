package paladin.lifeisbitter.item.tools;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.processing.TallBlastFurnaceBlock;
import paladin.lifeisbitter.item.fluid.BaseFluidType;
import paladin.lifeisbitter.item.fluid.ModFluids;

public class ToolItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Lifeisbitter.MODID);
    public static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    // STEEl
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, 4, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, 6, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, 9, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, 4.5f, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, 4, 0.0f, new Item.Properties()));

    // IRON OVERRIDE
    public static final RegistryObject<Item> IRON_PICKAXE = VANILLA_ITEMS.register("iron_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NEWIRON, 4, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SWORD = VANILLA_ITEMS.register("iron_sword",
            () -> new SwordItem(ModToolTiers.NEWIRON, 6, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_AXE = VANILLA_ITEMS.register("iron_axe",
            () -> new AxeItem(ModToolTiers.NEWIRON, 9, -3.1f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SHOVEL = VANILLA_ITEMS.register("iron_shovel",
            () -> new ShovelItem(ModToolTiers.NEWIRON, 4.5f, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HOE = VANILLA_ITEMS.register("iron_hoe",
            () -> new HoeItem(ModToolTiers.NEWIRON, 4, -1.0f, new Item.Properties()));

    public static final RegistryObject<Item> USED_FILTER = ITEMS.register("used_filter",
            () -> new Item(new Item.Properties()));

    // FLUID STORAGE
    public static ItemEntry<BucketItem> URINE_BUCKET = Lifeisbitter.REGISTRATE.item("urine_bucket",
            properties -> new BucketItem(
                    ModFluids.SOURCE_URINE,
                    properties.stacksTo(1)
                            .craftRemainder(Items.BUCKET)
            )).register();

    /*
    public static final RegistryObject<Item> URINE_BUCKET = ITEMS.register("urine_bucket",
            () -> new BucketItem(ModFluids.SOURCE_URINE, (new Item.Properties()).stacksTo(1)));

     */
    public static final RegistryObject<Item> ROCKET_FUEL_BUCKET = ITEMS.register("rocket_fuel_bucket",
            () -> new BucketItem(ModFluids.SOURCE_ROCKET_FUEL, (new Item.Properties()).stacksTo(1)));

}

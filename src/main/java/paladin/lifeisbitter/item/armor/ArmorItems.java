package paladin.lifeisbitter.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import paladin.lifeisbitter.Lifeisbitter;

import java.util.function.Supplier;

import static paladin.lifeisbitter.item.armor.ArmorMaterials.*;

public class ArmorItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Lifeisbitter.MODID);
    //public static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.createItems("minecraft");

    // STEEL
    public static final Supplier<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet", () -> new ArmorItem(
            STEEL,
            ArmorItem.Type.HELMET,
            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15)) //TO DO - zkontrolovat hodnoty
    ));
    public static final Supplier<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", () -> new ArmorItem(
            STEEL,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))
    ));
    public static final Supplier<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings", () -> new ArmorItem(
            STEEL,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))
    ));
    public static final Supplier<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots", () -> new ArmorItem(
            STEEL,
            ArmorItem.Type.BOOTS,
            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(17))
    ));

    // IRON
/*

    public static final Supplier<ArmorItem> IRON_HELMET = ITEMS.register("iron_helmet", () -> new ArmorItem(
            IRON,
            ArmorItem.Type.HELMET,
            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
    ));
    public static final Supplier<ArmorItem> IRON_CHESTPLATE = ITEMS.register("iron_chestplate", () -> new ArmorItem(
            IRON,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
    ));
    public static final Supplier<ArmorItem> IRON_LEGGINGS = ITEMS.register("iron_leggings", () -> new ArmorItem(
            IRON,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
    ));
    public static final Supplier<ArmorItem> IRON_BOOTS = ITEMS.register("iron_boots", () -> new ArmorItem(
            IRON,
            ArmorItem.Type.BOOTS,
            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
    ));

 */
    /*

    OLD FORGE CODE

    // STEEL
    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // IRON OVERRIDE
    public static final RegistryObject<Item> IRON_HELMET = VANILLA_ITEMS.register("iron_helmet",
            () -> new ArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> IRON_CHESTPLATE = VANILLA_ITEMS.register("iron_chestplate",
            () -> new ArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> IRON_LEGGINGS = VANILLA_ITEMS.register("iron_leggings",
            () -> new ArmorItem(ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> IRON_BOOTS = VANILLA_ITEMS.register("iron_boots",
            () -> new ArmorItem(ArmorMaterials.IRON, ArmorItem.Type.BOOTS, new Item.Properties()));
     */
}

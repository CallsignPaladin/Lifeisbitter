package paladin.lifeisbitter.item.tools;

import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import static paladin.lifeisbitter.Lifeisbitter.MODID;

public class ToolItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.Items VANILLA_ITEMS = DeferredRegister.createItems("minecraftt");

    // STEEl
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    1,-2.8f
                            ))));
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    3,-2.4f
                            ))));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    6,-3.1f
                            ))));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    1.5f,-3.0f
                            ))));
                    //4.5f, -3.0f, new Item.Properties()));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    1,0.0f
                            ))));
/*
    // IRON OVERRIDE
    public static final DeferredItem<Item> IRON_PICKAXE = VANILLA_ITEMS.register("iron_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NEWIRON,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    4,-2.8f
                            ))));
/*

    public static final DeferredItem<Item> IRON_SWORD = VANILLA_ITEMS.register("iron_sword",
            () -> new SwordItem(ModToolTiers.NEWIRON,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    6,-2.4f
                            ))));
    public static final DeferredItem<Item> IRON_AXE = VANILLA_ITEMS.register("iron_axe",
            () -> new AxeItem(ModToolTiers.NEWIRON,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    9,-3.1f
                            ))));
    public static final DeferredItem<Item> IRON_SHOVEL = VANILLA_ITEMS.register("iron_shovel",
            () -> new ShovelItem(ModToolTiers.NEWIRON,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    4.5f,-3.0f
                            ))));
    public static final DeferredItem<Item> IRON_HOE = VANILLA_ITEMS.register("iron_hoe",
            () -> new HoeItem(ModToolTiers.NEWIRON,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolTiers.STEEL,
                                    4,-1.0f
                            ))));
*/

}



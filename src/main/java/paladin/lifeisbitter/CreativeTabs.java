package paladin.lifeisbitter;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import paladin.lifeisbitter.item.AllBasicItems;

import java.util.function.Supplier;

import static net.minecraft.world.item.Items.*;
import static paladin.lifeisbitter.item.AllBasicItems.*;
import static paladin.lifeisbitter.item.armor.ArmorItems.*;
import static paladin.lifeisbitter.item.tools.ToolItems.*;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lifeisbitter.MODID);

    private static BuildCreativeModeTabContentsEvent event;

    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack((AllBasicItems.POLISHED_DIAMOND.get())))
                    .title(Component.translatable("creativetab.main_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(STEEL_HELMET.get());
                        output.accept(STEEL_CHESTPLATE.get());
                        output.accept(STEEL_LEGGINGS.get());
                        output.accept(STEEL_BOOTS.get());
                        output.accept(STEEL_SWORD.get());
                        output.accept(STEEL_PICKAXE.get());
                        output.accept(STEEL_AXE.get());
                        output.accept(STEEL_SHOVEL.get());
                        output.accept(STEEL_HOE.get());
                        output.accept(STEEL_INGOT.get());
                        output.accept(STEEL_SHEET.get());
                        output.accept(STEEL_BLOCK_ITEM.get());
                        output.accept(POLISHED_DIAMOND.get());
                        output.accept(DIAMOND_DUST.get());
                        output.accept(DIAMONDSTEEL_SHEET.get());
                        output.accept(SULFUR_DUST.get());
                        output.accept(NITRE.get());
                        output.accept(LIMESTONE_DUST.get());
                        output.accept(SOLID_FUEL.get());
                        output.accept(URINE_BUCKET);
                        output.accept(ROCKET_FUEL_BUCKET);
                        output.accept(COPPER_BURNER.get());
                        //output.accept(TALL_BLAST_FURNACE_ITEM.get()); TO DO low prio
                        output.accept(USED_FILTER.get());
                        output.accept(COKE.get());
                        output.accept(INFESTED_PULP.get());
                    })
                    .build()
            );

    public static void AddCreative(BuildCreativeModeTabContentsEvent event) {
        CreativeTabs.event = event;
        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            PutAfter(STEEL_HELMET.get(), Items.IRON_BOOTS);
            PutAfter(STEEL_CHESTPLATE.get(), STEEL_HELMET.get());
            PutAfter(STEEL_LEGGINGS.get(), STEEL_CHESTPLATE.get());
            PutAfter(STEEL_BOOTS.get(), STEEL_LEGGINGS.get());
            PutAfter(STEEL_AXE, Items.IRON_AXE);
            PutAfter(STEEL_SWORD, Items.IRON_SWORD);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            PutAfter(STEEL_BLOCK_ITEM.get(), Items.IRON_BLOCK);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            PutAfter(POLISHED_DIAMOND, DIAMOND);
            PutAfter(STEEL_INGOT, Items.IRON_INGOT);
            PutAfter(DIAMONDSTEEL_SHEET, STEEL_INGOT);
            PutAfter(SULFUR_DUST, GUNPOWDER);
            PutAfter(NITRE, SULFUR_DUST);
            PutAfter(DIAMOND_DUST, NITRE);
            PutAfter(SOLID_FUEL, DIAMOND_DUST);
            PutAfter(COKE, CHARCOAL);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            PutAfter(STEEL_SHOVEL, Items.IRON_HOE);
            PutAfter(STEEL_PICKAXE, STEEL_SHOVEL);
            PutAfter(STEEL_AXE, STEEL_PICKAXE);
            PutAfter(STEEL_HOE, STEEL_AXE);
            PutAfter(URINE_BUCKET, MILK_BUCKET);
            PutAfter(ROCKET_FUEL_BUCKET, URINE_BUCKET);
        }
    }

    private static void PutAfter(ItemLike item, ItemLike putAfterItem){
        event.insertAfter(putAfterItem.asItem().getDefaultInstance(),
                item.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    /*
    private static void PutAfter(Supplier<Item> item, Item putAfterItem){
        event.insertAfter(putAfterItem.getDefaultInstance(),
                item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

    }

     */
}

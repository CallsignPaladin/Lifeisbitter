package paladin.lifeisbitter;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.item.AllBasicItems;

import static net.minecraft.world.item.Items.*;
import static paladin.lifeisbitter.item.AllBasicItems.*;
import static paladin.lifeisbitter.item.armor.ArmorItems.*;
import static paladin.lifeisbitter.item.tools.ToolItems.*;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lifeisbitter.MODID);

    private static BuildCreativeModeTabContentsEvent event;

    public static final void Register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack((AllBasicItems.POLISHED_DIAMOND.get())))
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
                        //output.accept(URINE_BUCKET);
                        output.accept(ROCKET_FUEL_BUCKET.get());
                        output.accept(COPPER_BURNER_ITEM.get());
                        output.accept(TALL_BLAST_FURNACE_ITEM.get());
                        output.accept(USED_FILTER.get());
                        output.accept(COKE.get());
                    })
                    .build()
            );

    public static void AddCreative(BuildCreativeModeTabContentsEvent event) {
        CreativeTabs.event = event;
        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            PutAfter(STEEL_HELMET, Items.IRON_BOOTS);
            PutAfter(STEEL_CHESTPLATE, STEEL_HELMET);
            PutAfter(STEEL_LEGGINGS, STEEL_CHESTPLATE);
            PutAfter(STEEL_BOOTS, STEEL_LEGGINGS);
            PutAfter(STEEL_AXE, Items.IRON_AXE);
            PutAfter(STEEL_SWORD, Items.IRON_SWORD);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            PutAfter(STEEL_BLOCK_ITEM, Items.IRON_BLOCK);
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
            //PutAfter(URINE_BUCKET.asItem(), MILK_BUCKET);
            //PutAfter(ROCKET_FUEL_BUCKET, URINE_BUCKET);
        }
    }

    private static void PutAfter(RegistryObject<Item> item, RegistryObject<Item> putAfterItem){
        event.getEntries().putAfter(putAfterItem.get().getDefaultInstance(),
                item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    private static void PutAfter(RegistryObject<Item> item, Item putAfterItem){
        event.getEntries().putAfter(putAfterItem.getDefaultInstance(),
                item.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}

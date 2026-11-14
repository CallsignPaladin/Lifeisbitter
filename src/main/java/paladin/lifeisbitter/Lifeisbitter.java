package paladin.lifeisbitter;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.Registrate;
import dev.engine_room.flywheel.lib.model.baked.NeoforgeMeshEmitter;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.joml.Vector3f;
import org.slf4j.Logger;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.blocks.fluid.BaseFluidType;
import paladin.lifeisbitter.crafting.ModRecipes;
import paladin.lifeisbitter.event.ModEvents;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.item.armor.ArmorItems;
import paladin.lifeisbitter.blocks.fluid.ModFluidTypes;
import paladin.lifeisbitter.blocks.fluid.ModFluids;
import paladin.lifeisbitter.item.armor.ArmorMaterials;
import paladin.lifeisbitter.item.tools.ToolItems;
import paladin.lifeisbitter.ponder.ModPonderIndex;
import paladin.lifeisbitter.ponder.ModPonderPlugin;
//import paladin.lifeisbitter.screens.ModMenuTypes;
//import paladin.lifeisbitter.screens.TallBlastFurnaceScreen;

@Mod(Lifeisbitter.MODID)
public class Lifeisbitter {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "lifeisbitter";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String shortVersion = "1.2.0.0"; //legacy from forge - no longer used

    //public static final Registrate REGISTRATE = Registrate.create(MODID); //TO DO - probably remove

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    static {
        REGISTRATE
                .defaultCreativeTab((ResourceKey<CreativeModeTab>) null)
                .setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                        .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }

    public Lifeisbitter(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);

        ArmorItems.ITEMS.register(modEventBus);
        //ArmorItems.VANILLA_ITEMS.register(modEventBus);
        AllBasicItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ENTITIES.register(modEventBus);
        ModBlocks.FLUIDS.register(modEventBus);
        ToolItems.ITEMS.register(modEventBus);
        ToolItems.VANILLA_ITEMS.register(modEventBus);
        ArmorMaterials.ARMOR_MATERIALS.register(modEventBus);

        //ModMenuTypes.register(modEventBus); TO DO low priority

        ModRecipes.SERIALIZERS.register(modEventBus);

        ModFluids.FLUIDS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        //NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC,"lifeisbitter-server.toml");

        /*
        fmlJavaModLoadingContext.registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(
                        () -> shortVersion + ".x",
                        (remoteVersionString, isServer) -> {return remoteVersionString.startsWith(shortVersion);}
                )
            );

         */
        //modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new); //TO DO low prio
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        CreativeTabs.AddCreative(event);
    }


    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_URINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_URINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_ROCKET_FUEL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_ROCKET_FUEL.get(), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.COPPER_BURNER.get(), RenderType.cutout()); //TO DO: předělat na novou verzi - nejspíš odstranit
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.TALL_BLAST_FURNACE.get(), RenderType.translucent()); TO DO low prio
            //MenuScreens.register(ModMenuTypes.TALL_BLAST_FURNACE_MENU.get(), TallBlastFurnaceScreen::new); TO DO low prio
            PonderIndex.addPlugin(new ModPonderPlugin());
        }
    }
}

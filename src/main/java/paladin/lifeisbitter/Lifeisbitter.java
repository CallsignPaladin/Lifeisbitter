package paladin.lifeisbitter;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.crafting.ModRecipes;
import paladin.lifeisbitter.item.MaterialItems;
import paladin.lifeisbitter.item.armor.ArmorItems;
import paladin.lifeisbitter.item.fluid.ModFluidTypes;
import paladin.lifeisbitter.item.fluid.ModFluids;
import paladin.lifeisbitter.item.tools.ToolItems;
import paladin.lifeisbitter.ponder.ModPonderPlugin;
import paladin.lifeisbitter.screens.ModMenuTypes;
import paladin.lifeisbitter.screens.TallBlastFurnaceScreen;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Lifeisbitter.MODID)
public class Lifeisbitter {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "lifeisbitter";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String shortVersion = "1.1.0.0";

    public static final Registrate REGISTRATE = Registrate.create(MODID);

    public Lifeisbitter(FMLJavaModLoadingContext fmlJavaModLoadingContext) {
        IEventBus modEventBus = fmlJavaModLoadingContext.getModEventBus();//FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ArmorItems.ITEMS.register(modEventBus);
        ArmorItems.VANILLA_ITEMS.register(modEventBus);
        MaterialItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ENTITIES.register(modEventBus);
        ToolItems.ITEMS.register(modEventBus);
        ToolItems.VANILLA_ITEMS.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        fmlJavaModLoadingContext.registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC, "lifeisbitter-server.toml");

        fmlJavaModLoadingContext.registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(
                        () -> shortVersion + ".x",
                        (remoteVersionString, isServer) -> {return remoteVersionString.startsWith(shortVersion);}
                )
            );
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        CreativeTabs.AddCreative(event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_URINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_URINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_ROCKET_FUEL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_ROCKET_FUEL.get(), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.COPPER_BURNER.get(), RenderType.cutout());
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.TALL_BLAST_FURNACE.get(), RenderType.translucent());
            MenuScreens.register(ModMenuTypes.TALL_BLAST_FURNACE_MENU.get(), TallBlastFurnaceScreen::new);
            PonderIndex.addPlugin(new ModPonderPlugin());
        }
    }
}

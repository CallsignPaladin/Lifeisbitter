package paladin.lifeisbitter.item.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.item.tools.ToolItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Lifeisbitter.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_URINE = FLUIDS.register("urine",
            () -> new ForgeFlowingFluid.Source(ModFluids.URINE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_URINE = FLUIDS.register("flowing_urine",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.URINE_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_ROCKET_FUEL = FLUIDS.register("rocket_fuel",
            () -> new ForgeFlowingFluid.Source(ModFluids.ROCKET_FUEL_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ROCKET_FUEL = FLUIDS.register("flowing_rocket_fuel",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.ROCKET_FUEL_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties URINE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.URINE_FLUID_TYPE, SOURCE_URINE, FLOWING_URINE)
            .slopeFindDistance(8).levelDecreasePerBlock(1).block(ModBlocks.URINE_BLOCK)
            .bucket(AllBasicItems.URINE_BUCKET);

    public static final ForgeFlowingFluid.Properties ROCKET_FUEL_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.ROCKET_FUEL_FLUID_TYPE, SOURCE_ROCKET_FUEL, FLOWING_ROCKET_FUEL)
            .slopeFindDistance(8).levelDecreasePerBlock(1).block(ModBlocks.ROCKET_FUEL_BLOCK)
            .bucket(AllBasicItems.ROCKET_FUEL_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}

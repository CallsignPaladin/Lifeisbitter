package paladin.lifeisbitter.blocks.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;
import paladin.lifeisbitter.Lifeisbitter;

public class ModFluidTypes { //TO DO: přidat overlay
    public static final ResourceLocation URINE_STILL_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "block/urine_still");
    public static final ResourceLocation URINE_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "block/urine_flow");

    public static final ResourceLocation ROCKET_FUEL_STILL_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/rocket_fuel_still");
    public static final ResourceLocation ROCKET_FUEL_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/rocket_fuel_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Lifeisbitter.MODID);

    public static final DeferredHolder<FluidType, BaseFluidType> URINE_TYPE = FLUID_TYPES.register("urine", () -> new BaseFluidType(
            URINE_STILL_RL,
            URINE_FLOWING_RL,
            0xDAB79113 ,
            new Vector3f(158f / 255f, 113f / 255f, 0f / 255f ),
            FluidType.Properties.create()
                    .density(1000)
                    .viscosity(1000)
                    .supportsBoating(true)
                    .canHydrate(false)
                    .canExtinguish(true)
                    .canSwim(true)
                    .canDrown(true)
                    .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK)
                    .canConvertToSource(false)
    ));

    public static final DeferredHolder<FluidType, BaseFluidType> ROCKET_FUEL_TYPE = FLUID_TYPES.register("rocket_fuel", () -> new BaseFluidType(
            ROCKET_FUEL_STILL_RL,
            ROCKET_FUEL_FLOWING_RL,
            0xFF6F00AF ,
            new Vector3f(111f / 255f, 0f / 255f, 175f / 255f ),
            FluidType.Properties.create()
                    .density(3000)
                    .viscosity(6000)
                    .canHydrate(false)
                    .fallDistanceModifier(0.2F)
                    .supportsBoating(true)
                    .canExtinguish(false)
                    .canSwim(true)
                    .canDrown(true)
                    .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK)
                    .canConvertToSource(false)
    ));

    /*
    public static final ResourceLocation URINE_STILL_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/urine_still");
    public static final ResourceLocation URINE_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/urine_flow");
    public static final ResourceLocation URINE_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/water_overlay");

    public static final ResourceLocation ROCKET_FUEL_STILL_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/rocket_fuel_still");
    public static final ResourceLocation ROCKET_FUEL_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/rocket_fuel_flow");
    public static final ResourceLocation ROCKET_FUEL_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID,"block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Lifeisbitter.MODID);

    public static final RegistryObject<FluidType> URINE_FLUID_TYPE = register("urine",
                    FluidType.Properties.create()
                            .density(1000)
                            .viscosity(1000)
                            .canHydrate(true)
                            .supportsBoating(true)
                            .canExtinguish(true)
                            .canDrown(true)
                            .sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK), 0xDAB79113, new Vector3f(158f / 255f, 113f / 255f, 0f / 255f),
            URINE_STILL_RL,
            URINE_FLOWING_RL,
            URINE_OVERLAY_RL
    );

    public static final RegistryObject<FluidType> ROCKET_FUEL_FLUID_TYPE = register("rocket_fuel",
            FluidType.Properties.create()
                    .density(3000)
                    .viscosity(6000)
                    .fallDistanceModifier(0.2F)
                    .canExtinguish(false)
                    .canDrown(true)
                    .sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK), 0xFF6F00AF, new Vector3f(111f / 255f, 0f / 255f, 175f / 255f),
            ROCKET_FUEL_STILL_RL,
            ROCKET_FUEL_FLOWING_RL,
            ROCKET_FUEL_OVERLAY_RL
            );

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties, int color, Vector3f rgb,
                                                      ResourceLocation still, ResourceLocation flowing, ResourceLocation overlay) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(still, flowing,
                color, rgb, properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
     */
}

package paladin.lifeisbitter.blocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.*;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.burners.CopperBurnerBlock;
import paladin.lifeisbitter.blocks.burners.CopperBurnerBlockEntity;
import paladin.lifeisbitter.blocks.burners.CopperBurnerBlockItem;
import paladin.lifeisbitter.blocks.fluid.ModFluids;
import paladin.lifeisbitter.blocks.fluid.RocketFuelLiquidBlock;
import paladin.lifeisbitter.blocks.fluid.UrineLiquidBlock;
import paladin.lifeisbitter.blocks.processing.TallBlastFurnaceBlock;
import paladin.lifeisbitter.blocks.processing.TallBlastFurnaceBlockEntity;

import java.util.function.Supplier;

import static paladin.lifeisbitter.Lifeisbitter.REGISTRATE;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lifeisbitter.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Lifeisbitter.MODID);

    public static final DeferredBlock<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).destroyTime(Blocks.IRON_BLOCK.defaultDestroyTime())));

    /*
    public static final DeferredBlock<Block> COPPER_BURNER = BLOCKS.register("copper_burner",
            () -> new CopperBurnerBlock(BlockBehaviour.Properties.ofFullCopy(AllBlocks.BLAZE_BURNER.get()).destroyTime(AllBlocks.BLAZE_BURNER.get().defaultDestroyTime())));


     */





    public static final DeferredBlock<Block> TALL_BLAST_FURNACE = BLOCKS.register("tall_blast_furnace",
            () -> new TallBlastFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final Supplier<BlockEntityType<TallBlastFurnaceBlockEntity>> TALL_BLAST_FURNACE_ENTITY = BLOCK_ENTITIES.register("tall_blast_furnace",
            () -> BlockEntityType.Builder.of(TallBlastFurnaceBlockEntity::new, TALL_BLAST_FURNACE.get()).build(null));
    /*
    public static final DeferredBlock<BlockEntityType<TallBlastFurnaceBlockEntity>> TALL_BLAST_FURNACE_ENTITY = BLOCK_ENTITIES.register("tall_blast_furnace_entity",
            () -> BlockEntityType.Builder.of(TallBlastFurnaceBlockEntity::new, TALL_BLAST_FURNACE.get()).build(null));
*/
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Lifeisbitter.MODID);

    public static final DeferredBlock<LiquidBlock> URINE_BLOCK = BLOCKS.register("urine", ()-> new UrineLiquidBlock(ModFluids.SOURCE_URINE.get(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    public static final DeferredBlock<LiquidBlock> ROCKET_FUEL_BLOCK = BLOCKS.register("rocket_fuel", ()-> new RocketFuelLiquidBlock(ModFluids.SOURCE_ROCKET_FUEL.get(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    //public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> URINE_BLOCK = FLUIDS.register("urine", ()-> new BaseFlowingFluid.Source(ModFluids.SOURCE_URINE));

/*
    public static final DeferredBlock<LiquidBlock> URINE_BLOCK = BLOCKS.register("urine",
            () -> new LiquidBlock(ModFluids.SOURCE_URINE, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
            */

    //public static final BlockEntry<CopperBurnerBlock> COPER_BURNERR;

    public static final BlockEntry<CopperBurnerBlock> COPPER_BURNER;

    static {

        COPPER_BURNER = REGISTRATE.block("copper_burner", CopperBurnerBlock::new)
                .simpleItem()
                .properties((properties) -> BlockBehaviour.Properties.ofFullCopy(AllBlocks.BLAZE_BURNER.get())
                        .lightLevel(CopperBurnerBlock::getLight))
                .register();


        /*
        ITEMS.register(
                "copper_burner",
                () -> new CopperBurnerBlockItem(
                        ModBlocks.COPPER_BURNER.get(),
                        new Item.Properties()
                )
        );

         */
    }

    public static final Supplier<BlockEntityType<CopperBurnerBlockEntity>> COPPER_BURNER_ENTITY = BLOCK_ENTITIES.register("copper_burner",
            () -> BlockEntityType.Builder.of(CopperBurnerBlockEntity::new, COPPER_BURNER.get()).build(null));

     



    /*

    OLD FORGE CODE

    // FLUIDS
    public static final RegistryObject<LiquidBlock> URINE_BLOCK = BLOCKS.register("urine",
            () -> new LiquidBlock(ModFluids.SOURCE_URINE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> ROCKET_FUEL_BLOCK = BLOCKS.register("rocket_fuel",
            () -> new LiquidBlock(ModFluids.SOURCE_ROCKET_FUEL, BlockBehaviour.Properties.copy(Blocks.WATER)));

     */
}

package paladin.lifeisbitter.blocks;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.burners.CopperBurnerBlock;
import paladin.lifeisbitter.blocks.burners.CopperBurnerBlockEntity;
import paladin.lifeisbitter.blocks.processing.TallBlastFurnaceBlock;
import paladin.lifeisbitter.blocks.processing.TallBlastFurnaceBlockEntity;
import paladin.lifeisbitter.item.fluid.ModFluids;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Lifeisbitter.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Lifeisbitter.MODID);

    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(Blocks.IRON_BLOCK.defaultDestroyTime())));

    public static final RegistryObject<Block> COPPER_BURNER = BLOCKS.register("copper_burner",
            () -> new CopperBurnerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<BlockEntityType<CopperBurnerBlockEntity>> COPPER_BURNER_ENTITY = BLOCK_ENTITIES.register("copper_burner",
            () -> BlockEntityType.Builder.of(CopperBurnerBlockEntity::new, COPPER_BURNER.get()).build(null));

    public static final RegistryEntry<TallBlastFurnaceBlock> TALL_BLAST_FURNACE = Lifeisbitter.REGISTRATE.block("tall_blast_furnace", TallBlastFurnaceBlock::new).register();

    //public static final RegistryObject<Block> TALL_BLAST_FURNACE = BLOCKS.register("tall_blast_furnace",
    //        () -> new TallBlastFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(Blocks.IRON_BLOCK.defaultDestroyTime()).noOcclusion()));

    public static final RegistryObject<BlockEntityType<TallBlastFurnaceBlockEntity>> TALL_BLAST_FURNACE_ENTITY = BLOCK_ENTITIES.register("tall_blast_furnace_entity",
            () -> BlockEntityType.Builder.of(TallBlastFurnaceBlockEntity::new, TALL_BLAST_FURNACE.get()).build(null));

    // FLUIDS
    public static final RegistryObject<LiquidBlock> URINE_BLOCK = BLOCKS.register("urine",
            () -> new LiquidBlock(ModFluids.SOURCE_URINE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> ROCKET_FUEL_BLOCK = BLOCKS.register("rocket_fuel",
            () -> new LiquidBlock(ModFluids.SOURCE_ROCKET_FUEL, BlockBehaviour.Properties.copy(Blocks.WATER)));
}

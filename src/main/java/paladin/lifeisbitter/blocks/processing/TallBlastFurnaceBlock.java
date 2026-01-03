package paladin.lifeisbitter.blocks.processing;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import paladin.lifeisbitter.blocks.ModBlocks;

public class TallBlastFurnaceBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0,0,0, 16, 30, 16);

    public TallBlastFurnaceBlock(Properties properties) {
        super(properties);
        System.out.println("načítání blocku");
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean pIsMoving) {
        if(blockState.getBlock() != newBlockState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof TallBlastFurnaceBlockEntity){
                ((TallBlastFurnaceBlockEntity) blockEntity).drops();
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof TallBlastFurnaceBlockEntity){
                NetworkHooks.openScreen(((ServerPlayer)player ), (TallBlastFurnaceBlockEntity)blockEntity,blockPos);
            }else{
                throw new IllegalStateException("Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TallBlastFurnaceBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        System.out.println("getTicker");

        if(level.isClientSide()){
            System.out.println("Client side nope");
            return null;
        }
        //System.out.println("ModBlocks.TALL_BLAST_FURNACE_ENTITY.get() is null? " + ModBlocks.TALL_BLAST_FURNACE_ENTITY.get().toString());
        return createTickerHelper(blockEntityType, ModBlocks.TALL_BLAST_FURNACE_ENTITY.get(),
                (pLevel, pBlockPos, pBlockState, pBlockEntity) -> pBlockEntity.tick(pLevel, pBlockPos, pBlockState));

        //return super.getTicker(level, blockState, blockEntityType);
    }
}

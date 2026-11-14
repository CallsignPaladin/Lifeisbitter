package paladin.lifeisbitter.blocks.burners;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.logistics.stockTicker.StockTickerBlockEntity;
import com.simibubi.create.content.logistics.stockTicker.StockTickerInteractionHandler;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.util.FakePlayer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class CopperBurnerBlock extends BlazeBurnerBlock {
    public CopperBurnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockRayTraceResult) {

        ItemStack heldItem = player.getItemInHand(hand);
        HeatLevel heat = (HeatLevel)state.getValue(HEAT_LEVEL);
        if (AllItems.GOGGLES.isIn(heldItem) && heat != BlazeBurnerBlock.HeatLevel.NONE) {
            return this.onBlockEntityUseItemOn(world, pos, (bbte) -> {
                if (bbte.goggles) {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                } else {
                    bbte.goggles = true;
                    bbte.notifyUpdate();
                    return ItemInteractionResult.SUCCESS;
                }
            });
        } else {
            BlazeBurnerBlockEntity be = (CopperBurnerBlockEntity)this.getBlockEntity(world, pos);
            if (be != null && be.stockKeeper) {
                StockTickerBlockEntity stockTicker = BlazeBurnerBlockEntity.getStockTicker(world, pos);
                if (stockTicker != null) {
                    StockTickerInteractionHandler.interactWithLogisticsManagerAt(player, world, stockTicker.getBlockPos());
                }

                return ItemInteractionResult.SUCCESS;
            } else if (heldItem.isEmpty() && heat != BlazeBurnerBlock.HeatLevel.NONE) {
                return this.onBlockEntityUseItemOn(world, pos, (bbte) -> {
                    if (!bbte.goggles) {
                        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                    } else {
                        bbte.goggles = false;
                        bbte.notifyUpdate();
                        return ItemInteractionResult.SUCCESS;
                    }
                });
            } else if (heat == BlazeBurnerBlock.HeatLevel.NONE) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            } else {
                boolean doNotConsume = player.isCreative();
                boolean forceOverflow = !(player instanceof FakePlayer);
                InteractionResultHolder<ItemStack> res = tryInsert(state, world, pos, heldItem, doNotConsume, forceOverflow, false);
                ItemStack leftover = (ItemStack)res.getObject();
                if (!world.isClientSide && !doNotConsume && !leftover.isEmpty()) {
                    if (heldItem.isEmpty()) {
                        player.setItemInHand(hand, leftover);
                    } else if (!player.getInventory().add(leftover)) {
                        player.drop(leftover, false);
                    }
                }

                return res.getResult() == InteractionResult.SUCCESS ? ItemInteractionResult.SUCCESS : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }
    }

    public static InteractionResultHolder<ItemStack> tryInsert(BlockState state, Level world, BlockPos pos,
                                                               ItemStack stack, boolean doNotConsume, boolean forceOverflow, boolean simulate) {
        if (!state.hasBlockEntity())
            return InteractionResultHolder.fail(ItemStack.EMPTY);

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CopperBurnerBlockEntity burnerBE))
            return InteractionResultHolder.fail(ItemStack.EMPTY);

        if (burnerBE.isCreativeFuel(stack)) {
            if (!simulate)
                burnerBE.applyCreativeFuel();
            return InteractionResultHolder.success(ItemStack.EMPTY);
        }
        if (!burnerBE.tryUpdateFuel(stack, forceOverflow, simulate))
            return InteractionResultHolder.fail(ItemStack.EMPTY);

        if (!doNotConsume) {
            ItemStack container = stack.hasCraftingRemainingItem() ? stack.getCraftingRemainingItem() : ItemStack.EMPTY;
            if (!world.isClientSide) {
                stack.shrink(1);
            }
            return InteractionResultHolder.success(container);
        }
        return InteractionResultHolder.success(ItemStack.EMPTY);
    }

    public @NotNull BlockState getStateForPlacement(BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        Item item = stack.getItem();
        BlockState defaultState = this.defaultBlockState();
        if (!(item instanceof BlazeBurnerBlockItem)) {
            return defaultState;
        } else {
            HeatLevel initialHeat = BlazeBurnerBlock.HeatLevel.SMOULDERING;
            return (BlockState)((BlockState)defaultState.setValue(HEAT_LEVEL, initialHeat)).setValue(FACING, Direction.NORTH);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CopperBurnerBlockEntity(pos, state);
    }

    public static int getLight(BlockState state){
        HeatLevel level = state.getValue(HEAT_LEVEL);
        return switch (level) {
            case NONE, SMOULDERING -> 0;
            default -> 8;
        };
    }
}
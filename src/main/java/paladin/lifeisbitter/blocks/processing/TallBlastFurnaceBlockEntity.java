package paladin.lifeisbitter.blocks.processing;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.screens.TallBlastFurnaceMenu;

public class TallBlastFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(4);

    private static final int OUTPUT_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    public int flameIntensity = 0;
    public static final int maxFlameIntensity = 14;
    public int moltenMetalRegresing = 0;

    public TallBlastFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.TALL_BLAST_FURNACE_ENTITY.get(),blockPos, blockState);
        System.out.println("načítání entity");
        this.data = new ContainerData() {
            @Override
            public int get(int p_39284_) {
                return switch (p_39284_){
                    case 0 -> TallBlastFurnaceBlockEntity.this.progress;
                    case 1 -> TallBlastFurnaceBlockEntity.this.maxProgress;
                    case 2 -> TallBlastFurnaceBlockEntity.this.flameIntensity;
                    case 3 -> TallBlastFurnaceBlockEntity.this.moltenMetalRegresing;
                    default -> 0;
                };
            }

            @Override
            public void set(int p_39285_, int p_39286_) {
                switch (p_39285_){
                    case 0 -> TallBlastFurnaceBlockEntity.this.progress = p_39285_;
                    case 1 -> TallBlastFurnaceBlockEntity.this.maxProgress = p_39285_;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,@Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        System.out.println("on Load");
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for(int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.lifeisbitter.tall_blast_furnace");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory inventory, Player player) {
        return new TallBlastFurnaceMenu(pContainerId, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        pTag.putInt("tall_blast_furnace.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        System.out.println("Load");
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("tall_blast_furnace.progress");
    }

    public void tick(Level pLevel, BlockPos pBlockPos, BlockState pBlockState) {

        boolean updateFire = (level.getGameTime() % 2) == 0;
        if (hasRecipe()) {
            increaseProgress();
            setChanged(pLevel,pBlockPos,pBlockState);
            if(hasProgressFinished()){
                Process();
                resetProgress();
            }
            if(updateFire && flameIntensity < maxFlameIntensity) flameIntensity += 1;
            moltenMetalRegresing = 0;
        } else{
            if(updateFire && flameIntensity > 0) flameIntensity -= 1;
            moltenMetalRegresing = 1;
            resetProgress();
        }
    }

    private void resetProgress() {
        System.out.println("Reseting progress");
        progress = 0;
    }

    private boolean hasRecipe() {
        return CanProcess();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseProgress() {
        progress++;
    }

    private boolean CanProcess(){
        boolean hasCraftingItem = this.itemStackHandler.getStackInSlot(0).getItem() == AllBasicItems.STEEL_INGOT.get();
        ItemStack result = new ItemStack(AllBasicItems.STEEL_SHEET.get());

        return hasCraftingItem && result.getCount() < 64 && (result.getItem() == AllBasicItems.STEEL_SHEET.get() || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty());
    }

     private void Process(){
        ItemStack result = new ItemStack(AllBasicItems.STEEL_SHEET.get(), 1);
        this.itemStackHandler.extractItem(0,1,false);

        this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
     }
}

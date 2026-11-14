package paladin.lifeisbitter.mixins;

import com.simibubi.create.content.trains.entity.Carriage;
import com.simibubi.create.content.trains.entity.Train;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import paladin.lifeisbitter.Config;
import paladin.lifeisbitter.item.AllBasicItems;

import java.util.List;

@Mixin(Train.class)
public class TrainMixin {
    @Shadow public List<Carriage> carriages;
    @Shadow public double speed;
    public int turboFuelTicks = 0;

    @Inject(method = "burnFuel", at = @At("HEAD"), cancellable = true) //TO DO - add remap
    public void burnFuel(CallbackInfo ci) {
        if(turboFuelTicks > 0) {
            turboFuelTicks--;
            System.out.println("turbo fuel ticks: " + turboFuelTicks);
            ci.cancel();
        }else {
            boolean iterateFromBack = this.speed < (double)0.0F;
            int carriageCount = this.carriages.size();

            for(int index = 0; index < carriageCount; ++index) {
                int i = iterateFromBack ? carriageCount - 1 - index : index;
                Carriage carriage = (Carriage)this.carriages.get(i);
                IItemHandlerModifiable fuelItems = carriage.storage.getFuelItems();
                if (fuelItems != null) {
                    for(int slot = 0; slot < fuelItems.getSlots(); ++slot) {
                        ItemStack stack = fuelItems.extractItem(slot, 1, true);
                        if(stack.getItem() == AllBasicItems.URINE_BUCKET.get()){ //TO DO: change to rocketfuel
                            int rocketFuelBurnTime = 1000;
                            turboFuelTicks += rocketFuelBurnTime;
                            fuelItems.extractItem(slot, 1, false);
                            ItemStack containerItem = Items.BUCKET.getDefaultInstance();
                            if (!containerItem.isEmpty()) {
                                ItemHandlerHelper.insertItemStacked(fuelItems, containerItem, false);
                            }

                            System.out.println("rocket fuel active!!! yay ");

                            return;
                        }

                        /*int burnTime = ForgeHooks.getBurnTime(stack, (RecipeType)null);
                        if (burnTime > 0) {
                            stack = fuelItems.extractItem(slot, 1, false);
                            this.fuelTicks += burnTime * stack.m_41613_();
                            ItemStack containerItem = stack.getCraftingRemainingItem();
                            if (!containerItem.m_41619_()) {
                                ItemHandlerHelper.insertItemStacked(fuelItems, containerItem, false);
                            }

                            return;
                         */
                        }
                    }
                }
                if(turboFuelTicks > 0) ci.cancel();
            }
        //fuelTicks = 0;
    }

    @Inject(method = "maxSpeed", at = @At("HEAD"), cancellable = true)
    public void maxSpeed(CallbackInfoReturnable<Float> cir){
        if(turboFuelTicks > 0) cir.setReturnValue(Config.rocketFueledTrainTopSpeed / 20f);
    }

    @Inject(method = "maxTurnSpeed", at = @At("HEAD"), cancellable = true)
    public void maxTurnSpeed(CallbackInfoReturnable<Float> cir){
        if(turboFuelTicks > 0) cir.setReturnValue(Config.rocketFueledTrainTopTurningSpeed / 20f);
    }

    @Inject(method = "acceleration", at = @At("HEAD"), cancellable = true)
    public void acceleration(CallbackInfoReturnable<Float> cir){
        if(turboFuelTicks > 0) cir.setReturnValue(Config.rocketFueledTrainAcceleration / 20f);
    }
}

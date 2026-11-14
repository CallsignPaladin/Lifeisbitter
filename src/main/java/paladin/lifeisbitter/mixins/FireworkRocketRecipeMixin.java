package paladin.lifeisbitter.mixins;

import com.simibubi.create.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import paladin.lifeisbitter.item.AllBasicItems;

import java.util.ArrayList;
import java.util.List;

import static paladin.lifeisbitter.Config.requireSolidFuelForRocket;
import static paladin.lifeisbitter.Config.requireSolidFuelForRocketAlways;

@Mixin(FireworkRocketRecipe.class)
public class FireworkRocketRecipeMixin {
    private static final Ingredient CARDBOARD_INGREDIENT = Ingredient.of(AllItems.CARDBOARD);
    private static final Ingredient GUNPOWDER_INGREDIENT = Ingredient.of(Items.GUNPOWDER);
    private static final Ingredient SOLID_FUEL_INGREDIENT = Ingredient.of(AllBasicItems.SOLID_FUEL.get());
    private static final Ingredient STAR_INGREDIENT = Ingredient.of(Items.FIREWORK_STAR);
    private static final int maxFlightCount = 5;

    @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
    public void matches(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir) {
        boolean containsCardboard = false;
        int gunpowderCount = 0;
        int solidFuelCount = 0;
        boolean hasStar = false;

        for(int j = 0; j < input.size(); ++j) {
            ItemStack itemstack = input.getItem(j);
            if (!itemstack.isEmpty()) {
                if (CARDBOARD_INGREDIENT.test(itemstack)) {
                    if (containsCardboard) {
                        cir.setReturnValue(false);
                        //return false;
                    }

                    containsCardboard = true;
                } else if (GUNPOWDER_INGREDIENT.test(itemstack)) {
                    ++gunpowderCount;
                    if (gunpowderCount > 3 || solidFuelCount + gunpowderCount > maxFlightCount) {
                        //return false;
                        cir.setReturnValue(false);
                    }
                } else if (SOLID_FUEL_INGREDIENT.test(itemstack)) {
                    ++solidFuelCount;
                    if (solidFuelCount + gunpowderCount > maxFlightCount) {
                        //return false;
                        cir.setReturnValue(false);
                    }
                }
                else if (STAR_INGREDIENT.test(itemstack)) {
                    hasStar = true;
                } else {
                    //return false;
                    cir.setReturnValue(false);
                }
            }
        }
        boolean returnValue = containsCardboard && ((gunpowderCount >= 1 && (hasStar || !requireSolidFuelForRocket) && !requireSolidFuelForRocketAlways) || (solidFuelCount >= 1 && gunpowderCount == 0));
        cir.setReturnValue(returnValue);
    }

    @Inject(method = "assemble", at = @At("HEAD"), cancellable = true)
    public void assemble(CraftingInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir){
        List<FireworkExplosion> list = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < input.size(); ++j) {
            ItemStack itemStack = input.getItem(j);
            if (!itemStack.isEmpty()) {
                if (GUNPOWDER_INGREDIENT.test(itemStack) || SOLID_FUEL_INGREDIENT.test(itemStack)) {
                    ++i;
                } else if (STAR_INGREDIENT.test(itemStack)) {
                    FireworkExplosion fireworkexplosion = (FireworkExplosion) itemStack.get(DataComponents.FIREWORK_EXPLOSION);
                    if (fireworkexplosion != null) {
                        list.add(fireworkexplosion);
                    }
                }
            }
        }

        ItemStack result = new ItemStack(Items.FIREWORK_ROCKET, 3);
        result.set(DataComponents.FIREWORKS, new Fireworks(i, list));
        cir.setReturnValue(result);
        //return result;
    }
}

package paladin.lifeisbitter.crafting;

import com.simibubi.create.AllItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import paladin.lifeisbitter.item.AllBasicItems;

import static paladin.lifeisbitter.Config.requireSolidFuelForRocket;
import static paladin.lifeisbitter.Config.requireSolidFuelForRocketAlways;

public class FireworkOverride extends FireworkRocketRecipe {
    private static final Ingredient CARDBOARD_INGREDIENT = Ingredient.of(AllItems.CARDBOARD);
    private static final Ingredient GUNPOWDER_INGREDIENT = Ingredient.of(Items.GUNPOWDER);
    private static final Ingredient SOLID_FUEL_INGREDIENT = Ingredient.of(AllBasicItems.SOLID_FUEL.get());
    private static final Ingredient STAR_INGREDIENT = Ingredient.of(Items.FIREWORK_STAR);
    private static final int maxFlightCount = 5;

    public FireworkOverride(ResourceLocation p_250923_, CraftingBookCategory p_250134_) {
        super(p_250923_, p_250134_);
    }

    @Override
    public boolean matches(CraftingContainer p_43854_, Level p_43855_) {
        boolean flag = false;
        int gunpowderCount = 0;
        int solidFuelCount = 0;
        boolean hasStar = false;

        for(int j = 0; j < p_43854_.getContainerSize(); ++j) {
            ItemStack itemstack = p_43854_.getItem(j);
            if (!itemstack.isEmpty()) {
                if (CARDBOARD_INGREDIENT.test(itemstack)) {
                    if (flag) {
                        return false;
                    }

                    flag = true;
                } else if (GUNPOWDER_INGREDIENT.test(itemstack)) {
                    ++gunpowderCount;
                    if (gunpowderCount > 3 || solidFuelCount + gunpowderCount > maxFlightCount) {
                        return false;
                    }
                } else if (SOLID_FUEL_INGREDIENT.test(itemstack)) {
                    ++solidFuelCount;
                    if (solidFuelCount + gunpowderCount > maxFlightCount) {
                        return false;
                    }
                }
                else if (STAR_INGREDIENT.test(itemstack)) {
                    hasStar = true;
                } else {
                    return false;
                }
            }
        }

        return flag && ((gunpowderCount >= 1 && (hasStar || !requireSolidFuelForRocket) && !requireSolidFuelForRocketAlways) || (solidFuelCount >= 1 && gunpowderCount == 0));
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess p_266791_) {
        ItemStack fireworksStack = new ItemStack(Items.FIREWORK_ROCKET, 3);
        CompoundTag compoundtag = fireworksStack.getOrCreateTagElement("Fireworks");
        ListTag listtag = new ListTag();
        int i = 0;

        for(int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemStack = container.getItem(j);
            if (!itemStack.isEmpty()) {
                if (GUNPOWDER_INGREDIENT.test(itemStack) || SOLID_FUEL_INGREDIENT.test(itemStack)) {
                    ++i;
                } else if (STAR_INGREDIENT.test(itemStack)) {
                    CompoundTag compoundtag1 = itemStack.getTagElement("Explosion");
                    if (compoundtag1 != null) {
                        listtag.add(compoundtag1);
                    }
                }
            }
        }

        compoundtag.putByte("Flight", (byte)i);
        if (!listtag.isEmpty()) {
            compoundtag.put("Explosions", listtag);
        }

        return fireworksStack;
    }
}

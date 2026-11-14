package paladin.lifeisbitter.crafting;

import com.simibubi.create.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import paladin.lifeisbitter.item.AllBasicItems;

import java.util.ArrayList;
import java.util.List;

import static paladin.lifeisbitter.Config.requireSolidFuelForRocket;
import static paladin.lifeisbitter.Config.requireSolidFuelForRocketAlways;


public class FireworkOverride extends FireworkRocketRecipe {
    private static final Ingredient CARDBOARD_INGREDIENT = Ingredient.of(AllItems.CARDBOARD);
    private static final Ingredient GUNPOWDER_INGREDIENT = Ingredient.of(Items.GUNPOWDER);
    private static final Ingredient SOLID_FUEL_INGREDIENT = Ingredient.of(AllBasicItems.SOLID_FUEL.get());
    private static final Ingredient STAR_INGREDIENT = Ingredient.of(Items.FIREWORK_STAR);
    private static final int maxFlightCount = 5;

    public FireworkOverride(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        boolean flag = false;
        int gunpowderCount = 0;
        int solidFuelCount = 0;
        boolean hasStar = false;

        for(int j = 0; j < craftingInput.size(); ++j) {
            ItemStack itemstack = craftingInput.getItem(j);
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
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        List<FireworkExplosion> list = new ArrayList();
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

        ItemStack itemstack1 = new ItemStack(Items.FIREWORK_ROCKET, 3);
        itemstack1.set(DataComponents.FIREWORKS, new Fireworks(i, list));
        return itemstack1;
    }
    /*
    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack fireworksStack = new ItemStack(Items.FIREWORK_ROCKET, 3);
        CompoundTag compoundtag = fireworksStack.getOrCreateTagElement("Fireworks");
        ListTag listtag = new ListTag();
        int i = 0;

        for(int j = 0; j < input.size(); ++j) {
            ItemStack itemStack = input.getItem(j);
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

     */
}



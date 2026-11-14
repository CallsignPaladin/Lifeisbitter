package paladin.lifeisbitter.crafting;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import paladin.lifeisbitter.Lifeisbitter;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Lifeisbitter.MODID);//minecraft


    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<FireworkOverride>> FIREWORKOVERRIDE_SERIALIZER =
            SERIALIZERS.register("crafting_special_firework_rocket", () -> new SimpleCraftingRecipeSerializer<>(FireworkOverride::new));
}

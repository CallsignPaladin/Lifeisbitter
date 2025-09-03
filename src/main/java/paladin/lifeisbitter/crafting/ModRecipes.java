package paladin.lifeisbitter.crafting;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import paladin.lifeisbitter.Lifeisbitter;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "minecraft");//Lifeisbitter.MODID

    public static final RegistryObject<RecipeSerializer<FireworkOverride>> FIREWORKOVERRIDE_SERIALIZER =
            SERIALIZERS.register("crafting_special_firework_rocket", () -> new SimpleCraftingRecipeSerializer<>(FireworkOverride::new));

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}

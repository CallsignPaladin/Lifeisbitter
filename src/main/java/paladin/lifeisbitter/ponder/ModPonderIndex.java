package paladin.lifeisbitter.ponder;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.item.tools.ToolItems;
import paladin.lifeisbitter.ponder.scenes.UrineScene;

public class ModPonderIndex {

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);
        /*
        HELPER.forComponents((Iterable<? extends ItemProviderEntry<?>>) Items.COBBLESTONE)
                .addStoryBoard("urine", UrineScene::scene);

         */
        HELPER.forComponents((ItemProviderEntry<?>) AllBasicItems.URINE_BUCKET)
                .addStoryBoard("urine", UrineScene::scene);
    }
}


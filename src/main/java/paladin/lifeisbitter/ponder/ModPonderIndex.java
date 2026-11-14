package paladin.lifeisbitter.ponder;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.ponder.scenes.UrineScene;

public class ModPonderIndex {


    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

        helper.addStoryBoard(AllBasicItems.URINE_BUCKET.getId(), "urine", UrineScene::scene);

    }

}


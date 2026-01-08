package paladin.lifeisbitter.ponder;

import com.simibubi.create.AllItems;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import paladin.lifeisbitter.blocks.ModBlocks;
import paladin.lifeisbitter.item.MaterialItems;
import paladin.lifeisbitter.item.tools.ToolItems;
import paladin.lifeisbitter.ponder.scenes.UrineScene;

public class ModPonderIndex {

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);
        /*
        HELPER.forComponents((Iterable<? extends ItemProviderEntry<?>>) Items.COBBLESTONE)
                .addStoryBoard("urine", UrineScene::scene);

         */
        HELPER.forComponents((ItemProviderEntry<?>) ToolItems.URINE_BUCKET)
                .addStoryBoard("urine", UrineScene::scene);
    }
}


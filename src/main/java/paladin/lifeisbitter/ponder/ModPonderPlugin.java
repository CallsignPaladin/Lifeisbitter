package paladin.lifeisbitter.ponder;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import paladin.lifeisbitter.Lifeisbitter;

public class ModPonderPlugin implements PonderPlugin {
    @Override
    public @NotNull String getModId() {
        return Lifeisbitter.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        ModPonderIndex.register(helper);
        PonderPlugin.super.registerScenes(helper);
    }
}

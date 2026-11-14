package paladin.lifeisbitter.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import paladin.lifeisbitter.item.tools.ToolItems;

@Mixin(Items.class)
public class ItemsMixin { //TO DO asi odstranit
    @Inject(method = "registerItem", at = @At("HEAD"), cancellable = true)
    private static void replaceDiamondPickaxe(String key, Item item, CallbackInfoReturnable<Item> cir) {
/*
        if ("iron_pickaxee".equals(key)) {
            cir.setReturnValue(ToolItems.IRON_PICKAXE.asItem());
        }

 */
    }
}

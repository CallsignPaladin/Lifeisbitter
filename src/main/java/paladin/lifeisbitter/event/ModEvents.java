package paladin.lifeisbitter.event;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.item.fluid.ModFluids;
import paladin.lifeisbitter.item.tools.ToolItems;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = Lifeisbitter.MODID)
    public static class ForgeEvents{

        @SubscribeEvent
        public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
            Player player = event.getEntity();
            Level world = player.level();
            ItemStack heldItem = event.getItemStack();

            if (world.isClientSide) return;

            if (!(event.getTarget() instanceof Pig)) return;

            if (heldItem.getItem() == Items.BUCKET) {

                ItemStack urineBucket = new ItemStack(ToolItems.URINE_BUCKET.get());
                heldItem.shrink(1);

                if (!player.getInventory().add(urineBucket)) {
                    player.drop(urineBucket, false);
                }

                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.COW_MILK, SoundSource.PLAYERS, 1.0F, 1.0F);
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.CONSUME);

            }
        }

    }
    @Mod.EventBusSubscriber(modid = Lifeisbitter.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeClientEvents{
        @SubscribeEvent
        public static void onFogColor(ViewportEvent.ComputeFogColor event) {
            Camera camera = event.getCamera();
            Level level = Minecraft.getInstance().level;

            // Check if player is in your fluid
            if (level != null  && isInRocketFuelFluid(camera, level)) {
                event.setRed(0.20F);   // R value (0.0–1.0)
                event.setGreen(0.0F); // G value
                event.setBlue(0.27F);  // B value
            }
        }


        @SubscribeEvent
        public static void onFogRender(ViewportEvent.RenderFog event) {
            Camera camera = event.getCamera();
            Level level = Minecraft.getInstance().level;

            // Same fluid check
            if (level != null && isInRocketFuelFluid(camera, level)) {
                event.setNearPlaneDistance(0.0F);   // Where fog starts
                event.setFarPlaneDistance(2.5F);    // Where fog fully obscures vision
                event.setCanceled(true);            // Stop vanilla fog from overriding
                }
        }

        private static boolean isInRocketFuelFluid(Camera camera, Level level) {
            BlockPos eyePos = BlockPos.containing(camera.getPosition());
            Fluid fluid = level.getFluidState(eyePos).getType();
            return (fluid == ModFluids.SOURCE_ROCKET_FUEL.get()) || (fluid == ModFluids.FLOWING_ROCKET_FUEL.get());
        }
    }
}

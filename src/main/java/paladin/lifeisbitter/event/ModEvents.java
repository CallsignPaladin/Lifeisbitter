package paladin.lifeisbitter.event;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.joml.Vector3f;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.blocks.fluid.BaseFluidType;
import paladin.lifeisbitter.blocks.fluid.ModFluidTypes;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.blocks.fluid.ModFluids;

import java.util.List;

@EventBusSubscriber(modid = Lifeisbitter.MODID, value = Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void registerFluidExtensions(RegisterClientExtensionsEvent event){
        BaseFluidType urine = ModFluidTypes.URINE_TYPE.get();
        BaseFluidType rocketFuel = ModFluidTypes.ROCKET_FUEL_TYPE.get();

        registerFluidType(urine, event);
        registerFluidType(rocketFuel, event);
    }
    private static void registerFluidType(BaseFluidType type, RegisterClientExtensionsEvent event){
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return type.getStillTexture();
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return type.getFlowingTexture();
            }

            @Override
            public int getTintColor() {
                return type.getTintColor();
            }

            @Override
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return type.getFogColor();
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(1f);
                RenderSystem.setShaderFogEnd(6f);
            }
        }, type);
    }

    //pig 'milking'
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Level world = player.level();
        ItemStack heldItem = event.getItemStack();

        //if (world.isClientSide) return;

        if (!(event.getTarget() instanceof Pig)) return;

        if (heldItem.getItem() == Items.BUCKET) {

            ItemStack urineBucket = new ItemStack(AllBasicItems.URINE_BUCKET.get());
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

    //tool override
    @SubscribeEvent
    public static void modifyVanillaPickaxe(ModifyDefaultComponentsEvent event) {
        modifyToolDurability(Items.IRON_PICKAXE, 180, event);
        modifyToolDurability(Items.IRON_AXE, 180, event);
        modifyToolDurability(Items.IRON_SHOVEL, 180, event);
        modifyToolDurability(Items.IRON_HOE, 180, event);
        modifyToolDurability(Items.IRON_SWORD, 180, event);
        /* TO DO: pak si s tím pohrát
        event.modify(Items.IRON_PICKAXE, builder -> {
            builder.set(DataComponents.MAX_DAMAGE, 1000);
            builder.set(
                    DataComponents.TOOL,
                    new Tool(
                            List.of(
                                    Tool.Rule.deniesDrops(net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL),
                                    Tool.Rule.minesAndDrops(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE, 12.0F)
                            ),
                            1.0F,
                            1
                    )
            );
        });

         */
    }
    private static void modifyToolDurability(Item item, int durability, ModifyDefaultComponentsEvent event){
        event.modify(item, builder -> {
            builder.set(DataComponents.MAX_DAMAGE, durability);
        });
    }

    //armor override
    @SubscribeEvent
    public static void modifyArmorDurabilities(ModifyDefaultComponentsEvent event) {
        modifyArmorDurability(Items.IRON_HELMET, 132, event);
        modifyArmorDurability(Items.IRON_CHESTPLATE, 192, event);
        modifyArmorDurability(Items.IRON_LEGGINGS, 180, event);
        modifyArmorDurability(Items.IRON_BOOTS, 156, event);
    }
    private static void modifyArmorDurability(Item armorItem, int durability, ModifyDefaultComponentsEvent event){
        event.modify(armorItem, builder -> {
            builder.set(DataComponents.MAX_DAMAGE, durability);
        });
    }

    @SubscribeEvent
    public static void modifyArmorValues(ItemAttributeModifierEvent event) {
        if (event.getItemStack().is(Items.IRON_HELMET)) {
            modifyArmorValue("modified_iron_helmet", 1.0, EquipmentSlotGroup.HEAD, event);
        }
        if (event.getItemStack().is(Items.IRON_CHESTPLATE)) {
            modifyArmorValue("modified_iron_chestplate", 4.0, EquipmentSlotGroup.CHEST, event);
        }
        if (event.getItemStack().is(Items.IRON_LEGGINGS)) {
            modifyArmorValue("modified_iron_leggings", 3.0, EquipmentSlotGroup.LEGS, event);
        }
        if (event.getItemStack().is(Items.IRON_BOOTS)) {
            modifyArmorValue("modified_iron_boots", 2.0, EquipmentSlotGroup.FEET, event);
        }
    }
    private static void modifyArmorValue(String path, double value, EquipmentSlotGroup slot, ItemAttributeModifierEvent event){
        event.removeAllModifiersFor(Attributes.ARMOR); //TO DO možná odstranit
        event.replaceModifier(
                Attributes.ARMOR,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, path),
                        value,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                slot
        );
    }

    /*
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

                ItemStack urineBucket = new ItemStack(AllBasicItems.URINE_BUCKET.get());
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
    TO DO
     */
}

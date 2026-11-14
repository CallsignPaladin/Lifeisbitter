package paladin.lifeisbitter.item.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;
import paladin.lifeisbitter.Lifeisbitter;
import paladin.lifeisbitter.item.AllBasicItems;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static paladin.lifeisbitter.Lifeisbitter.MODID;

public class ArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MODID);

    public static final Holder<ArmorMaterial> STEEL =
            ARMOR_MATERIALS.register("steel", () -> new ArmorMaterial(
                    // Determines the defense value of this armor material, depending on what armor piece it is.
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 7);
                    }),
                    17,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(AllBasicItems.STEEL_INGOT),
                    List.of(
                            // Creates a new armor texture that will be located at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(MODID, "steel")
                            ),
                            // Creates a new armor texture that will be rendered on top of the previous at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
                            // 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(MODID, "steel"), "_overlay", false
                            )
                    ),
                    1f,0.1f
            ));

    public static final Holder<ArmorMaterial> IRON =
            ARMOR_MATERIALS.register("iron", () -> new ArmorMaterial(
                    // Determines the defense value of this armor material, depending on what armor piece it is.
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 1);
                        map.put(ArmorItem.Type.LEGGINGS, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 4);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 5);
                    }),
                    20,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(Tags.Items.INGOTS_COPPER),
                    List.of(
                            // Creates a new armor texture that will be located at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "iron")
                            ),
                            // Creates a new armor texture that will be rendered on top of the previous at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
                            // 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "iron"), "_overlay", false
                            )
                    ),
                    1f,0.1f
            ));
}

/*
 Old forge code
public enum ArmorMaterials implements ArmorMaterial {
    STEEL("steel", 19, new int[]{ 2, 6, 5, 2}, 20, SoundEvents.ARMOR_EQUIP_IRON.value(), 1f, 0f,
            () -> Ingredient.of(AllBasicItems.STEEL_INGOT.get())),
    IRON("iron", 12, new int[]{ 2, 4, 3, 1}, 9, SoundEvents.ARMOR_EQUIP_IRON.value(), 0f, 0f,
            () -> Ingredient.of(Items.IRON_INGOT));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipsound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13};

    ArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipsound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipsound = equipsound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipsound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return Lifeisbitter.MODID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
*/
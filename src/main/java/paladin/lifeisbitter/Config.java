package paladin.lifeisbitter;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Lifeisbitter.MODID)
public class Config {
    static String mps = "[in Blocks/Second]";
    static String acc = "[in Blocks/Second²]";

    //TO DO: add translation support

    public static boolean requireSolidFuelForRocket;
    public static boolean requireSolidFuelForRocketAlways;
    public static float rocketFueledTrainTopSpeed;
    public static float rocketFueledTrainTopTurningSpeed;
    public static float rocketFueledTrainAcceleration;

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


    private static final ModConfigSpec.BooleanValue REQUIRE_SOLID_FUEL_FOR_ROCKET = BUILDER
            .comment("Require solid fuel to craft fireworks for elytra flight?")
            .define("requireSolidFuelForRocket", true);

    private static final ModConfigSpec.BooleanValue REQUIRE_SOLID_FUEL_FOR_ROCKET_ALWAYS = BUILDER
            .comment("Require solid fuel to craft all types of fireworks?")
            .define("requireSolidFuelForRocketAlways", false);

    private static final ModConfigSpec.DoubleValue ROCKET_FUELED_TRAIN_TOP_SPEED = BUILDER
            .comment(mps,"The top speed of rocket fueled Trains.")
            .defineInRange("rocketFueledTrainTopSpeed", 64, 0, Float.MAX_VALUE);

    private static final ModConfigSpec.DoubleValue ROCKET_FUELED_TRAIN_TOP_TURNING_SPEED = BUILDER
            .comment(mps,"The top speed of rocket fueled Trains during a turn.")
            .defineInRange("rocketFueledTrainTopTurningSpeed", 32, 0, Float.MAX_VALUE);


    private static final ModConfigSpec.DoubleValue ROCKET_FUELED_TRAIN_ACCELERATION = BUILDER
            .comment(acc,"The acceleration of rocket fueled Trains.")
            .defineInRange("rocketFueledTrainAcceleration", 5, 0, Float.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        requireSolidFuelForRocket = REQUIRE_SOLID_FUEL_FOR_ROCKET.get();
        requireSolidFuelForRocketAlways = REQUIRE_SOLID_FUEL_FOR_ROCKET_ALWAYS.get();
        rocketFueledTrainTopSpeed = ROCKET_FUELED_TRAIN_TOP_SPEED.get().floatValue();
        rocketFueledTrainTopTurningSpeed = ROCKET_FUELED_TRAIN_TOP_TURNING_SPEED.get().floatValue();
        rocketFueledTrainAcceleration = ROCKET_FUELED_TRAIN_ACCELERATION.get().floatValue();
    }
}
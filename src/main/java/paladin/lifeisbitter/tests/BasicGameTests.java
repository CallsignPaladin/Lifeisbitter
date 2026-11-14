package paladin.lifeisbitter.tests;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import static paladin.lifeisbitter.Lifeisbitter.MODID;

@GameTestHolder(MODID)
public class BasicGameTests {
    @GameTest
    @PrefixGameTestTemplate(false)
    public static void emptyTest(GameTestHelper helper) {
        // Do stuff
    }
}

package paladin.lifeisbitter.ponder.scenes;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import paladin.lifeisbitter.item.AllBasicItems;
import paladin.lifeisbitter.item.tools.ToolItems;

public class UrineScene {
    public static void scene(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("urine", "Getting Urine");
        scene.configureBasePlate(0, 0, 6);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos deployerPos = util.grid().at(4, 1, 2);
        BlockPos burner = util.grid().at(1, 1, 3);
        BlockPos pigPos = util.grid().at(2,1,2);

        scene.world().createEntity(fact -> {
            Pig pig = EntityType.PIG.create(fact);
            Vec3 vec3 = util.vector().blockSurface(pigPos, Direction.DOWN);
            pig.setPosRaw(vec3.x(), vec3.y(), vec3.z());
            pig.setYRot(180);
            return pig;
        });

        scene.idle(30);

        scene.overlay().showText(60)
                .attachKeyFrame()
                .text("You can get urine by using bucket on pigs")
                .pointAt(util.vector().blockSurface(pigPos, Direction.WEST))
                .placeNearTarget();
        scene.idle(40);

        scene.world().showSection(util.select().position(deployerPos), Direction.DOWN);
        scene.world().showSection(util.select().position(burner), Direction.DOWN);
        scene.idle(20);

        BlockPos power = util.grid().at(4, 1, 3);
        Selection setup = util.select().fromTo(power, power.south(3));
        scene.world().showIndependentSection(setup, Direction.DOWN);

        scene.idle(10);

        Class<DeployerBlockEntity> teType = DeployerBlockEntity.class;
        ItemStack emptyBucket = new ItemStack(Items.BUCKET);
        ItemStack urineBucket = new ItemStack(AllBasicItems.URINE_BUCKET.get());
        Selection deployerSelection = util.select().position(deployerPos);

        scene.world().modifyBlockEntityNBT(deployerSelection, teType, (nbt) -> nbt.put("HeldItem", emptyBucket.serializeNBT()));

        scene.world().moveDeployer(deployerPos, 1.0F, 25);
        scene.idle(26);
        scene.world().modifyBlockEntityNBT(deployerSelection, teType, (nbt) -> nbt.put("HeldItem", urineBucket.serializeNBT()));
        scene.world().moveDeployer(deployerPos, -1.0F, 25);

        /*
        scene.overlay().showText(60)
                .attachKeyFrame()
                .text("You can get urine by using bucket on pigs")
                .pointAt(util.vector().blockSurface(deployer, Direction.WEST))
                .placeNearTarget();
        scene.idle(60);




        //scene.world().hideSection(util.select().position(deployer), Direction.UP);
        //scene.world().hideSection(util.select().position(burner), Direction.UP);
        scene.idle(10);

        scene.idle(20);

        /*
        Selection powerBehind = util.select().fromTo(util.grid().at(2, 1, 3), util.grid().at(2, 1, 5));
        BlockPos powerCog = util.grid().at(1, 0, 5);
        scene.world().showIndependentSection(powerBehind, Direction.NORTH);
        scene.world().showSection(util.select().position(powerCog), Direction.NORTH);
        scene.idle(20);

        scene.overlay().showText(60)
                .attachKeyFrame()
                .text("To operate, it needs power from below...")
                .pointAt(util.vector().blockSurface(power.above(), Direction.WEST))
                .placeNearTarget();
        scene.idle(40);

        Selection waterSetup = util.select().fromTo(util.grid().at(3, 1, 2), util.grid().at(4, 4, 4));
        scene.world().showIndependentSection(waterSetup, Direction.WEST);

        BlockPos pumpCogConnection = util.grid().at(2, 2, 3);
        BlockPos pump = util.grid().at(3,2,3);
        scene.world().setBlock(pumpCogConnection, AllBlocks.COGWHEEL.get()
                .defaultBlockState().setValue(RotatedPillarKineticBlock.AXIS, Direction.Axis.Z), false);
        scene.world().setKineticSpeed(util.select().position(pumpCogConnection), -64);
        scene.world().setKineticSpeed(util.select().position(pump), 64);
        scene.world().propagatePipeChange(pump);
        scene.world().showSection(util.select().position(pumpCogConnection), Direction.DOWN);

        scene.idle(40);

        scene.overlay().showText(60)
                .attachKeyFrame()
                .text("And water from the side.")
                .pointAt(util.vector().blockSurface(power.above(), Direction.EAST))
                .placeNearTarget();
        scene.idle(50);

        BlockPos examplePress = power.above(4);
        Class<MechanicalPressBlockEntity> pressType = MechanicalPressBlockEntity.class;
        scene.world().setKineticSpeed(util.select().position(examplePress), 64);

        Selection belt = util.select().fromTo(util.grid().at(1, 2, 2), util.grid().at(0, 1, 2));
        scene.world().showIndependentSection(belt, Direction.SOUTH);
        scene.world().modifyBlockEntity(examplePress, pressType, e -> e.getPressingBehaviour().start(PressingBehaviour.Mode.BASIN));

        ItemStack iceCube = new ItemStack(LuncheonItems.ICE_CUBE);
        BlockPos beltPos = util.grid().at(1, 2, 2);
        for(int i = 0; i < 2; i++) {
            scene.world().modifyBlockEntity(examplePress, pressType, e -> e.getPressingBehaviour().start(PressingBehaviour.Mode.BASIN));
            scene.idle(15);
            scene.world().createItemOnBelt(beltPos, Direction.SOUTH, iceCube);
            scene.idle(15);
        }

         */
    }
}

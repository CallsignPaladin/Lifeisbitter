package paladin.lifeisbitter.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import paladin.lifeisbitter.Lifeisbitter;

public class TallBlastFurnaceScreen extends AbstractContainerScreen<TallBlastFurnaceMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Lifeisbitter.MODID, "textures/gui/tall_blast_furnace_gui.png");

    public TallBlastFurnaceScreen(TallBlastFurnaceMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        this.imageHeight = 196;
        this.topPos -= 5;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y - 20, 0, 0, imageWidth, imageHeight);

        renderFire(guiGraphics, x, y);
        renderMoltenMetal(guiGraphics, x, y);

        if(menu.isCrafting()){
            renderProgressArrow(guiGraphics, x, y);
        }
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        //texture, x,y, texture location x, texture location y, size x, size y
        guiGraphics.blit(TEXTURE, x + 70, y + 27 - 20, 176, 0, 35, menu.getScaledProgress());
    }

    private void renderFire(GuiGraphics guiGraphics, int x, int y){
        int flameIntentsity = menu.getFlameIntensity();
        guiGraphics.blit(TEXTURE, x + 82, y + 81 - 20 + 14 - flameIntentsity, 230, 14 - flameIntentsity, 14, flameIntentsity);//menu.blockEntity.flameIntensity
    }

    private void renderMoltenMetal(GuiGraphics guiGraphics, int x, int y){
        int flameIntentsity = menu.getFlameIntensity();
        if(menu.isMoltenMetalRegressing()){
            guiGraphics.blit(TEXTURE, x + 58 + 14 - flameIntentsity, y + 80 - 20, 212 + 14 - flameIntentsity, 0, flameIntentsity, 8);
        }else {
            guiGraphics.blit(TEXTURE, x + 58, y + 80 - 20, 212, 0, flameIntentsity, 8);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

}

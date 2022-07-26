package com.cleanroommc.ponder.gui;

import com.cleanroommc.ponder.util.LerpedFloat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PonderGuiScreen extends GuiScreen {

    public final LerpedFloat transition = LerpedFloat.linear().startWithValue(0).chase(0, 0.1F, LerpedFloat.Chaser.LINEAR);
    protected final LerpedFloat arrowAnimation = LerpedFloat.linear().startWithValue(0).chase(0, 0.075F, LerpedFloat.Chaser.LINEAR);

    protected int windowWidth, windowHeight;
    protected int windowXOffset, windowYOffset;
    protected int guiLeft, guiTop;
    protected int depthPointX, depthPointY;

    public PonderGuiScreen() {
        ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        depthPointX = scaledRes.getScaledWidth() / 2;
        depthPointY = scaledRes.getScaledHeight() / 2;
    }

    /**
     * This method must be called before {@code super.initGui()}!
     */
    protected void setWindowSize(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }

    /**
     * This method must be called before {@code super.initGui()}!
     */
    protected void setWindowOffset(int xOffset, int yOffset) {
        windowXOffset = xOffset;
        windowYOffset = yOffset;
    }

    @Override
    public void initGui() {
        guiLeft = (width - windowWidth) / 2;
        guiTop = (height - windowHeight) / 2;
        guiLeft += windowXOffset;
        guiTop += windowYOffset;
    }

}

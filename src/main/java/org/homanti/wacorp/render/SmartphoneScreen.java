package org.homanti.wacorp.render;

import com.cinemamod.mcef.MCEF;
import com.cinemamod.mcef.MCEFBrowser;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.platform.Window;

public class SmartphoneScreen extends Screen {
    private static MCEFBrowser browser;

    private static final int BASE_PHONE_HEIGHT = 400;
    private static final float ASPECT_RATIO = 9f / 16f;

    private int phoneX, phoneY;
    private int phoneWidth, phoneHeight;
    private int browserPixelWidth, browserPixelHeight;

    private static boolean visible = false;

    public SmartphoneScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        if (browser == null) {
            MCEF.getSettings().setUserAgent(
                    "Mozilla/5.0 (Linux; Android 10; SM-G973F) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/110.0.5481.153 Mobile Safari/537.36"
            );
            browser = MCEF.createBrowser("https://www.google.com", true);
        }

        visible = true;
        calculatePhoneDimensions();
    }

    private void calculatePhoneDimensions() {
        Window window = this.minecraft.getWindow();

        int windowHeight = window.getHeight();
        double guiScale = window.getGuiScale();

        int physicalPhoneHeight = Math.max((int) (windowHeight * 0.5), BASE_PHONE_HEIGHT);
        int physicalPhoneWidth = (int) (physicalPhoneHeight * ASPECT_RATIO);

        phoneHeight = (int) (physicalPhoneHeight / guiScale);
        phoneWidth = (int) (physicalPhoneWidth / guiScale);

        int marginX = 20;
        int marginY = 20;
        phoneX = this.width - phoneWidth - marginX;
        phoneY = this.height - phoneHeight - marginY;

        if (browser != null) {
            browserPixelWidth = physicalPhoneWidth;
            browserPixelHeight = physicalPhoneHeight;
            browser.resize(browserPixelWidth, browserPixelHeight);
        }
    }

    private int browserX(double mouseX) {
        if (mouseX < phoneX) return 0;
        if (mouseX > phoneX + phoneWidth) return browserPixelWidth;

        double relativeX = (mouseX - phoneX) / phoneWidth;
        return (int) (relativeX * browserPixelWidth);
    }

    private int browserY(double mouseY) {
        if (mouseY < phoneY) return 0;
        if (mouseY > phoneY + phoneHeight) return browserPixelHeight;

        double relativeY = (mouseY - phoneY) / phoneHeight;
        return (int) (relativeY * browserPixelHeight);
    }

    private boolean isMouseOverPhone(double mouseX, double mouseY) {
        return mouseX >= phoneX && mouseX <= phoneX + phoneWidth &&
                mouseY >= phoneY && mouseY <= phoneY + phoneHeight;
    }

    @Override
    public void resize(Minecraft minecraft, int w, int h) {
        super.resize(minecraft, w, h);
        calculatePhoneDimensions();
    }

    @Override
    public void onClose() {
        visible = false;
        super.onClose();
    }

    public static void handleWorldUnload() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        visible = false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (!visible || browser == null) return;

        int backgroundColor = 0xFF1E1E1E;
        guiGraphics.fill(phoneX, phoneY, phoneX + phoneWidth, phoneY + phoneHeight, backgroundColor);

        renderBrowser();
    }

    private void renderBrowser() {
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, browser.getRenderer().getTextureID());

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

        // Нижний левый
        buffer.vertex(phoneX, phoneY + phoneHeight, 0)
                .uv(0.0f, 1.0f)
                .color(255, 255, 255, 255)
                .endVertex();

        // Нижний правый
        buffer.vertex(phoneX + phoneWidth, phoneY + phoneHeight, 0)
                .uv(1.0f, 1.0f)
                .color(255, 255, 255, 255)
                .endVertex();

        // Верхний правый
        buffer.vertex(phoneX + phoneWidth, phoneY, 0)
                .uv(1.0f, 0.0f)
                .color(255, 255, 255, 255)
                .endVertex();

        // Верхний левый
        buffer.vertex(phoneX, phoneY, 0)
                .uv(0.0f, 0.0f)
                .color(255, 255, 255, 255)
                .endVertex();

        BufferUploader.drawWithShader(buffer.end());

        RenderSystem.setShaderTexture(0, 0);
        RenderSystem.enableDepthTest();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!visible || browser == null) return super.mouseClicked(mouseX, mouseY, button);

        if (isMouseOverPhone(mouseX, mouseY)) {
            int bx = browserX(mouseX);
            int by = browserY(mouseY);
            browser.sendMouseMove(bx, by);
            browser.sendMousePress(bx, by, button);
            browser.setFocus(true);
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (visible && browser != null) {
            int bx = browserX(mouseX);
            int by = browserY(mouseY);
            browser.sendMouseRelease(bx, by, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        if (visible && browser != null) {
            int bx = browserX(mouseX);
            int by = browserY(mouseY);
            browser.sendMouseMove(bx, by);
        }
        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (visible && browser != null) {
            int bx = browserX(mouseX);
            int by = browserY(mouseY);
            browser.sendMouseMove(bx, by);
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollDelta) {
        if (visible && browser != null && isMouseOverPhone(mouseX, mouseY)) {
            int bx = browserX(mouseX);
            int by = browserY(mouseY);
            browser.sendMouseWheel(bx, by, scrollDelta, 0);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollDelta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (visible && browser != null) {
            browser.sendKeyPress(keyCode, scanCode, modifiers);
            browser.setFocus(true);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (visible && browser != null) {
            browser.sendKeyRelease(keyCode, scanCode, modifiers);
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (visible && browser != null && codePoint != (char) 0) {
            browser.sendKeyTyped(codePoint, modifiers);
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static void setUrl(String url) {
        if (browser != null) {
            browser.loadURL(url);
        }
    }
}
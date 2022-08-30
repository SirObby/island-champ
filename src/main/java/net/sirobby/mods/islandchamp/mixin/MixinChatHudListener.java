package net.sirobby.mods.islandchamp.mixin;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.util.ChatMessages;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.math.MathHelper;
import net.sirobby.mods.islandchamp.IslandChamp;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(ChatHud.class)
public abstract class MixinChatHudListener {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private int scrolledLines;

    private final List<ChatHudLine.Visible> visibleMessagesIguess = Lists.newArrayList();

    @Shadow
    private boolean hasUnreadNewMessages;

    @Inject(method={"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V"}, at = @At("HEAD"), cancellable = true)
    public void onChatMessage(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) {
        if(IslandChamp.debugging_enabled) {
            System.out.println(message.toString());
        }

        /*ChatHudLine.Visible vis = new ChatHudLine.Visible(0, message.asOrderedText(), indicator, true);

        visibleMessagesIguess.add(vis);*/
        if(IslandChamp.sidechat_enabled) {
            if(/*Objects.equals(message.getStyle().getColor(), TextColor.fromRgb(0xFF7EFF)) && */Objects.equals(message.getSiblings().get(0).getString(), "[PM To] ") || Objects.equals(message.getSiblings().get(0).getString(), "[PM From] ")) {
                int i = MathHelper.floor((double) this.getWidth() / this.getChatScale());
                if (indicator != null && indicator.icon() != null) {
                    i -= indicator.icon().width + 4 + 2;
                }
                List<OrderedText> list = ChatMessages.breakRenderedChatMessageLines(message, i, this.client.textRenderer);
                boolean bl = this.isChatFocused();
                for (int j = 0; j < list.size(); ++j) {
                    OrderedText orderedText = list.get(j);
                    if (bl && this.scrolledLines > 0) {
                        this.hasUnreadNewMessages = true;
                        this.scroll(1);
                    }
                    boolean bl2 = j == list.size() - 1;
                    this.visibleMessagesIguess.add(0, new ChatHudLine.Visible(ticks, orderedText, indicator, bl2));
                }
                ci.cancel();
            }
        }
    }

    @Shadow
    protected abstract boolean isChatFocused();

    @Shadow
    public abstract int getVisibleLineCount();

    @Shadow
    protected abstract boolean isChatHidden();

    @Shadow
    public abstract double getChatScale();

    @Shadow
    public abstract int getWidth();

    @Shadow
    public abstract int getHeight();

    @Shadow
    protected abstract int getLineHeight();
    // Because I can't @Shadow this.
    private static double getMessageOpacityMultiplier(int age) {
        double d = (double)age / 200.0;
        d = 1.0 - d;
        d *= 10.0;
        d = MathHelper.clamp(d, 0.0, 1.0);
        d *= d;
        return d;
    }

    @Shadow
    protected abstract int getIndicatorX(ChatHudLine.Visible line);

    @Shadow
    protected abstract void drawIndicatorIcon(MatrixStack matrices, int x, int y, MessageIndicator.Icon icon);

    @Inject(method={"render"}, at = @At("HEAD"), cancellable = true)
    public void render(MatrixStack matrices, int currentTick, CallbackInfo ci) {
        renderChat(matrices, currentTick, IslandChamp.sidechat_x, 90, 90);
    }

    @Shadow
    public abstract void scroll(int scroll);

    @Inject(method={"clear"}, at = @At("HEAD"))
    public void clear(boolean clearHistory, CallbackInfo ci) {
        this.client.getMessageHandler().processAll();
        this.visibleMessagesIguess.clear();
        /*this.messages.clear();
        if (clearHistory) {
            this.messageHistory.clear();
        }*/
    }

    @SuppressWarnings("deprecation")
    private void renderChat(MatrixStack matrices, int currentTick,
                           int displayX, int width,
                           int scrolledLines) {

        int u;
        int t;
        int s;
        int r;
        int q;
        int o;

        if(isChatHidden()) return;

        int visibleLineCount = this.getVisibleLineCount();
        int renderedLines = 0;

        int visibleMessagesSize = visibleMessagesIguess.size();

        if (visibleMessagesSize <= 0) {
            return;
        }

        boolean bl = this.isChatFocused();
        float f = (float)this.getChatScale();

        int k = MathHelper.ceil((float)this.getWidth() / f);
        matrices.push();
        matrices.translate(displayX, 8.0, 0.0);
        matrices.scale(f, f, 1.0f);
        double d = this.client.options.getChatOpacity().getValue() * (double)0.9f + (double)0.1f;
        double e = this.client.options.getTextBackgroundOpacity().getValue();
        double g = this.client.options.getChatLineSpacing().getValue();

        int l = this.getLineHeight();
        double h = -8.0 * (g + 1.0) + 4.0 * g;
        int m = 0;

        for (int n = 0; n + this.scrolledLines < this.visibleMessagesIguess.size() && n < visibleLineCount; ++n) {
            ChatHudLine.Visible visible = this.visibleMessagesIguess.get(n + this.scrolledLines);
            if (visible == null || (o = currentTick - visible.addedTime()) >= 200 && !bl) continue;
            double p = bl ? 1.0 : getMessageOpacityMultiplier(o);
            q = (int)(255.0 * p * d);
            r = (int)(255.0 * p * e);
            ++m;
            if (q <= 3) continue;
            s = 0;
            t = -n * l;
            u = (int)((double)t + h);
            matrices.push();
            matrices.translate(displayX, 0.0, 50.0);
            ChatHud.fill(matrices, -4, t - l, 0 + k + 4 + 4, t, r << 24);
            MessageIndicator messageIndicator = visible.indicator();
            if (messageIndicator != null) {
                int v = messageIndicator.indicatorColor() | q << 24;
                ChatHud.fill(matrices, -4, t - l, -2, t, v);
                if (bl && visible.endOfEntry() && messageIndicator.icon() != null) {
                    int w = this.getIndicatorX(visible);
                    int x = u + this.client.textRenderer.fontHeight;
                    this.drawIndicatorIcon(matrices, w, x, messageIndicator.icon());
                }
            }
            RenderSystem.enableBlend();
            matrices.translate(0.0, 0.0, 50.0);
            this.client.textRenderer.drawWithShadow(matrices, visible.content(), 0.0f, (float)u, 0xFFFFFF + (q << 24));
            RenderSystem.disableBlend();
            matrices.pop();
        }

        long y = this.client.getMessageHandler().getUnprocessedMessageCount();

        if (y > 0L) {
            o = (int)(128.0 * d);
            int z = (int)(255.0 * e);
            matrices.push();
            matrices.translate(0.0, 0.0, 50.0);
            ChatHud.fill(matrices, -2, 0, k + 4, 9, z << 24);
            RenderSystem.enableBlend();
            matrices.translate(0.0, 0.0, 50.0);
            this.client.textRenderer.drawWithShadow(matrices, Text.translatable("chat.queue", y), 0.0f, 1.0f, 0xFFFFFF + (o << 24));
            matrices.pop();
            RenderSystem.disableBlend();
        }
        if (bl) {
            o = this.getLineHeight();
            int z = visibleMessagesSize * o;
            int aa = m * o;
            q = this.scrolledLines * aa / visibleMessagesSize;
            r = aa * aa / z;
            if (z != aa) {
                s = q > 0 ? 170 : 96;
                t = this.hasUnreadNewMessages ? 0xCC3333 : 0x3333AA;
                u = k + 4;
                ChatHud.fill(matrices, u, -q, u + 2, -q - r, t + (s << 24));
                ChatHud.fill(matrices, u + 2, -q, u + 1, -q - r, 0xCCCCCC + (s << 24));
            }
        }
        matrices.pop();

    }

}

package com.weep3rdev;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

@Singleton
public class ArmorReaderOverlay extends Overlay
{
    private final ArmorReaderPlugin plugin;


    @Inject
    private ArmorReaderOverlay(ArmorReaderPlugin plugin)
    {
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        String armorName = plugin.getCurrentArmor();
        String slotName = plugin.getSelectedSlot().getDisplayName();

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 14));
        graphics.drawString(slotName + ": " + armorName, 10, 20);

        return null;
    }
}
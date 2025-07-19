package com.weep3rdev;

@Singleton
public class TickTrackerOverlay extends Overlay
{
    private final ArmorReaderPlugin plugin;

    @Inject
    private TickTrackerOverlay(ArmorReaderPlugin plugin)
    {
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        String helmetName = plugin.getCurrentHelmet();

        // Set text properties
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 14));

        // Draw the text
        graphics.drawString("Helmet: " + helmetName, 10, 20);

        return null;
    }
}
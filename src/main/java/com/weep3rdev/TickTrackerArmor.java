package com.weep3rdev;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Helmet Reader",
	description = "Shows equipped helmet in an overlay"
)
public class TickTracker extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TickTrackerOverlay overlay;

	@Inject
	private ExampleConfig config;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

	public String getCurrentHelmet()
	{
		ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
		if (equipment == null)
		{
			return "No equipment data";
		}

		Item helmet = equipment.getItems()[EquipmentInventorySlot.HEAD.getSlotIdx()];

		if (helmet.getId() == -1)
		{
			return "No helmet";
		}

		return client.getItemDefinition(helmet.getId()).getName();
	}
}

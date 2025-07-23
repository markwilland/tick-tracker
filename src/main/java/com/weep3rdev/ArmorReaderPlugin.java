package com.weep3rdev;

import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.client.ui.overlay.OverlayManager;


@PluginDescriptor(
	name = "Armor Reader",
	description = "Shows equipped armor in an overlay",
	configName = "armorreaderconfig"
)
public class ArmorReaderPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ArmorReaderOverlay overlay;

	@Inject
	private ArmorReaderConfig config;

	@Inject
	private EventBus eventBus;

	private ArmorSlot lastSelectedSlot = null;
	private int lastItemId = -1;
	private int changeCounter = -1;

	@Override
	protected void startUp() {
		overlayManager.add(overlay);
		eventBus.register(this);

		lastSelectedSlot = config.armorSlot();
		lastItemId = getCurrentItemId();
		changeCounter = 0;
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
		eventBus.unregister(this);
	}

	@Provides
	ArmorReaderConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ArmorReaderConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		ArmorSlot currentSlot = config.armorSlot();
		int currentItemId = getCurrentItemId();

		if (lastSelectedSlot != currentSlot)
		{
			changeCounter = 0;
			lastSelectedSlot = currentSlot;
			lastItemId = currentItemId;
			return;
		}

		if (lastItemId != currentItemId)
		{
			changeCounter++;
			lastItemId = currentItemId;
		}
	}

	private int getCurrentItemId()
	{
		ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
		if (equipment == null)
		{
			return -1;
		}

		ArmorSlot selectedSlot = config.armorSlot();
		Item item = equipment.getItems()[selectedSlot.getSlot().getSlotIdx()];
		return item.getId();
	}

	public String getCurrentArmor() {

		ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);

		if (equipment == null) {
			return "No equipment data";
		}

		ArmorSlot selectedSlot = config.armorSlot();

		Item item = equipment.getItems()[selectedSlot.getSlot().getSlotIdx()];

		if (item.getId() == -1) {
			return "No " + selectedSlot.getDisplayName().toLowerCase();
		}

		return client.getItemDefinition(item.getId()).getName();
	}

	public ArmorSlot getSelectedSlot()
	{
		return config.armorSlot();
	}

	public int getChangeCounter()
	{
		return changeCounter;
	}
}
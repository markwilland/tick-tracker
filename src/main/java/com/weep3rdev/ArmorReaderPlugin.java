package com.weep3rdev;

import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
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

	@Override
	protected void startUp() {
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
	}

	@Provides
	ArmorReaderConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ArmorReaderConfig.class);
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
}
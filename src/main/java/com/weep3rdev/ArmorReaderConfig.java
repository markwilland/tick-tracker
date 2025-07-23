package com.weep3rdev;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("armorreaderconfig")
public interface ArmorReaderConfig extends Config
{
	@ConfigItem(
		keyName = "armorSlot",
		name = "Armor Slot",
		description = "Select which armor to display"
	)
	default ArmorSlot armorSlot()
	{
		return ArmorSlot.HEAD;
	}
}

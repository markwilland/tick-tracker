package com.weep3rdev;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ArmorReaderTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ArmorReaderPlugin.class);
		RuneLite.main(args);
	}
}
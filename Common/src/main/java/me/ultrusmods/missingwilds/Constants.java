package me.ultrusmods.missingwilds;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "missingwilds";
	public static final String MOD_NAME = "Missing Wilds";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static ResourceLocation id(String id) {
		return new ResourceLocation(MOD_ID, id);
	}
}
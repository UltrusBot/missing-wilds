package me.ultrusmods.missingwilds.resource;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsFabric;
import me.ultrusmods.missingwilds.compat.JsonDefinedModCompatInstance;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;

public class MissingWildsFabricResources {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(Constants.id("resources"));
	public static void init() {
		RRPCallback.AFTER_VANILLA.register(a -> a.add(RESOURCE_PACK));
		MissingWildsFabric.FABRIC_MOD_COMPAT_HANDLER.getModCompats().forEach(modCompatInstance -> {
			if (modCompatInstance instanceof JsonDefinedModCompatInstance jsonDefinedModCompatInstance) {
				jsonDefinedModCompatInstance.generateAssets(RESOURCE_PACK::addResource);
			}
		});
		JsonDefinedModCompatInstance.generateFallenLogTags(RESOURCE_PACK::addResource);

	}
}

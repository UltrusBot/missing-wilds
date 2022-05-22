package me.ultrusmods.missingwilds;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class MissingWildsPrelaunch implements PreLaunchEntrypoint {

	@Override
	public void onPreLaunch() {
		MixinExtrasBootstrap.init();
	}
}

package net.dianacraft.democracy;

import net.dianacraft.democracy.command.GimmickCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemocracyLife implements ModInitializer {
	public static final String MOD_ID = "democracy";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initialising Choose Your Life!");

		CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> {
			// Registering /skin reload
			GimmickCommand.register(dispatcher);
		});
	}
}
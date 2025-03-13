package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompatClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

import static me.ultrusmods.missingwilds.client.MissingWildsClientCommon.CLIENT_COMPATS;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class MissingWildsNeoForgeEvents {
    
    @SubscribeEvent
    public static void onTagsUpdated(TagsUpdatedEvent event) {
        if (event.getUpdateCause() == TagsUpdatedEvent.UpdateCause.CLIENT_PACKET_RECEIVED) {
            CLIENT_COMPATS.forEach(ModCompatClient::onTagLoad);
        }
    }
}

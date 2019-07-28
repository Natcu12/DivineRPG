package naturix.divinerpg.events;

import naturix.divinerpg.capabilities.ArcanaProvider;
import naturix.divinerpg.capabilities.IArcana;
import naturix.divinerpg.client.ArcanaHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ArcanaTickHandler {
	IArcana arcana;
	@SubscribeEvent
	public void onTick(PlayerTickEvent event){
		arcana = event.player.getCapability(ArcanaProvider.ARCANA_CAP, null);
		if(event.phase == Phase.START){
			onTickStart(event.player);
		} else {
			onTickEnd(event.player);
		}
	}

	private void onTickEnd(EntityPlayer player) {
		arcana = player.getCapability(ArcanaProvider.ARCANA_CAP, null);
		arcana.updateAllBars();
	}

	private void onTickStart(EntityPlayer player) {
		arcana = player.getCapability(ArcanaProvider.ARCANA_CAP, null);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){ 
		arcana = event.player.getCapability(ArcanaProvider.ARCANA_CAP, null);
		arcana.set(0);
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event){ 
		arcana = event.player.getCapability(ArcanaProvider.ARCANA_CAP, null);
		arcana.set(0);
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){ 
		arcana = event.player.getCapability(ArcanaProvider.ARCANA_CAP, null);
		arcana.set(200);
	}
	
	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event){
		arcana = event.player.getCapability(ArcanaProvider.ARCANA_CAP, null);
		arcana.set(200);
		
		event.player.addExperienceLevel(0);
	}
}

package divinerpg.events;

import divinerpg.Reference;
import divinerpg.objects.entities.assets.model.ModelHat;
import divinerpg.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
	public class EventDevHat {
		
	private ModelHat hat = new ModelHat();
	
	@SubscribeEvent
	public void playerRender(RenderPlayerEvent.Post evt) {
		//FIXME - has no rotation based on player head. i attempted stuff but failed - Nico
        if (Utils.isDeveloperName(evt.getEntityPlayer().getDisplayNameString())) {
			GL11.glPushMatrix();
			float height = evt.getEntity().height;
			GL11.glTranslatef(-0.5f, height, -0.5f);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("divinerpg:textures/model/devhats/hat_red.png"));
			hat.renderAll();
			GL11.glPopMatrix();
		}
	}
}

package naturix.divinerpg.objects.entities.entity.twilight;

import com.google.common.base.Predicate;

import naturix.divinerpg.enums.BulletType;
import naturix.divinerpg.objects.entities.entity.EntityDivineRPGMob;
import naturix.divinerpg.objects.entities.entity.projectiles.EntityTwilightMageShot;
import naturix.divinerpg.registry.ModSounds;
import naturix.divinerpg.utils.Reference;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Mystic extends EntityDivineRPGMob {
    public static final ResourceLocation LOOT = new ResourceLocation(Reference.MODID, "entities/twilight/mystic");

    public Mystic(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 2F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(2,
                new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, (Predicate) null));
    }

    @Override
    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted % 10 == 0) {
            this.attackingPlayer = this.world.getNearestAttackablePlayer(this, 16D, 16D);
            if (this.attackingPlayer != null && !this.world.isRemote) {
                double tx = this.attackingPlayer.posX - this.posX;
                double ty = this.attackingPlayer.getEntityBoundingBox().minY - this.posY;
                double tz = this.attackingPlayer.posZ - this.posZ;
                EntityTwilightMageShot shot = new EntityTwilightMageShot(this.world, this, BulletType.MYSTIC_SHOT);
                shot.shoot(tx, ty, tz, 1.6f, 0);
                this.world.spawnEntity(shot);
                this.world.playSound((EntityPlayer) null, this.attackingPlayer.posX, this.attackingPlayer.posY,
                        this.attackingPlayer.posZ, ModSounds.MAGE_FIRE, SoundCategory.HOSTILE, 1.0F, 1.0F);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.INSECT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.INSECT;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return this.LOOT;
    }
}
package divinerpg.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SoulTrapCountProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<SoulTrapCount> SOUL_TRAP_COUNT = CapabilityManager.get(new CapabilityToken<>(){});
    private SoulTrapCount counter = null;
    private final LazyOptional<SoulTrapCount> opt = LazyOptional.of(this::createCounter);
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return opt.cast();
	}
	private SoulTrapCount createCounter() {
		if(counter == null) counter = new SoulTrapCount();
		return counter;
	}
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		if(counter != null) tag.putInt("count", counter.count);
		return tag;
	}
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		if(nbt.contains("count")) {
			int count = nbt.getInt("count");
			if(counter == null) counter = new SoulTrapCount(count);
			else counter.count = count;
		}
	}
}
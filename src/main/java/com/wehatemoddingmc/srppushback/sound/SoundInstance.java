package com.wehatemoddingmc.srppushback.sound;

import net.minecraft.client.audio.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nullable;

public class SoundInstance extends MovingSound {
    private boolean done = false;

    public SoundInstance(SoundEvent sound) {
        super(sound, SoundCategory.PLAYERS);
        this.repeat = false;
        this.repeatDelay = 0;
        this.volume = 1.0f;
        this.xPosF = this.yPosF = this.zPosF = 0f;
        this.attenuationType = AttenuationType.NONE;
    }

    @Override
    public void update() {
        if (done) {
            this.donePlaying = true;
            this.repeat = false;
        }
    }

    public void stopSound() {
        this.done = true;
    }
}


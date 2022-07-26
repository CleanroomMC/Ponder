package com.cleanroommc.ponder.util;

import net.minecraft.nbt.NBTTagCompound;

// Can replace all Interpolated value classes
// InterpolatedChasingValue, InterpolatedValue, InterpolatedChasingAngle, InterpolatedAngle
public class LerpedFloat {

    protected Interpolator interpolator;
    protected float previousValue;
    protected float value;

    protected Chaser chaseFunction;
    protected float chaseTarget;
    protected float chaseSpeed;

    protected boolean forcedSync;

    public LerpedFloat(Interpolator interpolator) {
        this.interpolator = interpolator;
        startWithValue(0);
        forcedSync = true;
    }

    public static LerpedFloat linear() {
        return new LerpedFloat((p, c, t) -> (float) MathUtil.lerp(p, c, t));
    }

    public static LerpedFloat angular() {
        return new LerpedFloat(AngleHelper::angleLerp);
    }

    public LerpedFloat startWithValue(double value) {
        float f = (float) value;
        this.previousValue = f;
        this.chaseTarget = f;
        this.value = f;
        return this;
    }

    public LerpedFloat chase(double value, double speed, Chaser chaseFunction) {
        this.chaseTarget = (float) value;
        this.chaseSpeed = (float) speed;
        this.chaseFunction = chaseFunction;
        return this;
    }

    public void updateChaseTarget(float target) {
        this.chaseTarget = target;
    }

    public boolean updateChaseSpeed(double speed) {
        float prevSpeed = this.chaseSpeed;
        this.chaseSpeed = (float) speed;
        return !MathUtil.equal(prevSpeed, speed);
    }

    public void tickChaser() {
        previousValue = value;
        if (chaseFunction == null)
            return;
        if (MathUtil.equal((double) value, chaseTarget)) {
            value = chaseTarget;
            return;
        }
        value = chaseFunction.chase(value, chaseSpeed, chaseTarget);
    }

    public void setValue(double value) {
        this.previousValue = this.value;
        this.value = (float) value;
    }

    public float getValue() {
        return getValue(1);
    }

    public float getValue(float partialTicks) {
        return MathUtil.lerp(partialTicks, previousValue, value);
    }

    public boolean settled() {
        return MathUtil.equal((double) previousValue, value);
    }

    public float getChaseTarget() {
        return chaseTarget;
    }

    public void forceNextSync() {
        forcedSync = true;
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound compoundNBT = new NBTTagCompound();
        compoundNBT.setFloat("Speed", chaseSpeed);
        compoundNBT.setFloat("Target", chaseTarget);
        compoundNBT.setFloat("Value", value);
        if (forcedSync) {
            compoundNBT.setBoolean("Force", true);
        }
        forcedSync = false;
        return compoundNBT;
    }

    public void readNBT(NBTTagCompound compoundNBT, boolean clientPacket) {
        if (!clientPacket || compoundNBT.hasKey("Force")) {
            startWithValue(compoundNBT.getFloat("Value"));
        }
        readChaser(compoundNBT);
    }

    protected void readChaser(NBTTagCompound compoundNBT) {
        chaseSpeed = compoundNBT.getFloat("Speed");
        chaseTarget = compoundNBT.getFloat("Target");
    }

    @FunctionalInterface
    public interface Interpolator {
        float interpolate(double progress, double current, double target);
    }

    @FunctionalInterface
    public interface Chaser {

        Chaser IDLE = (c, s, t) -> (float) c;
        Chaser EXP = exp(Double.MAX_VALUE);
        Chaser LINEAR = (c, s, t) -> (float) (c + MathUtil.clamp(t - c, -s, s));

        static Chaser exp(double maxEffectiveSpeed) {
            return (c, s, t) -> (float) (c + MathUtil.clamp((t - c) * s, -maxEffectiveSpeed, maxEffectiveSpeed));
        }

        float chase(double current, double speed, double target);
    }

}


package com.cleanroommc.ponder.util;


import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;

public class AngleHelper {

    public static float horizontalAngle(EnumFacing facing) {
        if (facing.getAxis().isVertical()) {
            return 0;
        }
        float angle = facing.getHorizontalAngle();
        if (facing.getAxis() == Axis.X) {
            angle = -angle;
        }
        return angle;
    }

    public static float verticalAngle(EnumFacing facing) {
        return facing == EnumFacing.UP ? -90 : facing == EnumFacing.DOWN ? 90 : 0;
    }

    public static float rad(double angle) {
        return angle == 0 ? 0 : (float) (angle / 180 * Math.PI);
    }

    public static float deg(double angle) {
        return angle == 0 ? 0 : (float) (angle * 180 / Math.PI);
    }

    public static float angleLerp(double pct, double current, double target) {
        return (float) (current + getShortestAngleDiff(current, target) * pct);
    }

    public static float getShortestAngleDiff(double current, double target) {
        current = current % 360;
        target = target % 360;
        return (float) (((((target - current) % 360) + 540) % 360) - 180);
    }

    public static float getShortestAngleDiff(double current, double target, float hint) {
        float diff = getShortestAngleDiff(current, target);
        if (MathUtil.equal(Math.abs(diff), 180) && Math.signum(diff) != Math.signum(hint)) {
            return diff + 360 * Math.signum(hint);
        }
        return diff;
    }

}


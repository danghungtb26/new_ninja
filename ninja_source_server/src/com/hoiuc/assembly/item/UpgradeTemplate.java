package com.hoiuc.assembly.item;

import com.hoiuc.assembly.Option;
import com.hoiuc.io.Util;

public class UpgradeTemplate {
    public static double[] increasePer = new double[] { 1.05, 1.142857143, 1.083333333, 1.076923077, 1.071428571,
            1.066666667, 1.0625, 1.058823529, 1.055555556, 1.105263158, 1.095238095, 1.086956522, 1.2, 1.166666667,
            1.142857143, 1.25
    };

    public static int[] percen = new int[] { 70, 60, 50, 40, 35, 30, 25, 20, 15, 12, 12, 10, 7, 5, 4,
            4 };

    public static boolean shouldUpgrade(int upgrade, boolean vip) {
        if (Util.isDebug()) {
            return true;
        }
        int upPer = (int) (percen[upgrade] * (vip ? 1.2 : 1));
        if (!vip && upgrade > 8) {
            upPer = -1;
        }

        return Util.nextInt(150) < upPer && upPer != -1;
    }

    public static Option upgradeOption(int id, int param, byte upgrade) {
        if (param < 10) {
            return new Option(id, param + 1);
        }

        return new Option(id, (int) Math.round(param * increasePer[upgrade - 1]));
    }

    public static int daNangCap() {
        return 682;
    }
}

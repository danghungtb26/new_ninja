package com.hoiuc.assembly.item;

import com.hoiuc.io.Util;

public class Random {

    public static int[] chisoNormal = new int[] { 4, 2, 3, 82, 83, 119, 120, 100 };

    public static int[] chisoVip = new int[] { 87, 88, 89, 90, 84, 86, 94, 92, 1, 0, 57, 58 };


    public static int random(int id) {
        switch (id) {
            // hồi mp
            case 119:

                return (int) Util.nextInt(50, 100);
            // hồi hp
            case 120:
                return (int) Util.nextInt(50, 100);

            // kinh nghiệm đánh quái
            case 100:
                return (int) Util.nextInt(8, 10);

            // tấn công
            case 87:
            case 88:
            case 89:
            case 90:
                return (int) Util.nextInt(700, 1000);

            // né đòn
            // chí mạng
            // chính xác
            case 84:
            case 86:
            case 92:
                return (int) Util.nextInt(15,20);

            // mp, hp
            case 82:
            case 83:
                return (int) Util.nextInt(700, 1000);

            // kháng
            case 2:
            case 3:
            case 4:

                return (int) Util.nextInt(20, 100);

            // tấn công nôi ngoại
            case 0:
            case 1:
                return (int) Util.nextInt(700, 1000);

            case 9:
            case 8:
                return (int) Util.nextInt(5, 10);
            // + tiềm năng
            case 57:
                return (int) Util.nextInt(10, 20);
            // % tiềm năng
            case 58:
                return (int) Util.nextInt(5, 10);
            case 94: 
                return (int) Util.nextInt(10, 20); 
            default:
                return 0;
        }
    }
}

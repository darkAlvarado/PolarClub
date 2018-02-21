package com.club.jalvara2.polarclub2.utils;

/**
 * Created by jalvara2 on 7/02/18.
 */

public class DailyHeart {
    private static int hearbeat;
    private static int moyenne;

    public static void updateHearbeat(int h) {
        hearbeat = h;
    }

    public static int getHearbeat() {
        return hearbeat;
    }

    public static void updateMoyenne(int m){
        moyenne = m;
    }

    public static int getMoyenne(){
        return moyenne;
    }

}

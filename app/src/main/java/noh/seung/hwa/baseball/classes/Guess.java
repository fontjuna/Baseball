package noh.seung.hwa.baseball.classes;

import java.util.Random;

/**
 * Created by fontjuna on 2017-09-05.
 */

public class Guess {
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    private int[] count = {0, 0, 0};

    public Guess() {
        makeNewcount();
    }

    private void makeNewcount() {
        Random random = new Random();

        count[LEFT] = 0;
        count[CENTER] = 0;
        count[RIGHT] = 0;
        while (count[LEFT] == count[CENTER] || count[CENTER] == count[RIGHT] || count[LEFT] == count[RIGHT]) {
            count[LEFT] = random.nextInt(10);
            count[CENTER] = random.nextInt(10);
            count[RIGHT] = random.nextInt(10);
        }
    }

    public int getCount() {
        return count[LEFT] * 100 + count[CENTER] * 10 + count[RIGHT];
    }

    public int getCount(int position) {
        return count[position];
    }

    public String getJudge(String value) {
        if (value.isEmpty() || value.length() != 3) {
            return "error";
        }
        int[] intVal = {0, 0, 0};
        intVal[0] = Integer.parseInt(value.substring(0, 1));
        intVal[1] = Integer.parseInt(value.substring(1, 2));
        intVal[2] = Integer.parseInt(value.substring(2, 3));
        return getJudge(intVal);
    }

    public String getJudge(int[] value) {
        if (value.length != 3) {
            return "error";
        }
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < value.length; j++) {
                if (count[i] == value[j]) {
                    if (i == j) {
                        strike++;
                    } else {
                        ball++;
                    }
                }
            }
        }
        return strike == 3 ? "Success!" : strike + " s, " + ball + " b";
    }
}

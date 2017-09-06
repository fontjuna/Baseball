package noh.seung.hwa.baseball.testers;

import java.util.Scanner;

import noh.seung.hwa.baseball.classes.Guess;

/**
 * Created by fontjuna on 2017-09-05.
 */

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] value = {0, 0, 0};

        Guess guess = new Guess();
        System.out.println(guess.getCount());

        while (true) {
            for (int i = 0; i < 3; i++) {
                value[i] = sc.nextInt();
            }

            System.out.println(guess.getJudge(value));
        }
    }
}

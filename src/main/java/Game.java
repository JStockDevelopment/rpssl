package main.java;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Game {
    private static final int TIMES_TO_PLAY = 10000;


    public static void main(String[] args) {
        FileDB db = new FileDB();
        Map<GameOption, Integer> wins = new HashMap<>();
        Map<GameOption, Integer> losses = new HashMap<>();

        db.loadWinsAndLossesFromDB(wins, losses);

        System.out.println("Playing rock, paper, scissors, lizard, spock " + String.format("%,d", TIMES_TO_PLAY) + " times. \nPrinting the win percentage");
        for(int i = 0; i < TIMES_TO_PLAY; i++) {
            GameOption playerOne = getGameOption();
            GameOption playerTwo = getGameOption();
            if(playerOne.beats(playerTwo)) {
                incrementWinLoss(wins, playerOne);
                incrementWinLoss(losses, playerTwo);
            }
            else if(playerTwo.beats(playerOne)){
                incrementWinLoss(wins, playerTwo);
                incrementWinLoss(losses, playerOne);
            }
        }


        for(GameOption gameOption : GameOption.values())
            printWinPercentage(gameOption.name(), wins.get(gameOption), losses.get(gameOption));

        db.saveWinsAndLosses(wins, losses);
    }

    public static void printWinPercentage(String name, float wins, float losses)
    {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        System.out.println(name + ": " + df.format((wins / (losses + wins)) * 100) + "%");

    }

    public static GameOption getGameOption()
    {
        int pick = new Random().nextInt(GameOption.values().length);
        return GameOption.values()[pick];
    }

    public static void incrementWinLoss(Map<GameOption, Integer> map, GameOption key)
    {
        Integer currentValue = map.computeIfAbsent(key, t -> 0);
        map.put(key, currentValue + 1);
    }






}

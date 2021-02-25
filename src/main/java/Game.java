package main.java;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {

    public static void main(String[] args) {
        Map<GameOption, Integer> wins = new HashMap<>();
        Map<GameOption, Integer> losses = new HashMap<>();
        for(int i = 0; i < 10000; i++) {
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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        for(GameOption gameOption : GameOption.values())
        {
            float optionWins = wins.get(gameOption);
            float optionLosses = losses.get(gameOption);
            System.out.println(gameOption.name() + ": " + df.format((optionWins / (optionLosses + optionWins)) * 100) + "%");
        }
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

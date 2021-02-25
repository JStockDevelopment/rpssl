package main.java;

import java.io.*;
import java.util.*;

public class FileDB {

    private static final String WINS_DB = "wins.txt";
    private static final String LOSSES_DB = "losses.txt";
    public void saveWinsAndLosses(Map<GameOption, Integer> wins, Map<GameOption, Integer> losses)
    {
        writeFile(WINS_DB, wins);
        writeFile(LOSSES_DB, losses);
    }

    public void loadWinsAndLossesFromDB(Map<GameOption, Integer> wins, Map<GameOption, Integer> losses)
    {
        loadMap(WINS_DB, wins);
        loadMap(LOSSES_DB, losses);
    }

    private static void writeFile(String fileName, Map<GameOption, Integer> map)
    {
        try {
            FileWriter writer = new FileWriter(fileName);
            for(GameOption option : map.keySet())
                writer.write(option.name() + "," + map.get(option) + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String fileName, Map<GameOption, Integer> map)
    {
        Map<String, GameOption> mapOfOptions = getGameOptionMap();

        File tempFile = new File(fileName);
        if(tempFile.exists())
        {
            try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {

                String line;
                while((line = br.readLine()) != null)
                {
                    if(line.contains(","))
                    {
                        String[] values = line.split(",");
                        if(values.length == 2)
                            map.put(mapOfOptions.get(values[0]), Integer.parseInt(values[1]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, GameOption> getGameOptionMap()
    {
        Set<GameOption> gameOptions = new HashSet<>(Arrays.asList(GameOption.values()));
        Map<String, GameOption> mapOfOptions = new HashMap<>();

        for(GameOption option : gameOptions)
            mapOfOptions.put(option.name(), option);
        return mapOfOptions;
    }
}

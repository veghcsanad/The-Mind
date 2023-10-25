package main.java.model;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataRecorder {
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> currentData;
    private LocalDateTime lastCardPlayed;

    public DataRecorder() {
        this.data = new ArrayList<>();
        this.currentData = new ArrayList<>();
    }

    public void resetTime() {
        lastCardPlayed = LocalDateTime.now();
    }

    public void startNewGame(int numGame, ArrayList<Card> handP, ArrayList<Card> handC) {
        if (currentData != null) {
            currentData.clear();
        }
        currentData.add("Game " + numGame);
        currentData.add("Hand Participant: " + handP.toString());
        currentData.add("Hand Computer: " + handC.toString());
        resetTime();
    }

    public void cardPlayed(Player player, Card card) {
        Duration waitingTime = Duration.between(lastCardPlayed, LocalDateTime.now());
        if (player.getIsHuman()) {
            currentData.add("Participant played " + card.getNumber() + ". Time passed: " + String.valueOf(waitingTime.toMillis()));
        } else {
            currentData.add("Computer played " + card.getNumber() + ". Time passed: " + String.valueOf(waitingTime.toMillis()));
        }
        lastCardPlayed = LocalDateTime.now();
    }

    public void recordGame(Game game) {
        if (game.isGameWon()) {
            currentData.add("WON");
        } else {
            currentData.add("LOST");
        }
        ArrayList<String> copyList = new ArrayList<>();
        copyList.addAll(currentData);
        data.add(copyList);
    }

    public void recordExperiment() {

        // Get the current date and time as a string
        String timestamp = LocalDateTime.now().toString();

        // Replace colons (:) and periods (.) in the timestamp to make it a valid file name
        timestamp = timestamp.replace(":", "-").replace(".", "_");

        // Create the file name with the folder path and timestamp
        String fileName = "experimentData_" + timestamp + ".txt";

        // Write data to the file
        writeArrayListToFile(data, fileName);
    }

    public static void writeArrayListToFile(ArrayList<ArrayList<String>> list, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write text to the file
            for (ArrayList<String> arrayGames : list) {
                for (String datapoint : arrayGames) {
                    writer.write(datapoint);
                    writer.newLine();
                }
                writer.write("-----------------------");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

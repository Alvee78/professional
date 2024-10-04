import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {

//		Check arguments
        if (args == null || args.length != 1) {
            System.out.println("Usage: (a | r | c | +WORD | ?WORD)");
            return;
        }

        String fileContents = readFileContents("students.txt");
        System.out.println("Loading data ...");

        if (args[0].equals("a")) {
            String words[] = fileContents.split(",");
            for (String word : words) {
                System.out.println(word);
            }
        } else if (args[0].equals("r")) {
            String words[] = fileContents.split(",");
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(words.length);
            System.out.println(words[randomNumber]);
        } else if (args[0].contains("+")) {
            String word = args[0].substring(1);
            Date date = new Date();
            String dateFormatSample = "dd/mm/yyyy-hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(dateFormatSample);
            String formatedDate = dateFormat.format(date);
            writeToFile("students.txt", ", " + word + "\nList last updated on " + formatedDate);
        } else if (args[0].contains("?")) {
            String words[] = fileContents.split(",");
            boolean done = false;
            String word = args[0].substring(1);
            for (int idx = 0; idx < words.length && !done; idx++) {
                if (words[idx].equals(word)) {
                    System.out.println("We found it!");
                    done = true;
                }
            }
        } else if (args[0].contains("c")) {
            char charArray[] = fileContents.toCharArray();
            boolean in_word = false;
            int count = 0;
            for (char character : charArray) {
                if (character == ' ') {
                    if (!in_word) {
                        count++;
                        in_word = true;
                    } else {
                        in_word = false;
                    }
                }
            }
            System.out.println(count + " word(s) found " + charArray.length);
        }

        System.out.println("Data Loaded.");
    }

    public static String readFileContents(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileName)));
            String fileContents = bufferedReader.readLine();
            bufferedReader.close();
            return fileContents;
        } catch (Exception e) {
            return null;
        }
    }

    public static void writeToFile(String fileName, String data) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(fileName, true));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {

//		Check arguments
        if (args == null || args.length != 1) {
            System.out.println(Constants.INPUT_ARG);
            return;
        }

        String fileContents = readFileContents(Constants.FILE_NAME);
        System.out.println(Constants.LOADING_DATA_TEXT);

        if (args[0].equals(Constants.SHOW_ALL)) {
            String words[] = fileContents.split(Constants.STUDENT_ENTRY_DELIMITER);
            for (String word : words) {
                System.out.println(word);
            }
        } else if (args[0].equals(Constants.SHOW_RANDOM)) {
            String words[] = fileContents.split(Constants.STUDENT_ENTRY_DELIMITER);
            int randomNumber = new Random().nextInt(words.length);
            System.out.println(words[randomNumber]);
        } else if (args[0].contains(Constants.ADD_ENTRY)) {
            String word = args[0].substring(1);
            String formatedDate = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
            writeToFile(Constants.FILE_NAME, ", " + word + "\nList last updated on " + formatedDate);
        } else if (args[0].contains(Constants.FIND_ENTRY)) {
            String words[] = fileContents.split(Constants.STUDENT_ENTRY_DELIMITER);
            boolean done = false;
            String word = args[0].substring(1);
            for (int idx = 0; idx < words.length && !done; idx++) {
                if (words[idx].equals(word)) {
                    System.out.println(Constants.FOUND_TEXT);
                    done = true;
                }
            }
        } else if (args[0].contains(Constants.SHOW_COUNT)) {
            char charArray[] = fileContents.toCharArray();
            boolean in_word = false;
            int count = 0;
            for (char character : charArray) {
                if (character == Constants.SPACE) {
                    if (!in_word) {
                        count++;
                        in_word = true;
                    } else {
                        in_word = false;
                    }
                }
            }
            System.out.println(count + Constants.WORDS_FOUND_TEXT);
        }

        System.out.println(Constants.DATA_LOADED_TEXT);
    }

    public static String readFileContents(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                                            new InputStreamReader(
                                            new FileInputStream(fileName)));
            return bufferedReader.readLine();
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
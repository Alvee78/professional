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
            String word = args[0].substring(1);
            int indexLocation = -1;
            for (int idx = 0; idx < words.length; idx++) {
                if ((words[idx].trim()).equals(word.trim())) {
                    indexLocation = idx;
                    break;
                }
            }
            if(indexLocation >= 0) {
                System.out.println("Entry " + word + " found at location : " + indexLocation);
            } else {
                System.out.println("Entry " + word + " does not exists");
            }

        } else if (args[0].contains(Constants.SHOW_COUNT)) {
            String[] words = fileContents.split(Constants.STUDENT_ENTRY_DELIMITER);
            System.out.println(words.length + Constants.WORDS_FOUND_TEXT);
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
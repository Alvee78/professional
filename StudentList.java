import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {

        //	Check arguments
        if (args == null || args.length != 1 ) {
            System.out.println(Constants.INPUT_ARG);
            return; //Early exit
        }
        // Every operation need to load data
        String studentList = loadStudentList(Constants.FILE_NAME); // Contains Student list
        System.out.println(Constants.LOADING_DATA_TEXT);

        if (args[0].equals(Constants.SHOW_ALL)) {
            String students[] = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            for (String student : students) {
                System.out.println(student);
            }
        } else if (args[0].equals(Constants.SHOW_RANDOM)) {
            String students[] = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            int randomNumber = new Random().nextInt(students.length);
            System.out.println(students[randomNumber]);

        } else if (args[0].contains(Constants.ADD_ENTRY)) {
            String studentEntry = args[0].substring(1);
            String formatedDate = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
            updateStudentList(Constants.FILE_NAME, ", " + studentEntry + "\nList last updated on " + formatedDate);

        } else if (args[0].contains(Constants.FIND_ENTRY)) {
            String students[] = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            String searchTerm = args[0].substring(1);
            int indexLocation = -1;
            for (int idx = 0; idx < students.length; idx++) {
                if ((students[idx].trim()).equals(searchTerm.trim())) {
                    indexLocation = idx;
                    break;
                }
            }
            //
            if(indexLocation >= 0) {
                System.out.println("Entry " + searchTerm + " found at location : " + indexLocation);
            } else {
                System.out.println("Entry " + searchTerm + " does not exists");
            }

        } else if (args[0].contains(Constants.SHOW_COUNT)) {
            String[] words = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            System.out.println(words.length + Constants.WORDS_FOUND_TEXT);
        } else {
            System.out.println("Invalid argument: " + args[0]);
            System.out.println(Constants.INPUT_ARG);
        }

        System.out.println(Constants.DATA_LOADED_TEXT);
    }
    //To load student list
    public static String loadStudentList(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                                            new InputStreamReader(
                                            new FileInputStream(fileName)));
            return bufferedReader.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    // To entry new student data
    public static void updateStudentList(String fileName, String data) {
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
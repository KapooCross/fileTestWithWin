package my.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileChecked {
    private static final int AMOUNT_QUESTION = 5;
    private int right = 0;
    private int wrong = 0;
    private final List<String> answerFromFile = new ArrayList<>();


    public void findAllFiles() throws FileNotFoundException {
        String separator = File.separator;

        //String path = separator + "E:" + separator + "Directory";
        String path = separator + "Z:" + separator + "testt";
        File directory = new File(path);

        if (directory.isDirectory()) {

            for (File item : Objects.requireNonNull(directory.listFiles())) {// проходим по папке с файлами
                Scanner scanner = new Scanner(item.getAbsoluteFile()); // найденный файл кладём в Scanner
                readNextLine(scanner); // считываем информацию с файла (метод ниже)
                System.out.println(item.getName()); // получаем название файла

                foundAnswer(); // метод, который находит правильные ответы(ниже описан)
                System.out.println("right: " + right + ", wrong: " + wrong);
                System.out.println(procentRight(right));
                answerFromFile.clear(); // так как мы храним сефчас данные в одном листе "answerFromFile",
                // то перед тем как снова зайти в цикл её нужно почистить
                right = 0;
                wrong = 0; // с правильными и неправлиьными тоже самое
            }
        }
    }
    public void readNextLine(Scanner scanner){
        while (scanner.hasNextLine()) { // здесь построчно считывается файл
            answerFromFile.add(scanner.nextLine()); //найденная информация сразу же помещается в коллекцию
        }
        scanner.close(); // scanner слудцет закрывать, не совсем понял почему, но нужно
    }

    public void foundAnswer(){ // сверяем построчно элементы наших rightAnswer()(просто хранит правлиьные ответы)
        // и answerFromFile(ответы, которые мы считали с файла)
        List<String> foundAnswer;
        //                for (int i = 0; i < answerFromFile.size(); i++) {
//                    System.out.println(i + " " + rightAnswer().get(i).equals(answerFromFile.get(i)));
//                }
        /*закоментированный цикл сверху делает то же самое, что и нижний,
        * но нижний работает быстрее, так как когда он находит значение, то выходит из цикла,
        * а верхний будет проходиться по каждому значению */

         for (String answer : rightAnswer()) {
            foundAnswer = answerFromFile.stream()
                    .filter(s -> s.equals(answer)).toList();
            System.out.println(!foundAnswer.isEmpty() ? right++ : wrong++);
        }
    }

    public List<String> rightAnswer() {
        List<String> question = new ArrayList<>();
        question.add("1. 2, 3, 6, 8");
        question.add("2. 2, 6");
        question.add("3. 2, 7");
        question.add("4. 1, 3");
        question.add("5. 1, 2");

        return question;
    }

    public String procentRight(int right) {
        return "procent right answer: " + (right * 100) / AMOUNT_QUESTION +
                "," + (right * 100) % AMOUNT_QUESTION;
    }
}
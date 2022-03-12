package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    public static int age = 0;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        //sentences
        String sentence = scanner.nextLine().replaceAll("\u00a0", " ").toLowerCase();
        String[] sentences = sentence.split("(?<=[!.?])");
        List<String> wordList;
        List<String> charList;

        int words = 0;
        int characters = 0;
        int syllables = 0;
        int polysyllables = 0;
        int vowels = 0;
        int letters = 0;
        boolean isVowel;
        for (String s : sentences) {

            String[] tempWords = s.split("\\s");
            wordList = new ArrayList<>(Arrays.asList(tempWords));
            wordList.removeAll(Arrays.asList("", null));
            words += wordList.size();

            for (String string : wordList) {
                String[] tempChar = string.split("");
                charList = new ArrayList<>(Arrays.asList(tempChar));
                characters += charList.size();
                charList.remove(".");
                charList.remove("!");
                charList.remove("?");
                charList.remove(",");
                letters += charList.size();

                for (int i = 0; i < charList.size() - 1; i++) {
                    if (charList.get(i).equals("a")
                            || charList.get(i).equals("e")
                            || charList.get(i).equals("i")
                            || charList.get(i).equals("o")
                            || charList.get(i).equals("u")
                            || charList.get(i).equals("y")) {
                        isVowel = true;
                        vowels++;
                    } else {
                        isVowel = false;
                    }
                    if (isVowel && (charList.get(i + 1).equals("a")
                            || charList.get(i + 1).equals("e")
                            || charList.get(i + 1).equals("i")
                            || charList.get(i + 1).equals("o")
                            || charList.get(i + 1).equals("u")
                            || charList.get(i + 1).equals("y"))) {
                        vowels--;
                    }
                }
                if (vowels == 0) {
                    vowels = 1;
                }
                if (vowels > 2) {
                    polysyllables++;
                }
                if (vowels > 0) {
                    syllables += vowels;
                    vowels = 0;
                }
            }
        }
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences.length);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);

        calculateScore(characters, words, sentences.length, syllables, polysyllables, letters);
        scanner.close();
    }

    public static void scoreLevel(double score, BigDecimal bigScore) {

        switch ((int) Math.round(score)) {
            case 1:
                setAge(6);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 2:
                setAge(7);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 3:
                setAge(9);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 4:
                setAge(10);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 5:
                setAge(11);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 6:
                setAge(12);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 7:
                setAge(13);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 8:
                setAge(14);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 9:
                setAge(15);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 10:
                setAge(16);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 11:
                setAge(17);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 12:
                setAge(18);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 13:
                setAge(19);
                System.out.println(bigScore + " (about " + age + " year olds).");
                break;
            case 14:
                setAge(24);
                System.out.println(bigScore + " (about " + age + "+ year olds).");
                break;
        }
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        Main.age = age;
    }

    public static void calculateScore(int characters, int words, double sentences, int syllables, int polysyllables, double letters) {
        Scanner scanner = new Scanner(System.in);
        double score;
        int ageSum = 0;
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String scoreType = scanner.nextLine();
        System.out.println();
        switch (scoreType) {
            case "ARI":
                score = (4.71 * characters / words + 0.5 * words / sentences - 21.43);
                BigDecimal bigDecimalScore = BigDecimal.valueOf(score);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Automated Readability Index: ");
                scoreLevel(score, bigDecimalScore);
                break;
            case "FK":
                score = (0.39 * words / sentences + 11.8 * syllables / words - 15.59);
                bigDecimalScore = BigDecimal.valueOf(score);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Flesch–Kincaid readability tests: ");
                scoreLevel(score, bigDecimalScore);
                break;
            case "SMOG":
                score = (1.043 * Math.sqrt(polysyllables * 30 / sentences) + 3.1291);
                bigDecimalScore = BigDecimal.valueOf(score);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Simple Measure of Gobbledygook: ");
                scoreLevel(score, bigDecimalScore);
                break;
            case "CL":
                score = (0.0588 * ((letters / words) * 100)) - (0.296 * ((sentences / words) * 100)) - 15.8;
                bigDecimalScore = BigDecimal.valueOf(score);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Coleman–Liau index: ");
                scoreLevel(score, bigDecimalScore);
                break;
            case "all":
                double scoreARI = (4.71 * characters / words + 0.5 * words / sentences - 21.43);
                bigDecimalScore = BigDecimal.valueOf(scoreARI);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Automated Readability Index: ");
                scoreLevel(scoreARI, bigDecimalScore);
                ageSum += getAge();
                double scoreFK = (0.39 * words / sentences + 11.8 * syllables / words - 15.59);
                bigDecimalScore = BigDecimal.valueOf(scoreFK);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Flesch–Kincaid readability tests: ");
                scoreLevel(scoreFK, bigDecimalScore);
                ageSum += getAge();
                double scoreSMOG = (1.043 * Math.sqrt(polysyllables * 30 / sentences) + 3.1291);
                bigDecimalScore = BigDecimal.valueOf(scoreSMOG);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Simple Measure of Gobbledygook: ");
                scoreLevel(scoreSMOG, bigDecimalScore);
                ageSum += getAge();
                double scoreCL = (0.0588 * ((letters / words) * 100) - 0.296 * ((sentences / words) * 100) - 15.8);
                bigDecimalScore = BigDecimal.valueOf(scoreCL);
                bigDecimalScore = bigDecimalScore.setScale(2, RoundingMode.DOWN);
                System.out.print("Coleman–Liau index: ");
                scoreLevel(Math.abs(scoreCL), bigDecimalScore);
                ageSum += getAge();
                System.out.println();
                System.out.println("This text should be understood in average by " + ageSum / 4 + " year olds.");
                break;
        }
    }
}
package Exe2Ver1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

//numbers do wczytania, zapisu danych i podzialu na wątki
//konkretne dzialania niech wykona oddzielna klasa Worker wewnętrzna względem Numbers
//Worker dostaje ref na tablicę, tab wynikową i zakres indeksów na których przeprowadza obliczenia
//iteruje po danych, wykonuje oper mat, zapisuje w tab wynikowej na takim samym indeksie
public class Numbers extends Thread {

    private double[] numbersList;
    private double[] newArray;
    private double[] finalArray;


    public Numbers(int arraySize) {
        numbersList = new double[arraySize];
        newArray = new double[arraySize];
        //randomize();
        finalArray = new double[arraySize];
    }

    public Numbers(int arraySize, int start, int end) {
        numbersList = new double[arraySize];
        newArray = new double[end - start];
        for (int i = start; i < end; i++) {
            newArray[i] = start;
        }
    }

    //czytanie liczb z pliku
    public void readNumbers(String fileName) {
        int i = 0;
        Scanner scanner = new Scanner("resources\\" + fileName);
        while (scanner.hasNextLine()) {
            String newLine = scanner.nextLine();
            numbersList[i] = Double.valueOf(newLine);
            i++;
        }
    }


    //losowanie liczb
    public void randomize(String fileName) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources\\" + fileName));
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
          //  double random = Math.random() * 100;

            numbersList[i] = random.nextDouble();
            bufferedWriter.write(String.valueOf(numbersList[i]));
        }
    }

    //zapisywanie nowej tablicy do pliku
    public void write(String fileName, int splitIndex) throws IOException {
        for (double d : finalArray) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources\\" + fileName));
            bufferedWriter.write(String.valueOf(d));
        }
    }


    public void splitArray(int threadsQty) {
        int arraySizeSplit = numbersList.length / threadsQty;
        for (int n = 0; n < threadsQty; n++) {
            Numbers thread = new Numbers(numbersList.length, n, arraySizeSplit);
            thread.start();
        }
    }


    public void run() {
        int i = 0;
        for (double d : numbersList) {
            double calculated = Math.sin((Math.cos(Math.log(Math.tan(d)))) + Math.cos(Math.sin(Math.log(Math.log(1.0 / Math.tan(d))))));
            finalArray[i] = calculated;
            i++;
        }


    }


//    public class Worker {
//
//        double finalArray[];
//        double newArray[];
//        int startIndex;
//        int endIndex;
//
//        public Worker() {
//            finalArray = new double[numbersList.length];
//
//        }
//
//    }

}

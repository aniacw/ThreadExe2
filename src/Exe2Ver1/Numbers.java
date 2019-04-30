package Exe2Ver1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

//numbers do wczytania, zapisu danych i podzialu na wątki
//konkretne dzialania niech wykona oddzielna klasa Worker wewnętrzna względem Numbers
//Worker dostaje ref na tablicę, tab wynikową i zakres indeksów na których przeprowadza obliczenia
//iteruje po danych, wykonuje oper mat, zapisuje w tab wynikowej na takim samym indeksie
public class Numbers {

    private double[] numbers;
    private double[] results;
    private Function<Double, Double> function;

    private class Worker extends Thread {
        private int begin;
        private int size;

        public Worker(int begin, int size) {
            this.begin = begin;
            this.size = size;
        }

        @Override
        public void run() {
            int end = begin + size;
            for (int i = begin; i < end; ++i) {
                results[i] = function.apply(numbers[i]);
            }
        }
    }


    //czytanie liczb z pliku
//    private void readNumbers(String fileName) {
//        try {
//            Scanner scanner = new Scanner(new FileInputStream("resources\\" + fileName));
//            ArrayList<Double> numbersList = new ArrayList<>();
//            while (scanner.hasNextLine()) {
//                String newLine = scanner.nextLine();
//                numbersList.add(Double.valueOf(newLine));
//            }
//            numbers = new double[numbersList.size()];
//            results = new double[numbersList.size()];
//            for (int i = 0; i < numbers.length; i++) {
//                numbers[i] = numbersList.get(i);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


    //losowanie liczb


    //zapisywanie nowej tablicy do pliku
//    private void write(String fileName, int splitIndex) throws IOException {
//        for (double d : results) {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources\\" + fileName));
//            bufferedWriter.write(String.valueOf(d));
//        }
//    }


    private boolean splitArray(int threadsQty) {
        int partSize = numbers.length / threadsQty;
        int firstPartSize = partSize + numbers.length % threadsQty;
        ArrayList<Worker> threads = new ArrayList<>(threadsQty);
        Worker worker = new Worker(0, firstPartSize);
        threads.add(worker);
        worker.start();
        int lastIdx = firstPartSize;
        for (int n = 1; n < threadsQty; n++) {
            worker = new Worker(lastIdx, partSize);
            threads.add(worker);
            worker.start();
            lastIdx += partSize;
        }
        for (Worker w : threads) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


//    public double[] compute(String filename, int threadsQty) {
//        readNumbers(filename);
//        if (splitArray(threadsQty))
//            return results;
//        else
//            return null;
//    }

    public double[] compute(Function<Double, Double> function, double[] data, int threadsQty) {
        numbers = data;
        results = new double[data.length];
        this.function=function;
        if (splitArray(threadsQty))
            return results;
        else
            return null;
    }
}

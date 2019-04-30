package Exe2Ver1;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void randomize(String fileName, int n) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources\\" + fileName));
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            bufferedWriter.write(String.valueOf(random.nextDouble()));
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
    }

    public static double[] randomize(int n) {
        double[] data = new double[n];
        for (int i=0;i<n;++i)
            data[i] = Math.random();
        return data;
    }

    private static double func(double x){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x+1;
    }

    public static void main(String[] args) throws IOException {
        //randomize("Numbers.txt", 100);
        Numbers numbers = new Numbers();
        double[] data = randomize(100);
        long t = System.currentTimeMillis();
        double[] results = numbers.compute(Main::func, data, 5);
        System.out.println(System.currentTimeMillis()-t);
    }


}


//Napisz program, który z pliku wczyta dane liczbowe (każda liczba oddzielona nową linią, dane wcześniej wylosuj)
//o odpowiednio dużym rozmiarze (np 1'000'000 liczb) i załaduje je do tablicy

//następnie rozkładając zadanie na kilka wątków podzieli tablicę na kilka części
//i wykona odpowiednio skomplikowaną funkcję (tak żeby czas działania był zauważalnie długi), np
//sin(cos(log(tan(x)))) + cos(sin(log(log(cot(x)))))
//na każdej liczbie, wyniki zapisując w tablicy.

//Kiedy wszystkie wątki skończą działanie zebrane w całość wyniki powinny zostać zapisane do nowego pliku.
//Zagwaratnuj, że ilość danych się zgadza, kolejność danych odpowida danym wejściowym i wyniki są poprawne.
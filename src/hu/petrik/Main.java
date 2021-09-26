package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Festmeny> festmenyList = new ArrayList<>();
    static void beOlv(String path){
        try {
            FileReader f = new FileReader(path);
            BufferedReader b = new BufferedReader(f);
            String sor = b.readLine();
            while (sor != null){
                String[] adat = sor.split(";");
                festmenyList.add(new Festmeny(adat[0], adat[1],adat[2]));
                sor = b.readLine();
            }
            b.close();
            f.close();
        }
        catch (IOException e){
            System.out.println(" Sajna nem sikerult :(");
        }
    }
    public static void main(String[] args) {
        beOlv("festmenyek.csv");

    }
}

package hu.petrik;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner s = new Scanner(System.in);
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
    public static void addFestmeny(){
        String ffesto;
        String fcim;
        String fstilus;
        System.out.println("Szeretne képet hozzáadni az aukcióhoz? (I)gen / (N)em");
        String kerdes = s.next();
        if(kerdes.equals("I") || kerdes.equals("i") || kerdes.equals("Igen") || kerdes.equals("igen")){
            System.out.print("Hány képet adna hozzá?");
            int db = s.nextInt();
            for (int i = 0; i < db; i++) {
                System.out.print("Mi a kép címe?");
                ffesto = s.next();
                System.out.print("Ki készítette?");
                fcim = s.next();
                System.out.print("Milyen stílusban készült?");
                fstilus = s.next();
                festmenyList.add(new Festmeny(ffesto,fcim,fstilus));
            }
        }
    }
    public static void licit20Random(){
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            festmenyList.get(rnd.nextInt(festmenyList.size())).licit();
        }
    }
    public static void licital(boolean bool){
        while(bool){
            System.out.printf("\nKérek egy sorszámot (0 kilép, képek száma: %d)", festmenyList.size());
            try{
            int sorszam = s.nextInt();
            if (sorszam == 0){
                bool = false;
            }
            else if (festmenyList.get(sorszam-1).getElkelt()){
                System.out.println("A festmény elkelt!");
            }
            else{
                System.out.printf("\nA festmény adatai: \n%s", festmenyList.get(sorszam-1));
                System.out.println("Kérek egy százalékot, amennyivel emeli a licitet: ");
                int szazalek = s.nextInt();
                festmenyList.get(sorszam-1).licit(szazalek);
            }
            }
            catch (Exception e){
                System.out.println("Nem számot adott meg!");
                //System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        beOlv("festmenyek.csv");
        addFestmeny();
        licit20Random();
        System.out.println("Szeretne licitálni az aukción? (I)gen / (N)em");
        String kerdes = s.next();
        if(kerdes.equals("I") || kerdes.equals("i") || kerdes.equals("Igen") || kerdes.equals("igen")){
            licital(true);
        }
        for (int i = 0; i < festmenyList.size(); i++) {
            if (festmenyList.get(i).getLicitekSzama() != 1){
                festmenyList.get(i).setElkelt(true);
            }
            System.out.println(festmenyList.get(i));
        }
        System.out.println("\nLegyen szép napja!");
    }
}

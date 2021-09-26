package hu.petrik;

import java.time.LocalDateTime;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama = 0;
    private int legmagasabbLicit = 0;
    private LocalDateTime legutolsoLicit;
    private boolean elkelt = false;

    public Festmeny(String cim, String festo, String stilus){
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
    }
    public String getFesto() {
        return this.festo;
    }
    public String getStilus() {
        return this.stilus;
    }
    public int getLicitekSzama() {
        return this.licitekSzama;
    }
    public int getLegmagasabbLicit() {
        return this.legmagasabbLicit;
    }
    public LocalDateTime getLegutolsoLicit() {
        return this.legutolsoLicit;
    }
    public boolean getElkelt() {
        return this.elkelt;
    }
    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }
    public void licit(){
        if (getElkelt()){
            System.out.println("A festmény már elkelt T-T ");
        }
        if (getLicitekSzama() == 0){
            this.legmagasabbLicit += 100;
            this.licitekSzama++;
            this.legutolsoLicit = LocalDateTime.now();
        }
        if (getLicitekSzama() > 0){
            licit(10);
        }
    }
    public void licit(int mertek){
        if (10<=mertek && mertek<=100){
            this.legmagasabbLicit += this.legmagasabbLicit * (mertek/10);
            this.licitekSzama++;
            this.legutolsoLicit = LocalDateTime.now();
        }
        else{
            System.out.println("Nem megfelelő mérték");
        }
    }

    @Override
    public String toString() {
        String ez = festo +": "+cim+  "(" + stilus + ")" + "\n"+elkelt+"\n"+
                legmagasabbLicit+"$ - "+ legutolsoLicit +" ( összesen: "+ licitekSzama + " db)";
        String az = festo +": "+cim+  "(" + stilus + ")" + "\n"+
                legmagasabbLicit+"$ - "+ legutolsoLicit +" ( összesen: "+ licitekSzama + " db)";
        if (elkelt){
            return ez;
        }
        else{
            return az;
        }
    }
}

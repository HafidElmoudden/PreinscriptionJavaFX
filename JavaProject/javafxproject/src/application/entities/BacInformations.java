package application.entities;

public class BacInformations
{
    public String cne;
    public String baccalaureat;
    public String bac_anne;
    public String note_math;
    public String note_physic;
    public String note_svt;
    public String note_francais;
    public String note_anglais;
    public String note_bac;
    public String ville;
    
   public BacInformations(String note_math,String note_physic, String note_svt, String note_francais, String note_anglais) {
	   this.note_math= note_math;
   		this.note_anglais= note_anglais;
   		this.note_francais= note_francais;
   		this.note_physic= note_physic;
   		this.note_svt= note_svt;
	}

public BacInformations() {
}
	
}
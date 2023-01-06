package application.entities;

public class SchoolFormationPost{
    public String ecole_code;
    public String max_chaises;
    public String nbr_chaises_reserver;
    public String nbr_chaises_available;
    public String formation_code;
    public String cp_code;
    public String formation_nom;

    public SchoolFormationPost(String ecole_code, String max_chaises, String nbr_chaises_reserver, String nbr_chaises_available, String formation_code, String cp_code, String formation_nom)
    {
        this.ecole_code = ecole_code;
        this.max_chaises = max_chaises;
        this.nbr_chaises_reserver = nbr_chaises_reserver;
        this.nbr_chaises_available = nbr_chaises_available;
        this.formation_code = formation_code;
        this.cp_code = cp_code;
        this.formation_nom = formation_nom;
    }    
}

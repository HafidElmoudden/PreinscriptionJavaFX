package application.entities;



public class FormationPost
{
    public String etablissement;
    public String formation;
    public String ville;
    public String residuelle;
    public String formation_code;

    //to use in student application gridview
    public String classement_note;
    public String candida_code;
    public FormationPost(String formation_code, String etablissement, String formation, String ville, String residuelle)
    {
        this.formation_code = formation_code;
        this.etablissement = etablissement;
        this.formation = formation;
        this.ville = ville;
        this.residuelle = residuelle;
    }
    public FormationPost(String etablissement, String formation, String classement_note, String ville, String residuelle, String candida_code)
    {
        this.etablissement = etablissement;
        this.formation = formation;
        this.ville = ville;
        this.residuelle = residuelle;
        this.classement_note = classement_note;
        this.candida_code = candida_code;
    }
}

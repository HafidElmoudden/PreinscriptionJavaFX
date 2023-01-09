package application.repositories;

import java.util.ArrayList;
import java.util.List;

import application.database.dbClient;

public class SignUpRepository {

	 static public void addUser(String email, String password, String usertype)
     {

         String insertCommand = "INSERT INTO Users VALUES (?, ?, ?)";

         List<Object> parameters = new ArrayList<>();
         parameters.add(email);
         parameters.add(password);
         parameters.add(usertype);

         dbClient.executeCommand(false,insertCommand, parameters);
     }
	 
	 static public void addEtudiant(String cne, String nom, String prenom, String ville, String telephone, String dateNaissance, String email, String sexe)
     {
         String insertCommand = "INSERT INTO Etudiant VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

         List<Object> parameters = new ArrayList<>();
         parameters.add( cne);
         parameters.add(nom);
         parameters.add(prenom);
         parameters.add(ville);
         parameters.add(telephone);
         parameters.add(dateNaissance);
         parameters.add(email);
         parameters.add(sexe);

         dbClient.executeCommand(false, insertCommand, parameters);
     }
}

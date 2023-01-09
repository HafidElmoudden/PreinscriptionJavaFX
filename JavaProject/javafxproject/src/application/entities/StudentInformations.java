package application.entities;

import application.utilities.ImageUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

public class StudentInformations
{
	private String firstName;
    private String lastName;
    private String email;
    private String cne;
    private String telephone;
    private String sexe;
    private String dateNaissance;
    private String age;
    private String bacYear;
    private String city;
    private String bac;
    private String formationNom;
    private String reponse;
    //for Sign Up
    private String password;
    private String bacCity;
    private Button deleteUser = new Button();
    private Button editUser = new Button();
    
    private BacInformations bacInformations = new BacInformations();
    
    public StudentInformations() {
    	ImageUtils.setButtonImage(getClass(),deleteUser, "Delete.png");
    	ImageUtils.setButtonImage(getClass(),editUser, "edit.png");
    	deleteUser.setOnAction(e -> {
    		System.out.println("yo yoyoyo Remove clicked : " + this.firstName + " " + this.lastName +" CNE : " + this.cne + " email : " + this.email);
    	});
    	editUser.setOnAction(e -> {
    		System.out.println("yo yoyoyo Edit clicked : " + this.firstName + " " + this.lastName+" CNE : " + this.cne + " email : " + this.email);
    	});
    }
    

    public Button getDeleteUser() {
		return deleteUser;
	}
	public void setDeleteUser(Button deleteUser) {
		this.deleteUser = deleteUser;
	}
	public Button getEditUser() {
		return editUser;
	}
	public void setEditUser(Button editUser) {
		this.editUser = editUser;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public String getFormationNom() {
		return formationNom;
	}
	public void setFormationNom(String formationNom) {
		this.formationNom = formationNom;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBacYear() {
		return bacYear;
	}
	public void setBacYear(String bacYear) {
		this.bacYear = bacYear;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBac() {
		return bac;
	}
	public void setBac(String bac) {
		this.bac = bac;
	}
	public BacInformations getBacInformations() {
		return bacInformations;
	}
	public void setBacInformations(BacInformations bacInformations) {
		this.bacInformations = bacInformations;
	}
	public String getBacCity() {
		return bacCity;
	}
	public void setBacCity(String bacCity) {
		this.bacCity = bacCity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
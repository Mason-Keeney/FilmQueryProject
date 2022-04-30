package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  private DatabaseAccessor db = new DatabaseAccessorObject();
  private Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//     app.test();
     app.launch();
  }

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  }

  private void launch() {
    
    startUserInterface();
    
    input.close();
  }

  private void startUserInterface() {
	  int userInput = 0;
	  do {
		  printMenu();
		  String userInputString = input.nextLine();
		  try {
			  userInput = Integer.parseInt(userInputString);
		  } catch (NumberFormatException e) {
			  System.out.println("Please enter a valid number");
			  continue;
		  }
		  
		  menuSwitch(userInput);
		  
	  } while(userInput != 3);
	  
	  
    
	  
  }
  
  private void selectFilmById() {
	  Integer id = null;
	  do {
		  System.out.println("What is the ID number of the film? (0 - Main Menu)");
		  String idString = input.nextLine();
		  try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			  System.out.println("Please enter a valid number");
			  id = 1;
			  continue;
		} 
		  Film film = db.findFilmById(id);
		  if  (film != null) {
			  System.out.println(film.quickDisplay());
			  printSubMenu();
			  id = subMenuSwitch(menuSelect(), film);
		  } else if (film == null && id != 0) {
			  System.out.println("Sorry, that is not a valid Film ID Number");
		  }
		  
		 
		  
	  } while (id != 0);
	  
  }
  
  private void selectFilmByKeyword() {
	  String id;
	  do {
		  System.out.println("What Keyword would you like to use? (0 - Main Menu)");
		  id = input.nextLine();
		  List<Film> films = db.findFilmsByKeyword(id);
		  if  (!films.isEmpty() && !id.equals("0")) {
			  System.out.println("These films match that description: ");
			  for (Film film : films) {
				  System.out.println(film.quickDisplay());	  
			}
			  printSubMenu();
			  id = subMenuSwitch(menuSelect(), films);
		  } else if (!id.equals("0")){
			  System.out.println("Sorry, no films match that description");
		  }
	  } while(!id.equals("0"));
	 
		
	}
  


private void printMenu() {
	  System.out.println("==============================");
	  System.out.println("| 1. Find Film by ID         |");
	  System.out.println("| 2. Find film by Keyword    |");
	  System.out.println("| 3. Exit                    |");
	  System.out.println("==============================");
  }
  
  private void menuSwitch(int userChoice) {
	  boolean isSelecting = true;
	  while(isSelecting) {
		  
	  switch (userChoice) {
	  
	  	case 1:
	  		selectFilmById();
	  		isSelecting = false;
	  		break;
		  
	  	case 2:
	  		selectFilmByKeyword();
	  		isSelecting = false;
	  		break;
	  		
	  	case 3:
	  		System.out.println("Have a wonderful day!");
	  		return;
	  		
	  	default:
	  		System.out.println("We didn't recognize that, please select a valid menu option");
	  		printMenu();
	  		userChoice = input.nextInt();
	  		input.nextLine();
	  		continue;
	  }
		  
	  }
  }
  
  private void printSubMenu() {
	  System.out.println("==============================");
	  System.out.println("| 1. Return to Main Menu     |");
	  System.out.println("| 2. View All Film Details   |");
	  System.out.println("==============================");
  }
  
  private Integer subMenuSwitch(int userInput, Film film) {
	  boolean isSelecting = true;
	  Integer resume = 0;
	  while(isSelecting) {
		  
	  switch (userInput) {
	  	case 1:
	  		isSelecting = false;
	  		break;
	  	case 2:
	  		System.out.println(film.toString());
	  		isSelecting = false;
	  		resume = 1;
	  		break;
	  	default:
	  		System.out.println("We didn't recognize that, please select a valid menu option");
	  		printMenu();
	  		userInput = input.nextInt();
	  		continue;
	  }		
	  }
	  
	  return resume;
  }
  
  private String subMenuSwitch(int userInput, List<Film> films) {
	  boolean isSelecting = true;
	  String resume = "0";
	  while(isSelecting) {
		  
	  switch (userInput) {
	  	case 1:
	  		isSelecting = false;
	  		break;
	  	case 2:
	  		for (Film film : films) {
	  			System.out.println(film.toString());
			}
	  		isSelecting = false;
	  		resume = "1";
	  		break;
	  	default:
	  		System.out.println("We didn't recognize that, please select a valid menu option");
	  		printMenu();
	  		userInput = input.nextInt();
	  		input.nextLine();
	  		continue;
	  }		
	  }
	  
	  return resume;
}
  
  private int menuSelect() {
	  
	  int userInput = 0;
	  boolean isSelecting = true;
	  while (isSelecting) {
		  String userInputString = input.nextLine();
	  try {
	 	  userInput = Integer.parseInt(userInputString);
	 	  isSelecting = false;
	  } catch (NumberFormatException e) {
		  System.out.println("Please enter a valid number");
	  }
	  }
	  return userInput;
	  
  }



}

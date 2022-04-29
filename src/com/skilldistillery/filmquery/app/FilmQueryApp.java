package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();
  Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//     app.test();
     app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

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
		  System.out.println("What is the ID number of the film? (0 to exit)");
		  String idString = input.nextLine();
		  try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			  System.out.println("Please enter a valid number");
			  continue;
		} 
		  
		  Film film = db.findFilmById(id);
		  if  (film != null) {
			  System.out.println(film.quickDisplay());
		  } else if (film == null && id != 0) {
			  System.out.println("Sorry, that is not a valid Film ID Number");
		  }
		  
	  } while (id != 0);
	  
  }
  
  private void selectFilmByKeyword() {
	  String id;
	  do {
		  System.out.println("What Keyword would you like to use? (\"exit\" to exit)");
		  id = input.nextLine();
		  List<Film> films = db.findFilmsByKeyword(id);
		  if  (films != null && !id.equals("exit")) {
			  System.out.println("These films match that description: ");
			  for (Film film : films) {
				  System.out.println(film.quickDisplay());	  
			}
		  } else if (id.equals("next")){
			  System.out.println("Sorry, no films match that description");
		  }
	  } while(!id.equals("exit"));
	 
		
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



}

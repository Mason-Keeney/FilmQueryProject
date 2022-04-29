package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
     app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  int id = input.nextInt();
    
//	  Actor actor = db.findActorById(id);
//	  System.out.println(actor);
//	  
//	  List<Actor> actors = db.findActorsByFilmId(id);
//	  System.out.println(actors);	  
//	  
	  List<Film> films = db.findFilmsByActorId(id);
	  System.out.println(films);
//	  
//	  Film film = db.findFilmById(id);
//	  System.out.println(film);
	  
  }

}

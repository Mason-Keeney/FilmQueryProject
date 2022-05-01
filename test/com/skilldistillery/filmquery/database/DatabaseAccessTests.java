package com.skilldistillery.filmquery.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
  private DatabaseAccessor db;


  @BeforeEach
  void setUp() throws Exception {
    db = new DatabaseAccessorObject();
  }

  @AfterEach
  void tearDown() throws Exception {
    db = null;
  }

  @Test
  void test_findFilmById_with_invalid_id_returns_null() {
    Film f = db.findFilmById(-42);
    assertNull(f);
  }
  
  @Test
  void test_findActorById_with_invalid_id_returns_null() {
	  Actor a = db.findActorById(-42);
	  assertNull(a);
  }
  
  @Test
  void test_findFilmById_returns_valid_film_object() {
	  Film f = db.findFilmById(1);
	  assertTrue(f.getTitle().contains("ACADEMY DINOSAUR"));
  }
  
  @Test
  void test_findActorById_returns_valid_actor_object() {
	  Actor a = db.findActorById(1);
	  assertTrue(a.getFirstName().equals("Penelope"));
  }
  
  @Test
  void test_findFilmsByKeyword_with_no_match_returns_empty_array() {
	  List<Film> f = db.findFilmsByKeyword("NO SUCH KEYWORD");
	  assertTrue(f.isEmpty());
  }
  
  @Test
  void test_findFilmsByKeyword_returns_valid_film_list() {
	  List<Film> f = db.findFilmsByKeyword("dinosaur");
	  assertFalse(f.isEmpty());
  }
  
  @Test
  void test_findFilmsByActorId_with_invalid_id_returns_empty_array() {
	  List<Film> f = db.findFilmsByActorId(-42);
	  assertTrue(f.isEmpty());
  }
  
  @Test
  void test_findFilmsByActorId_returns_valid_film_list() {
	  List<Film> f = db.findFilmsByActorId(1);
	  assertFalse(f.isEmpty());
  }
  
  @Test
  void test_findActorsByFilmId_with_invalid_id_returns_empty_array() {
	  List<Actor> a = db.findActorsByFilmId(-42);
	  assertTrue(a.isEmpty());
  }
  
  @Test
  void test_findActorsByFilmId_returns_valid_film_list() {
	  List<Actor> a = db.findActorsByFilmId(1);
	  assertFalse(a.isEmpty());
  }

}

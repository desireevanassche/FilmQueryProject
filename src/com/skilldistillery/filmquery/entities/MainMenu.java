
package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class MainMenu {

	Scanner kb = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public void menu() {
		boolean menuLoop = true;

		while (menuLoop) {
		MainMenu mainM = new MainMenu();
			System.out.println();
			System.out.println("        Movie Menu");
			System.out.println("------------------------------");
			System.out.println(" 1. Look up film by Id");
			System.out.println(" 2. Look up film by keyword");
			System.out.println(" 3. Exit the application ");
			System.out.println();

			int menuChoice = kb.nextInt();

			if (menuChoice > 0 || menuChoice <= 3) {

				switch (menuChoice) {

				case 1:
					mainM.searchById();
					break;

				case 2:
					mainM.SearchByKeyword();
					break;

				case 3:
					System.out.println("Thanks for stopping in..");
					System.out.println("Be Kind & Rewind! ");
					menuLoop = false; 
					break;

				default:
					System.out.println("Invlaid entry, please try again.");
					break;

				}

			}
		}
		}


	public void subMenu(int id) {

		Scanner kb = new Scanner(System.in);
		DatabaseAccessor db = new DatabaseAccessorObject();
		MainMenu mainM = new MainMenu();

		boolean menuLoop = true;

		while (menuLoop) {
			System.out.println();
			System.out.println("          Movie Submenu         ");
			System.out.println("------------------------------");
			System.out.println(" 1. Return to main menu");
			System.out.println(" 2. View all film details");
			System.out.println("------------------------------");
			System.out.println();

			int menuChoice = kb.nextInt();

			if (menuChoice > 0 || menuChoice <= 3) {

				switch (menuChoice) {

				case 1:
					mainM.menu();
					break;

				case 2:
					Film film = db.findFilmById(id);
					System.out.println(film.displayAllData());
					break;

				default:
					System.out.println("Invlaid entry, please try again.");
					break;

				}

			}
		}

	}
	


	public void searchById() {
		// prompt user to search
		System.out.println("Please enter the Id of the film you are looking for: ");
		int filmId = kb.nextInt();
		MainMenu mainM = new MainMenu();

		// assign to search from user input
		Film film = db.findFilmById(filmId);

		System.out.println(
				"Film Title:" + film.getTitle() + " Rating: " + film.getRating() + " Language " + film.getLanguage());
		System.out.println(film.getDescription() + "\n");
		System.out.println(film.getCast());
		System.out.println("");
		mainM.subMenu(filmId);
		
		
//		System.out.println(film);

		// create an if null or blank statement
	}

	public void SearchByKeyword() {
		
		System.out.println("Please enter the keyword you would like to search:");
		String filmKey = kb.nextLine();
		List<Film> film = db.findFilmByKey(filmKey);

		for (Film movie : film) {
			System.out.println(movie);
		}

//		System.out.println(film);

//		 for (int i = 0; i < film.size(); i++) {
//			 System.out.print(film.get(i) + " "); 
//			} 		

	}

}

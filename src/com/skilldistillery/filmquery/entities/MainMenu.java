package com.skilldistillery.filmquery.entities;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class MainMenu {

	Scanner kb = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public void menu() {
		MainMenu mainM = new MainMenu();
		
		boolean menuLoop = true;
		
		while (menuLoop) {
			System.out.println("----------Movie Menu");
    		System.out.println("------------------------------");
    		System.out.println(" 1. Look up film by Id");
    		System.out.println(" 2. Look up film by keyword");
    		System.out.println(" 3. Exit the application ");

			int menuChoice = kb.nextInt();

			if (menuChoice > 0 || menuChoice <= 3) {

				switch (menuChoice) {

        case 1:
			mainM.searchById();
			// call to method to search by id
//			mainM.searchById();

			break;

		case 2:
			mainM.SearchByKeyword();
			break;

		case 3:
			System.out.println("Thanks for stopping in..");
			System.out.println("Be Kind & Rewind! ");
			// add a true/ false statement that will evaluate false & exit
			break;

		default:
			System.out.println("Invlaid entry, please try again.");
			break;

				}

			}
		}

	}

	public void searchById() {
		//prompt user to search 
		System.out.println("Please enter the Id of the film you are looking for: ");
		int filmId = kb.nextInt();
		
		// assign to search from user input 
		Film film = db.findFilmById(filmId);
		
		System.out.println(film);
		
		//create an if null or blank statement 
	}
	
	public void SearchByKeyword() {
		System.out.println("Please enter the keyword you would like to search:");
		String filmKey = kb.nextLine();
		
		Film film = db.findFilmByKey(filmKey);
		System.out.println(film);
		
		
	}
}
package com.skilldistillery.filmquery.entities;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class MainMenu {

	Scanner kb = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public void menu() {
		
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
			Film film = db.findFilmById(17);
			System.out.println(film);
			// call to method to search by id
//			mainM.searchById();

			break;

		case 2:
			// call to method to search by keyword
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
		Film film = db.findFilmById(17);
		System.out.println(film);
	}
}
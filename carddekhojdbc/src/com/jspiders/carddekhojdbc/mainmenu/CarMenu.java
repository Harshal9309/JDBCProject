package com.jspiders.carddekhojdbc.mainmenu;

import java.util.Scanner;

import com.jspiders.carddekhojdbc.caroperation.CarOpearation;

public class CarMenu {

	static boolean loop = true;

	static CarOpearation carOpearation = new CarOpearation();

	public static void main(String[] args) {

		while (loop) {
			mainMenu();
		}
	}

	public static void mainMenu() {
		System.out.println("=======Main Menu=======\n" 
							+ "1.View All Cars\n" 
							+ "2.Search car\n" 
							+ "3.Add Car\n"
							+ "4.Remove Car\n" 
							+ "5.Edit Car\n" 
							+ "6.Exit");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		switch (choice) {

		case 1:
			carOpearation.viewAllCars();
			break;
		case 2:
			System.out.println("======Search Menu======\n" 
								+ "1.Search by name\n" 
								+ "2.Search by brand\n"
								+ "3.Search by FuelType\n" 
								+ "4.Search by price\n" 
								+ "5.Search by Id\n"
								+ "6. Go back ");
			int choice1 = sc.nextInt();

			switch (choice1) {

			case 1:
				carOpearation.searchByName(sc);
				break;
			case 2:
				carOpearation.searchByBrand(sc);
				break;
			case 3:
				carOpearation.searchByFuelType(sc);
				break;
			case 4:
				carOpearation.searchByPrice(sc);
				break;
			case 5:
				carOpearation.searchByCarId(sc);
				break;
			case 6 :
				mainMenu();
				break;
			default :
				System.out.println("Invalid Option. Please choose Correct Option");
				break;
			}
			break;
		case 3:
			carOpearation.addCar();
			break;
		case 4:
			carOpearation.removeCar(sc);
			break;
		case 5:
			carOpearation.editCar(sc);
			break;
		case 6:
			System.out.println("Thank You !");
			loop = false;
			break;
		default:
			System.out.println("Invalid Choice. Please Try Again !");
			break;
		}
	}
}

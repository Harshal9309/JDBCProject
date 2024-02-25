package com.jspiders.carddekhojdbc.caroperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class CarOpearation {

	private static Connection  connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static String query;
	
	public void viewAllCars() {
		try {
			openConnection();
			query ="SELECT * FROM Car_info";
			preparedStatement=connection.prepareStatement(query);
			resultSet =preparedStatement.executeQuery();
			if (resultSet.next()) {
	            do {
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(2));
	                System.out.println(resultSet.getString(3));
	                System.out.println(resultSet.getString(4));
	                System.out.println(resultSet.getString(5));
	                System.out.println(resultSet.getInt(6));
	            } while (resultSet.next());
	        } else {
	            System.out.println("Car data not found");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4", "root", "root");
	}
	
	private static void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}
		if(preparedStatement != null) {
			preparedStatement.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
	}

	public void addCar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many cars you Want to Add");
		int cars = scanner.nextInt();
		
		try {
			openConnection();
			for (int i = 0; i < cars; i++) {
				 System.out.println("Enter Id : ");
				 int id = scanner.nextInt();
				 scanner.nextLine();
				 System.out.println("Enter Name : ");
				 String name = scanner.nextLine();
				 System.out.println("Enter Brand : ");
				 String brand = scanner.nextLine();
				 System.out.println("Enter Model : ");
				 String model = scanner.nextLine();
				 System.out.println("Enter Fuel type : ");
				 String fuiltype = scanner.nextLine();
				 System.out.println("Enter price : ");
				 int price = scanner.nextInt();
				 query = "INSERT INTO Car_info VALUES (?,?,?,?,?,?)";
					preparedStatement=connection.prepareStatement(query);
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, brand);
					preparedStatement.setString(4, model);
					preparedStatement.setString(5, fuiltype);
					preparedStatement.setInt(6, price);
				    preparedStatement.addBatch();
			}
			     int[] res = preparedStatement.executeBatch();
			System.out.println(cars + "Row(s) Affected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeCar(Scanner sc) {
		System.out.println("Enter the id");
		int id = sc.nextInt();
		try {
			openConnection();
			query = "DELETE FROM Car_info WHERE id = ?";;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int res = preparedStatement.executeUpdate();
			System.out.println("Row Deleted");
			System.out.println(res + "row(s) Affected");
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void editCar(Scanner sc) {
		System.out.println("Enter car id");
		int id = sc.nextInt();
		
		System.out.println("Enter the Car name");
		String name = sc.next();
		sc.nextLine();
		System.out.println("Enter the Car Brand");
		String brand = sc.nextLine();
		System.out.println("Enter the Car Module");
		String model = sc.nextLine();
		System.out.println("Enter the Car Fuiltype");
		String fuel = sc.nextLine();
		System.out.println("Enter car Price");
		int price = sc.nextInt();
		try {
			openConnection();
			query = "UPDATE Car_info SET name = ? , brand = ? , model = ?, fueltype =?, price = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, brand);
			preparedStatement.setString(3, model);
			preparedStatement.setString(4, fuel);
			preparedStatement.setInt(5, price);
			preparedStatement.setInt(6, id);
			int res = preparedStatement.executeUpdate();
			if(res == 1) {
				System.out.println("Car Data Edieted succefully");
			}else {
				System.out.println("Car not found");
			}
			System.out.println(res + "row(s) Affected");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void searchByName(Scanner sc) {
		System.out.println("Enter the Car Name");
		String name = sc.next();
		try {
			openConnection();
			query="SELECT * FROM Car_info WHERE name = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
	            do {
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(2));
	                System.out.println(resultSet.getString(3));
	                System.out.println(resultSet.getString(4));
	                System.out.println(resultSet.getString(5));
	                System.out.println(resultSet.getInt(6));
	            } while (resultSet.next());
	        } else {
	            System.out.println("No cars found within the specified Name.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void searchByBrand(Scanner sc) {
		System.out.println("Enter the Car Brand");
		String brand = sc.next();
		try {
			openConnection();
			query="SELECT * FROM Car_info WHERE brand = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, brand);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
	            do {
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(2));
	                System.out.println(resultSet.getString(3));
	                System.out.println(resultSet.getString(4));
	                System.out.println(resultSet.getString(5));
	                System.out.println(resultSet.getInt(6));
	            } while (resultSet.next());
	        } else {
	            System.out.println("No cars found within the specified Brand.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void searchByFuelType(Scanner sc) {
		System.out.println("Enter the FuelType");
		String fueltype = sc.next();
		try {
			openConnection();
			query="SELECT * FROM Car_info WHERE fueltype = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fueltype);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
	            do {
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(2));
	                System.out.println(resultSet.getString(3));
	                System.out.println(resultSet.getString(4));
	                System.out.println(resultSet.getString(5));
	                System.out.println(resultSet.getInt(6));
	            } while (resultSet.next());
	        } else {
	            System.out.println("No cars found within the specified Fuel Type.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void searchByPrice(Scanner sc) {
		System.out.println("Enter the Car price Minimum range");
		int price1 = sc.nextInt();
		System.out.println("Enter the Car price Maximum range");
		int price2 = sc.nextInt();
		try {
			openConnection();
			query="SELECT * FROM Car_info WHERE price BETWEEN ? AND ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, price1);
			preparedStatement.setInt(2, price2);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
	            do {
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(2));
	                System.out.println(resultSet.getString(3));
	                System.out.println(resultSet.getString(4));
	                System.out.println(resultSet.getString(5));
	                System.out.println(resultSet.getInt(6));
	            } while (resultSet.next());
	        } else {
	            System.out.println("No cars found within the specified price range.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void searchByCarId(Scanner sc) {
		System.out.println("Enter the Car id");
		int id = sc.nextInt();
		try {
			openConnection();
			query="SELECT * FROM Car_info WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
	            do {
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(2));
	                System.out.println(resultSet.getString(3));
	                System.out.println(resultSet.getString(4));
	                System.out.println(resultSet.getString(5));
	                System.out.println(resultSet.getInt(6));
	            } while (resultSet.next());
	        } else {
	            System.out.println("No cars found within the specified Id");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

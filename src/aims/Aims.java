package aims;
import java.util.Scanner;

//import com.sun.tools.classfile.StackMapTable_attribute.chop_frame;

import media.Book;
import media.CompactDisc;
import media.DigitalVideoDisc;
import media.Media;
import media.Track;
import order.Order;

public class Aims {
	
	private static Order createExampleOrder() {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", 19.95f, 87, "Roger Allers");
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Java world", "Codeventure", 69.96f, 90, "Mad Demon");
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("A beautiful hello World", "Science Fiction", 90.9f, 89, "Peter Scripter");
		
		Book book1 = new Book("Nightmare", "Chemical periodic table", 100.0f, "Dmitri Ivanovich Mendeleev");
		Book book2 = new Book("Fiction", "Doraemon", 20.05f, "Fujiko F. Fujio");
		
		Track track1 = new Track("Hello", 12);
		Track track2 = new Track("Send my love", 10);
		Track track3 = new Track("Someone like you", 8);
		Track track4 = new Track("All i ask", 21);
		Track track5 = new Track("Skyfall", 15);
		
		CompactDisc album1 = new CompactDisc("Depression", "Classic", 245.55f, "Adele",track1, track2, track3, track4, track5);
		Order order = new Order();
		order.addMedia(dvd1, dvd2, dvd3, book1, book2, album1);
		return order;
	}
		public static void showMenu() {
		System.out.println("Order Management Application: ");
		System.out.println("--------------------------------");
		System.out.println("1. Create new order");
		System.out.println("2. Add item to the order");
		System.out.println("3. Delete item by id");
		System.out.println("4. Display the items list of order");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		Order order = new Order();
		Order exOrder = createExampleOrder();
		showMenu();
		int c;
		do {
			c = scanner.nextInt();
			scanner.nextLine();
			switch (c) {
			case 0: {
				System.out.println("Good bye !!");
				System.exit(1);
				break;
			}
			case 1: {
				System.out.println("Sucessfully created new order!");
				flag = true;
				break;
			}
			case 2: {
				if (!flag) {
					System.out.println("Please create a new order first!");
					break;
				}
				int m = 0;
				do {
					System.out.println("Please choose media format:");
					System.out.println("1. Book");
					System.out.println("2. DVD");
					System.out.println("3. CD");
					m = scanner.nextInt();
					scanner.nextLine();
				} while (m < 1 || m > 3 );
				System.out.println("Enter title: ");
				String title = scanner.nextLine();
				Media media = exOrder.searchMedia(title);
				if (media != null) {
					order.addMedia(media);
				} else {
					System.out.println("Error! Can not add + " + title);
					break;
				}
				if (m == 2 || m == 3) {
					System.out.println("Do you want to play the cd/dvd?(y/n)");
					String answer;
					while (true) {
						answer = scanner.nextLine();
						if (answer.equals("y") && m == 2) {
							((DigitalVideoDisc)media).play();
							break;
						} else if (answer.equals("y") && m == 3) {
							((CompactDisc)media).play();
							break;
						}	
					}
				}
				showMenu();
				break;
			}
			case 3: {
				if (!flag) {
					System.out.println("Please create a new order first!");
					break;
				}
				System.out.print("Enter title: ");
				String title = scanner.nextLine();
				if (order.searchMedia(title) != null) {
					order.removeMedia(order.searchMedia(title));
					System.out.println("Sucessfully deleted " + title);
				} else {
					System.out.println("Item not found!");
				}
				showMenu();
				break;
			}
			case 4: {
				if (!flag) {
					System.out.println("Please create a new order first!");
					break;
				}
				order.print();
				showMenu();
				break;
			}
			default:
				System.out.println("Invalid input! Please choose a number: 0-1-2-3-4");
				showMenu();
			}
		} while (c != 0);
		scanner.close();
	}
	
}

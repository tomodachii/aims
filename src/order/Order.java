package order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import media.Book;
import media.CompactDisc;
import media.DigitalVideoDisc;
import media.Media;
import utils.MyDate;

public class Order {
	public static final int MAX_NUMBERS_ORDERED = 10;
	public static final int MAX_LIMITTED_ORDERS = 5;
	private List<Media> itemsOrdered = new ArrayList<Media>();
	private MyDate dateOrdered = new MyDate();
	private static int nbOrders = 0;
	
	public ArrayList<Media> getItemsOrderedList() {
		return (ArrayList<Media>) itemsOrdered;
	}

	public static int getNbOrders() {
		return nbOrders;
	}

	public boolean addMedia(Media item) {
		if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
			itemsOrdered.add(item);
			return true;
		}
		return false;
	}

	public boolean addMedia(Media... itemList) {
		for (int i = 0; i < itemList.length; i++) {
			if (!addMedia(itemList[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean addMedia(Media item1, Media item2) {
		if (addMedia(item1) && addMedia(item2)) {
			return true;
		}
		return false;
	}

	public void removeMedia(Media item) {
		itemsOrdered.remove(item);
	}

	public float totalCost() {
		float total = 0;
		Media mediaItem;
		Iterator<Media> iter = itemsOrdered.iterator();
		while (iter.hasNext()) {
			mediaItem = (Media) (iter.next());
			total += mediaItem.getCost();
		}
		return total;
	}

	public Media searchMedia(String title) {
		for (Media item : itemsOrdered) {
			if (item.getTitle().equals(title)) {
				return item;
			}
		}
		return null;
	}

	public List<String> getTitleList() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < itemsOrdered.size(); i++) {
			if (itemsOrdered.get(i) != null) {
				list.add(itemsOrdered.get(i).getTitle());
			}
		}
		return list;
	}

	public void print() {
		System.out.println("***********************Order***********************");
		System.out.print("Date: ");
		dateOrdered.print();
		System.out.println("Ordered Items:");
		float total = 0f;
		for (Media item : itemsOrdered) {
			if (item != null) {
				if (item instanceof DigitalVideoDisc) {
					System.out.print("DVD");
					System.out.printf(" - %s - %s - ", item.getTitle(), item.getCategory());
					System.out.print(
							((DigitalVideoDisc) item).getDirector() + " - " + ((DigitalVideoDisc) item).getLength());
				}
				if (item instanceof Book) {
					System.out.print("Book");
					System.out.printf(" - %s - %s - ", item.getTitle(), item.getCategory());
					System.out.print(((Book) item).getAuthors());
				}
				if (item instanceof CompactDisc) {
					System.out.print("CD");
					System.out.printf(" - %s - %s - ", item.getTitle(), item.getCategory());
					System.out.print(((CompactDisc) item).getArtist() + " - " + ((CompactDisc) item).getLength());
				}
				System.out.println(" : " + item.getCost());

				total += item.getCost();
			} else {
				break;
			}
		}
		System.out.println("Total cost: " + total);
		System.out.println("***************************************************");
	}

	public Media getALuckyItem() {
		int random = (int) (Math.random() * (itemsOrdered.size()));
		itemsOrdered.get(random).setCost(0);
		System.out.println("Congratulation! " + itemsOrdered.get(random).getTitle() + " is the lucky item");
		return itemsOrdered.get(random);
	}

	public Order() {
		if (nbOrders < MAX_LIMITTED_ORDERS) {
			this.dateOrdered = new MyDate();
			nbOrders += 1;
		}
	}

	public Order(ArrayList<Media> itemsOrdered, MyDate dateOrdered) {
		if (nbOrders < MAX_LIMITTED_ORDERS) {
			this.itemsOrdered = itemsOrdered;
			this.dateOrdered = dateOrdered;
			nbOrders += 1;
		}
	}
}

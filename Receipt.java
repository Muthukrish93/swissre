package swissRE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Receipt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		Map<String,Double> items = new HashMap<>();
		items.put("small coffee", 2.50);
		items.put("medium coffee", 3.00);
		items.put("large coffee", 3.50);
		items.put("orange juice", 3.95);

		Map<String,Double> snacks = new HashMap<>();
		snacks.put("bacon roll", 4.50);

		Map<String,Double> extras = new HashMap<>();
		extras.put("extra milk", 0.30);
		extras.put("foamed milk", 0.50);
		extras.put("special roast", 0.90);

		ArrayList< String> products = new ArrayList<String>();
		ArrayList< Double> productCost = new ArrayList<Double>();
		ArrayList< String> extraList = new ArrayList<String>();
		int itemcount = 0;
		Boolean hasBeverages = false;
		Boolean hasSnacks = false;
		Boolean hasExtras = false;
		double total =0;

		while(true){
			System.out.println("Enter the items ::");
			String orders = scanner.nextLine();

			if(orders.equalsIgnoreCase("Done")) {
				break;
			}
			if (orders.contains("with")) {
				String extra = orders.split("with ")[1];
				String item = orders.split("with")[0].trim();

				if (extras.containsKey(extra)) {
					products.add(orders);
					Double itemWithExtra =items.get(item)+ extras.get(extra);
					productCost.add(itemWithExtra);
					hasExtras = true;
					extraList.add(extra);
					total+=itemWithExtra;
					System.out.println("product ::" +orders);
					System.out.println("productCost::" +itemWithExtra+"CHF");

				}else {
					System.out.println("Extras Not Present in the List");
				}
			}
			else if(snacks.containsKey(orders)){
				products.add(orders);
				productCost.add(snacks.get(orders));
				hasSnacks = true;
				total+=snacks.get(orders);
				System.out.println("product ::" +orders);
				System.out.println("productCost::" +snacks.get(orders)+"CHF");
			}	
			else if (items.containsKey(orders)){
				products.add(orders);
				productCost.add(items.get(orders));
				hasBeverages = true;
				total+=items.get(orders);
				itemcount++;
				if(itemcount ==5) {
					System.out.println("Discount on 5th Beverage..."+orders +""+items.get(orders)+"CHF");
					total-=items.get(orders);
					itemcount =0;
				}
				System.out.println("product ::" +orders);
				System.out.println("productCost::" +items.get(orders)+"CHF");
			}else {
				System.out.println("Given order is not available");
			}

		}
		if (hasBeverages && hasSnacks) {
			if(hasExtras) {
				String discountExtra = extraList.getFirst();
				System.out.println("Discount Extra..."+discountExtra +""+extras.get(discountExtra)+"CHF");
				total-=extras.get(discountExtra);
			}
		}

		System.out.println("Products List::"+products.toString());

		System.out.println("Total cost::" + total +"CHF");
	}

}

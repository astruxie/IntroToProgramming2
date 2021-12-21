
/**
 * Class that stores information about a recepe.
 * 
 * @author Sam Noggle
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Recipe implements Comparable<Recipe> {
	/** The name of the recipe. */
	private String name;
	/** Which category the recipe belongs in. */
	private String category;
	/** A list of the ingredients needed. */
	private Set<Ingredient> ingredients;
	/** An ordered list of instructions. */
	private List<String> instructions;

	/**
	 * Constructs a new Recipe.
	 * 
	 * @param n the name of the recipe
	 * @param c the category the recipe belongs in
	 */
	public Recipe(String n, String c) {
		category = c;
		name = n;
		ingredients = new HashSet<Ingredient>();
		instructions = new ArrayList<String>();
	}

	/**
	 * Returns the name of the recipe.
	 * 
	 * @return the name of the recipe
	 */

	public String getName() {
		return name;
	}

	/**
	 * Returns the category of the recipe.
	 * 
	 * @return the category of the recipe
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Adds one ingredient to the end of the list.
	 * 
	 * @param i the ingredient to be added
	 */
	public void addIngredient(Ingredient i) {
		if (!(ingredients.contains(i))) {
			ingredients.add(i);
		}

	}

	/**
	 * Adds a step to the end of the list.
	 * 
	 * @param i the step to be added
	 */
	public void addStep(String i) {
		instructions.add(i);
	}

	/**
	 * Inserts a step anywhere in the list.
	 * 
	 * @param step the step to be added
	 * @param i    the index of where the step should be added
	 */
	public void insertStep(String step, int i) {
		String temp;
		String last = instructions.get(instructions.size() - 1);
		for (int x = instructions.size() - 1; i < x; x--) {
			temp = instructions.get(x - 1);
			instructions.set(x, temp);
		}
		instructions.set(i, step);
		instructions.add(last);
	}

	/**
	 * Calculates and returns the total calories for a recipe.
	 * 
	 * @return the total calories of the recipe
	 */
	public double getTotalCalories() {
		// iterator
		Iterator<Ingredient> itr = ingredients.iterator();

		double totalCal = 0;
		while (itr.hasNext()) {
			Ingredient temp = itr.next();
			totalCal += (temp.getCalories() * temp.getQuantity());
		}
		return totalCal;
	}

	/**
	 * Doubles the recipe ingredients.
	 * 
	 * @return the new doubled recipe
	 */
	public Recipe getDoubled() {
		// construct the copy
		Recipe doubledRecipie = new Recipe(this.getName(), this.getCategory());

		// copy instructions
		for (int i = 0; i < this.instructions.size(); i++) {
			doubledRecipie.addStep(this.instructions.get(i));
		}
		// iter
		Iterator<Ingredient> itr = ingredients.iterator();

		// copy and double ingredients
		while (itr.hasNext()) {
			Ingredient temp = itr.next();
			// get all ingredient info
			double quantity = temp.getQuantity();
			quantity = quantity * 2;
			String unit = temp.getUnit();
			String type = temp.getType();
			int calories = temp.getCalories();

			// make the new ingredients to be added
			Ingredient ing = new Ingredient(quantity, unit, type, calories);

			doubledRecipie.addIngredient(ing);
		}
		return doubledRecipie;
	}

	/**
	 * Determines if the user's pantry is sufficient for the recipe.
	 * 
	 * @param userIngredients the user's pantry
	 * @return true if the recipe can be made, false if not
	 */
	public boolean canMake(Set<Ingredient> userIngredients) {
		// iterators
		Iterator<Ingredient> itr = ingredients.iterator();
		Iterator<Ingredient> userItr = userIngredients.iterator();
		boolean flag = false; // true if the ingredient has been found!

		// go through recipe ingredients
		while (itr.hasNext()) {
			Ingredient recipe = itr.next();
			// extract type and amount
			String neededType = recipe.getType();
			double neededQuantity = recipe.getQuantity();
			// reset flag
			flag = false;

			// go through user ingredients to check for it wooohoo
			while (userItr.hasNext()) {

				Ingredient pantry = userItr.next();
				// extract type and amount
				String userType = pantry.getType();
				double userQuantity = pantry.getQuantity();

				// check and return if not there
				if (neededType.contentEquals(userType) && (userQuantity < neededQuantity)) {
					return false;
				} else if (neededType.contentEquals(userType) && (userQuantity >= neededQuantity)) {
					flag = true;
				}
			}
			if (flag == false) {
				return false;
			}

		}
		// escaped the loops without being false so
		return true;
	}

	@Override
	public String toString() {
		String s;
		s = ("Name: " + name + "\nCategory: " + category + "\n\n\tIngredients:");
		for (Ingredient i : ingredients) {

			s += "\n" + i.toString();
		}
		s += ("\n\n\tInstructions:");
		for (int i = 0; i < instructions.size(); i++) {

			s += ("\n" + i + ": " + instructions.get(i));
		}
		s += "\n";
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Recipe)) {
			return false;
		}
		Recipe other = (Recipe) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Recipe other) {
		// variables to make it look prettier
		String otherName = other.getName();
		String otherCategory = other.getCategory();

		SortedSet<String> categories = new TreeSet<String>();
		categories.add(category);
		categories.add(otherCategory);

		SortedSet<String> names = new TreeSet<String>();
		names.add(name);
		names.add(otherName);

		if (name.contentEquals(otherName) && category.contentEquals(otherCategory)) {
			return 0;
		} else if (category.contentEquals(otherCategory)) {
			if (names.first().contentEquals(name)) {
				return -1;
			} else {
				return 1;
			}

		} else if (categories.first().contentEquals(category)) {
			return 1;
		} else {
			return -1;
		}

	}

}

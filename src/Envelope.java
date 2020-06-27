
//*******************************************************************
//  Envelope
//
// This serves as a subbudget designated for particular types of expenses. Expenses are routed to different categories
//*******************************************************************
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Envelope implements IExpenseContainer {
	Map<String, EnvelopeCategory> categories = new HashMap<String, EnvelopeCategory>();
	Map<String, Double> customAttributeNameToAmount = new HashMap<String, Double>();
	double currentBalance;
	double startingBalance;
	double totalSpent;
	String name;

	public Envelope(double targetBalance, String name) {
		this.currentBalance = targetBalance;
		this.startingBalance = targetBalance;
		this.totalSpent = 0;
		this.name = name;
		categories.put("Other", new EnvelopeCategory("Other"));
	}

	public abstract void addExpense(Expense ex);

	public String getName() {
		return this.name;
	}
	
	public <T extends Expense> void addExpenseItem (ExpenseItem<T> t) {
		Expense ex = (Expense)t.get();
		this.adjustEnvelope(ex);
		this.addToCategory(ex);
		this.addToAttribute(ex);
	}
	

	public Set<String> getCategoryNames() {
		Set<String> categoryNames = new HashSet<String>();
		for (EnvelopeCategory cat : this.getCategories()) {
			categoryNames.add(cat.getName());
		}
		return categoryNames;
	}

	public Collection<EnvelopeCategory> getCategories() {
		return this.categories.values();
	}

	protected void adjustEnvelope(Expense ex) {
		this.currentBalance -= ex.amount;
		this.totalSpent += ex.amount;
	}

	public void updateCategory(String oldName, String newName) {

		if (categories.containsKey(oldName)) {
			EnvelopeCategory categoryToUpdate = this.categories.remove(oldName);
			categoryToUpdate.setName(newName);
			categories.put(newName, categoryToUpdate);
		}

	}

	public String printEnvelope() {
		return "This month $" + this.startingBalance + " was allocated, $" + this.totalSpent + " was Spent, and $"
				+ this.currentBalance + " is remaining";
	}

	public void addToCategory(Expense ex) {
		String categoryName = ex.getCategory();
		if (this.categories.containsKey(categoryName)) {
			EnvelopeCategory category = this.categories.get(categoryName);
			category.addExpense(ex);
		} else {
			this.categories.get("Other").addExpense(ex);
		}

	}
	
	protected void addToCategory(Expense ex, String category) {
		if(this.categories.containsKey(category)) {
			this.categories.get(category).addExpense(ex);
		} else {
			this.categories.get("Other").addExpense(ex);
		}
	}
	
	protected void addToAttribute(Expense ex) {
		if(!this.customAttributeNameToAmount.containsKey(ex.getAttribute())) {
			this.increaseAttributeValue("unattributed", ex.amount);
		} else {
			this.increaseAttributeValue(ex.getAttribute(), ex.amount);
		};
	}
	
	protected void addAttribute (String attributeName) {
		this.customAttributeNameToAmount.put(attributeName, 0.0);
	}
	
	protected void increaseAttributeValue (String attribute, double amount) {
		if(this.customAttributeNameToAmount.containsKey(attribute)) {
			Double originalAmount = this.customAttributeNameToAmount.remove(attribute);
			this.customAttributeNameToAmount.put(attribute, originalAmount + amount);
		}
	}

}

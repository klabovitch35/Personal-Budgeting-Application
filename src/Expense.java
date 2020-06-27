//*******************************************************************
//  Expense
//
// Description: represents an individual expense, and its amount
//*******************************************************************

import java.util.Date;

public abstract class Expense {
	public double amount;
	public Date date;
	public String category;
	public String identifier;
	public String attribute;
	public String merchant;

	public Expense(double amount, Date date, String category) {
		this.amount = amount;
		this.date = date;
		this.category = category;
	}

	// contains identifier for finding envelope when importing expenses
	public Expense(double amount, Date date, String category, String identifier, String merchant) {
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.identifier = identifier;
		this.merchant = merchant;
	}
	
	public Expense(double amount, Date date, String category, String identifier, String attribute, String merchant) {
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.identifier = identifier;
		this.attribute = attribute;
		this.merchant = merchant;
	}
	
	public Expense() {
		
	}

	public String getCategory() {
		return this.category;
	}

	public String getIdentifier() {
		return this.identifier;
	}
	
	public String getAttribute() {
		return this.attribute;
	}
	
	public String printExpense() {
		return this.merchant + " " + this.date + " $" + this.amount;
	}

}

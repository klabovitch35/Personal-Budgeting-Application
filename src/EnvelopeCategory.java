
//*******************************************************************
//  Envelope Category
//
// Description: Subcategory of Envelope. Contains all expenses of a certain type. 
//*******************************************************************
import java.util.ArrayList;

public class EnvelopeCategory implements IExpenseContainer {
	ArrayList<Expense> expenses = new ArrayList<Expense>();
	public String name;
	double totalSpent;

	public EnvelopeCategory(String name) {
		this.name = name;
		this.totalSpent = 0;
	}

	public void addExpense(Expense ex) {
		this.expenses.add(ex);
		this.totalSpent += ex.amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}

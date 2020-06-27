//*******************************************************************
//  FoodExpense
//
// Description: Default ExpenseType with additional Metadata attribute
//*******************************************************************

import java.util.Date;

public class FoodExpense extends Expense {
	boolean isHealthy;

	public FoodExpense(boolean isHealthy, double amount, Date date, String category) {
		super(amount, date, category);
		this.isHealthy = isHealthy;
		this.identifier = "Food";
	}

	public FoodExpense(GenericExpense exp) {
		super(exp.amount, exp.date, exp.category);
		this.isHealthy = exp.genericBool;
		this.identifier = "Food";
		this.merchant = exp.merchant;
	}
}

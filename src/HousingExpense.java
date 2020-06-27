//*******************************************************************
//  HousingExpense
//
// Description: Default ExpenseType with additional Metadata attribute
//*******************************************************************

import java.util.Date;

public class HousingExpense extends Expense {
	boolean isVariable;

	public HousingExpense(boolean isVariable, double amount, Date date, String category) {
		super(amount, date, category);
		this.isVariable = isVariable;
		this.identifier = "Housing";
	}

	public HousingExpense(GenericExpense exp) {
		super(exp.amount, exp.date, exp.category);
		this.isVariable = exp.genericBool;
		this.identifier = "Housing";
		this.merchant = exp.merchant;
	}
}

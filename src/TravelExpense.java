//*******************************************************************
//  TravelExpense
//
// Description: Default ExpenseType with additional Metadata attribute
//*******************************************************************

import java.util.Date;

public class TravelExpense extends Expense {
	boolean isEcoFriendly;

	public TravelExpense(boolean isEcoFriendly, double amount, Date date, String category) {
		super(amount, date, category);
		this.isEcoFriendly = isEcoFriendly;
		this.identifier = "Travel";
	}

	public TravelExpense(GenericExpense exp) {
		super(exp.amount, exp.date, exp.category);
		this.isEcoFriendly = exp.genericBool;
		this.identifier = "Travel";
		this.merchant = exp.merchant;
	}

}

//*******************************************************************
// ExpenseException
//
// Description: Represents a caught exception, constructors for either importing errors or categorization errors. 
//*******************************************************************

import java.util.Date;

public class ExpenseException extends Exception {
	public Date date;
	public Double amount;
	public String category;
	public String identifier;
	public String[] expenseInfo;
	public String errorMessage;

	public ExpenseException(double amount, Date date, String category, String identifier, String message) {
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.identifier = identifier;
		this.errorMessage = message;
	}

	// If there is incomplete information to initialize the expense, the raw data is
	// logged in the expense
	// info attribute, as well as the error
	public ExpenseException(String[] expenseInfo, String errorMessage) {
		this.expenseInfo = expenseInfo;
		this.errorMessage = errorMessage;
	}

}

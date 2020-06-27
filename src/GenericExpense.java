//*******************************************************************
//  GenericExpense
//
// Description: Expense type that does not belong to a default category. Contains generic metadata attribute that can be set. 
//*******************************************************************

import java.util.Date;

public class GenericExpense extends Expense {


	public boolean genericBool;

	public GenericExpense(boolean genericBool, double amount, Date date, String category, String identifier, String merchant) {		
		super(amount, date, category, identifier, merchant);
		this.genericBool = genericBool;
		System.out.println("initializing expense from merchant " + merchant);
	}

	public GenericExpense(double amount, Date date, String category, String identifier, String merchant) {
		super(amount, date, category, identifier, merchant);
		System.out.println("initializing expense from merchant " + merchant);
	}
	
}

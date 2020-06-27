
//*******************************************************************
//  Exception Log
//
// Description: Container for all Exceptions resulting from hte budget.
//              Separates the expenses into Expense and Others. 
//*******************************************************************
import java.util.ArrayList;

public class ExceptionLog {
	ArrayList<Exception> exceptions = new ArrayList<Exception>();
	ArrayList<ExpenseException> expenseExceptions = new ArrayList<ExpenseException>();

	public void addException(Exception ex) {
		if (ex instanceof ExpenseException) {
			this.expenseExceptions.add((ExpenseException) ex);
		} else {
			this.exceptions.add(ex);
		}
	}

}

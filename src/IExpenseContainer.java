//*******************************************************************
//  FoodExpense
//
// Description: Interface for any object that contains expenses in it or its subclasses. 
//*******************************************************************
public interface IExpenseContainer {

	public void addExpense(Expense ex) throws ExpenseException;

}

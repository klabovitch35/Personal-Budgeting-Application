import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BudgetAddExceptionTest {

	@Test
	void testHousingExpense() {
		Budget budget = new Budget(300.00, 100.00, 100.00, 100.00, "Test Budget");
		try {
			budget.addExpense(new HousingExpense(true, 50.00, new Date(), "Rent/Mortgage"));
		} catch (ExpenseException ex) {
			// TODO Auto-generated catch block
			budget.addException(ex);
		}
		ArrayList<Expense> housingExpenses = budget.customEnvelopes.get("Housing").categories
				.get("Rent/Mortgage").expenses;
		assertEquals(housingExpenses.size(), 1);
	}

	@Test
	void testCastExpense() {
		Budget budget = new Budget(300.00, 100.00, 100.00, 100.00, "Test Budget");
		try {
			budget.addExpense(new GenericExpense(true, 50.00, new Date(), "Rent/Mortgage", "Housing", "Starbucks"));
		} catch (ExpenseException ex) {
			// TODO Auto-generated catch block
			budget.addException(ex);
		}
		ArrayList<Expense> housingExpenses = budget.customEnvelopes.get("Housing").categories
				.get("Rent/Mortgage").expenses;
		assertEquals(housingExpenses.size(), 1);
	}

	@Test
	void testAddException() {
		Budget budget = new Budget(300.00, 100.00, 100.00, 100.00, "Test Budget");
		try {
			budget.addExpense(new GenericExpense(true, 50.00, new Date(), "Rent/Mortgage", "sadf", "Starbucks"));
		} catch (ExpenseException ex) {
			// TODO Auto-generated catch block
			budget.addException(ex);
		}
		assertEquals(budget.log.expenseExceptions.size(), 2);
	}
}

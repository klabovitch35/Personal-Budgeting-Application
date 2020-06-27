import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class testAddExpenseAndPrintBudget {

	public static void main(String[] args) throws ExpenseException {
		Budget budget = new Budget(300.00, 100.00, 100.00, 100.00, "Test Budget");
		int successfulExpenses = 0;
		File file = new File("/Users/krislabovitch/Documents/test/METCS622termProject/TestExpenses.txt");
		try {
			Scanner infile = new Scanner(file);
			while (infile.hasNextLine()) {
				String[] expenseInfo = infile.nextLine().split(" ");
				// initialize strings from input file and convert to correct data type before
				// adding as Expense
				try {
					budget.addExpense(new GenericExpense(Boolean.valueOf(expenseInfo[0]),
							Double.parseDouble(expenseInfo[1]), new Date(Integer.parseInt(expenseInfo[2]),
									Integer.parseInt(expenseInfo[3]), Integer.parseInt(expenseInfo[4])),
							expenseInfo[5], expenseInfo[6], expenseInfo[7]));
					successfulExpenses++;
				} catch (Exception ex) {
					// if something is missing, log raw data for auditing
					ExpenseException exp = new ExpenseException(expenseInfo, ex.getMessage());
					budget.addException(ex);
				}
			}

			System.out.println(successfulExpenses + " imported Successfully");
			System.out.println(budget.getFailedExpenses());
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			budget.addException(ex);
		}
//		ExpenseItem<HousingExpense> hex = new ExpenseItem<HousingExpense>();
//		hex.set(new HousingExpense(true, 50.00, new Date(), "Rent/Mortgage"));
//		ExpenseItem<FoodExpense> fex = new ExpenseItem<FoodExpense>();
//		fex.set(new FoodExpense(true, 50.00, new Date(), "Restaurant"));
//		ExpenseItem<TravelExpense> tex = new ExpenseItem<TravelExpense>();
//		tex.set(new TravelExpense(true, 50.00, new Date(), "RideShare"));
//		budget.addExpense(fex);
//		budget.addExpense(hex);
//		budget.addExpense(tex);

		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter("output.txt");
		} catch (FileNotFoundException fnf) {
			budget.addException(fnf);
		}
		budget.writeToOutputFile(outFile);
		outFile.close();
		budget.printBudgetOutline();
	}

}

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//*******************************************************************
//Budget
//
//Description: This class serves as the container for all of the expense information in the budget, and contains functionality to output it in the correct format
//*******************************************************************

public class Budget<T extends Expense> implements IExpenseContainer {
	public String budgetName;
	public Float totalAmount;
	public Map<String, Envelope> customEnvelopes;
	public boolean isDefault;
	ExceptionLog log = new ExceptionLog();

	// if a non default budget is created, the user will have to define their own
	// envelopes
	public Budget(float totalAmount, String budgetName) {
		this.budgetName = budgetName;
		this.isDefault = false;
		this.customEnvelopes = new HashMap<String, Envelope>();
	}

	// defines a budget with the default envelopes set up
	public Budget(double totalAmount, double foodBudget, double housingBudget, double travelBudget, String budgetName) {
		this.budgetName = budgetName;
		this.isDefault = true;
		this.customEnvelopes = new HashMap<String, Envelope>();
		this.customEnvelopes.put("Food", new FoodEnvelope(foodBudget));
		this.customEnvelopes.put("Housing", new HousingEnvelope(housingBudget));
		this.customEnvelopes.put("Travel", new TravelEnvelope(travelBudget));
	}

	// adds an expense which is filtered to fall under the correct category
	public void addExpense(Expense gex) throws ExpenseException {
		Expense ex;
		if (gex instanceof GenericExpense) {
			System.out.println("general mer");
			
			ex = castExpense((GenericExpense) gex);
		} else {
			ex = gex;
		}
		System.out.println("addingto envelopes");
		if (ex instanceof FoodExpense) {
			System.out.println("Add to food envelope");
			Set<String> foodCategories = this.customEnvelopes.get("Food").getCategoryNames();
			if (!foodCategories.contains(ex.getCategory())) {
				ExpenseException exp = new ExpenseException(ex.amount, ex.date, ex.category, ex.identifier,
						"Envelope Category didn't exist");
				this.addException(exp);
				throw exp;
			}
			this.customEnvelopes.get("Food").addExpense(ex);
		} else if (ex instanceof TravelExpense) {
			System.out.println("Add to Travel envelope");
			Set<String> travelCategories = this.customEnvelopes.get("Travel").getCategoryNames();
			if (!travelCategories.contains(ex.getCategory())) {
				ExpenseException exp = new ExpenseException(ex.amount, ex.date, ex.category, ex.identifier,
						"Envelope Category didn't exist");
				this.addException(exp);
				throw exp;

			}
			System.out.println("merchant name " + ex.merchant);
			this.customEnvelopes.get("Travel").addExpense(ex);
		} else if (ex instanceof HousingExpense) {
			System.out.println("Add to housing envelope");
			Set<String> housingCategories = this.customEnvelopes.get("Housing").getCategoryNames();
			if (!housingCategories.contains(ex.getCategory())) {
				ExpenseException exp = new ExpenseException(ex.amount, ex.date, ex.category, ex.identifier,
						"Envelope Category didn't exist");
				this.addException(exp);
				throw exp;
			}
			System.out.println("adding housing expense");
			this.customEnvelopes.get("Housing").addExpense(ex);
		} else {
			System.out.println("adding generic expense " + gex.getIdentifier() + " " + gex.merchant);
			if (this.customEnvelopes.containsKey(gex.getIdentifier())) {
				this.customEnvelopes.get(gex.getIdentifier()).addExpense(ex);
			} else {
				// Submit new exception if the imported Expense doesn't have an appropriate
				// envelope
				System.out.println("That envelope doesn't exist right now");
				ExpenseException exp = new ExpenseException(gex.amount, gex.date, gex.category, gex.identifier,
						"Envelope Name didn't exist");
				this.addException(exp);
				throw exp;
			}
		}
	}
	
	public void addExpense (ExpenseItem<T> t) {
		System.out.println(t.getIdentifier());
		Envelope env = this.customEnvelopes.get(t.getIdentifier());
		env.addExpenseItem(t);
	}

	public Expense castExpense(GenericExpense ex) {
		System.out.println("casting expense");
		// if bool is not set then it is not a default expense type, then a generic
		// expense is returned
		try {
			System.out.println(ex.getIdentifier());
			String iden = ex.identifier;
			System.out.println("generic bool " + ex.genericBool);
			boolean boolValue = ex.genericBool;
			if (ex.getIdentifier().equals("Food")) {
				System.out.println("casting as food");
				FoodExpense fex = new FoodExpense(ex);
				fex.isHealthy = boolValue;
				return fex;
			} else if (ex.getIdentifier().equals("Travel")) {
				System.out.println("casting as travel");
				TravelExpense tex = new TravelExpense(ex);
				tex.isEcoFriendly = boolValue;
				return tex;
			} else if (ex.getIdentifier().equals("Housing")) {
				System.out.println("casting as housing");
				HousingExpense hex = new HousingExpense(ex);
				return hex;

			}
			return ex;
		} catch (NullPointerException npex) {
			this.addException(npex);
			return ex;
		}
	}

	// For the illustrative output in assignment one
	public void printBudgetOutline() {
		System.out.println(this.budgetName);
		System.out.println("Envelopes");
		for (Envelope env : this.customEnvelopes.values()) {
			System.out.println("\t" + env.getName() + ": " + env.printEnvelope());
			System.out.println("\tCategories");
			for (EnvelopeCategory envCat : env.getCategories()) {
				System.out.println("\t\t" + envCat.getName());
				for (Expense exp : envCat.expenses) {
					System.out.println("\t\t\t" + exp.printExpense());
				}
			}
		}

	}

	public void writeToOutputFile(PrintWriter pw) {
		pw.println(this.budgetName);
		pw.println("Envelopes");
		for (Envelope env : this.customEnvelopes.values()) {
			pw.println("\t" + env.getName() + ": " + env.printEnvelope());
			pw.println("\tCategories");
			for (EnvelopeCategory envCat : env.getCategories()) {
				pw.println("\t\t" + envCat.getName());
				for (Expense exp : envCat.expenses) {
					pw.println("\t\t\t" + exp.printExpense());
				}
			}
		}
	}

	public void addException(Exception ex) {
		this.log.addException(ex);
	}

	public String getFailedExpenses() {
		int failedExpenseCount = this.log.expenseExceptions.size();
		return failedExpenseCount + " expenses failed to insert";

	}

}

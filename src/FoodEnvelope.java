//*******************************************************************
//  FoodEnvelope
//
// Description: Default envelope with categories and other metadata preset
//*******************************************************************
public class FoodEnvelope extends Envelope implements IExpenseContainer, IDefaultEnvelope {
	double unhealthyFoodAmount;
	double healthyFoodAmount;

	public FoodEnvelope(double targetAmount) {
		super(targetAmount, "Food");
		this.addCategories();
		this.addAttributes();
	}

	@Override
	public void addExpense(Expense ex) {
		if (ex instanceof FoodExpense) {
			FoodExpense fex = (FoodExpense) ex;
			if (fex.isHealthy) {
				this.increaseAttributeValue("healthyFoodAmount",fex.amount);
			} else {
				this.increaseAttributeValue("unhealthyFoodAmount",fex.amount);
			}
			this.addToCategory(fex);
			this.adjustEnvelope(fex);
		}

	}


	public void addCategories() {
		this.categories.put("Groceries", new EnvelopeCategory("Groceries"));
		this.categories.put("Restaurant", new EnvelopeCategory("Restaurant"));
		this.categories.put("Delivery", new EnvelopeCategory("Delivery"));
		this.categories.put("Coffee", new EnvelopeCategory("Coffee"));
	}
	
	public void addAttributes() {
		this.customAttributeNameToAmount.put("unhealthyFoodAmount", 0.0);
		this.customAttributeNameToAmount.put("healthyFoodAmount", 0.0);
	}

}

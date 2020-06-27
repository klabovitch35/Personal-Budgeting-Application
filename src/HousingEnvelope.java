//*******************************************************************
//  HousingEnvelope
//
// Description: Default envelope with categories and other metadata preset
//*******************************************************************

public class HousingEnvelope extends Envelope implements IExpenseContainer, IDefaultEnvelope {
	public double fixedAmount;
	public double variableAmount;

	public HousingEnvelope(double targetAmount) {
		super(targetAmount, "Housing");
		this.addCategories();
		this.addAttributes();
	}

	@Override
	public void addExpense(Expense ex) {
		if (ex instanceof HousingExpense) {
			HousingExpense hex = (HousingExpense) ex;
			if (hex.isVariable) {
				this.increaseAttributeValue("variableAmount",hex.amount);
			} else {
				this.increaseAttributeValue("fixedAmount",hex.amount);
			}
			this.addToCategory(hex);
			this.adjustEnvelope(hex);
		}

	}


	public void addCategories() {
		this.categories.put("Rent/Mortgage", new EnvelopeCategory("Rent/Mortgage"));
		this.categories.put("Utilties", new EnvelopeCategory("Utilties"));
		this.categories.put("Maintenance", new EnvelopeCategory("Maintenance"));
		this.categories.put("Supplies", new EnvelopeCategory("Supplies"));
	}
	//Preset attributes
	public void addAttributes() {
		this.customAttributeNameToAmount.put("ecoFriendlyAmount", 0.0);
		this.customAttributeNameToAmount.put("nonEcoFriendlyAmount", 0.0);
	}

}

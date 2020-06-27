//*******************************************************************
//  TravelEnvelope
//
// Description: Default envelope with categories and other metadata preset
//*******************************************************************
public class TravelEnvelope extends Envelope implements IExpenseContainer, IDefaultEnvelope {
	public double ecoFriendlyAmount;
	public double nonEcoFriendlyAmount;

	public TravelEnvelope(double targetAmount) {
		super(targetAmount, "Travel");
		this.addCategories();
	}

	@Override
	public void addExpense(Expense ex) {
		if (ex instanceof TravelExpense) {
			TravelExpense tex = (TravelExpense) ex;
			if (tex.isEcoFriendly) {
				this.increaseAttributeValue("ecoFriendlyAmount",tex.amount);
			} else {
				this.increaseAttributeValue("nonEcoFriendlyAmount",tex.amount);
			}
			this.addToCategory(tex);
			this.adjustEnvelope(tex);
		}

	}



	public void addCategories() {
		this.categories.put("RideShare", new EnvelopeCategory("RideShare"));
		this.categories.put("PublicTransit", new EnvelopeCategory("PublicTransit"));
		this.categories.put("Gas", new EnvelopeCategory("Gas"));
		this.categories.put("CarMaintenance", new EnvelopeCategory("CarMaintenance"));
		this.categories.put("Flights", new EnvelopeCategory("Flights"));
	}
	
	public void addAttributes() {
		this.customAttributeNameToAmount.put("ecoFriendlyAmount", 0.0);
		this.customAttributeNameToAmount.put("nonEcoFriendlyAmount", 0.0);
	}

}

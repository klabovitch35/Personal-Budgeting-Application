import java.util.HashMap;
import java.util.Map;

public class GenericEnvelope<T> extends Envelope implements IExpenseContainer{
	private T t;
	public void set(T t) { this.t = t; }
    public T get() { return t; }

	public GenericEnvelope(double targetBalance, String name) {
		// TODO Auto-generated constructor stub
		super(targetBalance, name);
	}
	
	public GenericEnvelope(double targetBalance, String name, String attribute1, String attribute2) {
		// TODO Auto-generated constructor stub
		super(targetBalance, name);
		this.customAttributeNameToAmount.put(attribute1, 0.0);
		this.customAttributeNameToAmount.put(attribute2, 0.0);
	}
	
	public void addExpense(Expense ex) {
		this.addToCategory(ex, ex.getCategory());
		this.increaseAttributeValue(ex.attribute, ex.amount);
		
		
	}

	public void addCategory(String name) {
		this.categories.put(name, new EnvelopeCategory(name));
	}
	

}


public class ExpenseItem<T extends Expense> {
	private T t;
	public void set(T t) {this.t = t;}
	public T get() { return this.t;}
	public String getIdentifier() {
		return this.t.getIdentifier();
	}
	public String getCategory() {
		return this.t.getCategory();
	}
	public String getAttribute() {
		return this.t.getAttribute();
	}
}

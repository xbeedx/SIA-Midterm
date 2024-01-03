package objects;

public class Classe {

	private String ClassID;
	private String ClassName;
	private int PriceMultiplier;
	
	public Classe(String classID, String className, int priceMultiplier) {
		super();
		ClassID = classID;
		ClassName = className;
		PriceMultiplier = priceMultiplier;
	}

	public String getClassID() {
		return ClassID;
	}

	public void setClassID(String classID) {
		ClassID = classID;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public int getPriceMultiplier() {
		return PriceMultiplier;
	}

	public void setPriceMultiplier(int priceMultiplier) {
		PriceMultiplier = priceMultiplier;
	}
	
	
}

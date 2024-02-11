package data;

public enum numbers_{
	
	INTEGER_VALUE(Integer.class,1),
	DECIMAL_VALUE(Double.class,3.5),
	STRING_VALUE(String.class,"hellow world");
	
	public final Class<?> v;
	public final Object defaultValue;
	
	numbers_(Class<?> v, Object defaultValue ) {
		this.v = v;
		this.defaultValue = defaultValue;
	}
	
	public Object getDefaultValue() {
		return this.defaultValue;
	}
	
}

package comarch.mediation.ClientSide.model;

public enum PerceivedSeverity {
    INDETERMINATE(0),
    CRITICAL(1),
    MAJOR(2),
    MINOR(3),
    WARNING(4),
    CLEARED(5);
	
	private final int severity;
	
	PerceivedSeverity(int severity){
		this.severity = severity;
	}
	
	public String getName() {
		return this.name();
	}
	
	public int getValue() {
		return this.severity;
	}
}

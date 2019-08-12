package comarch.mediation.ClientSide.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

public class Alarm {
	private Long id;
	private Date eventTime;
	private int severity;
	private String probablyCause;
	private String managedObject;
	
	   public Alarm(
	            @JsonProperty("id") Long id,
	            @JsonProperty("eventTime") Date eventTime,
	            @JsonProperty("severity") int severity,
	            @JsonProperty("probablyCause") String probablyCause,
	            @JsonProperty("managedObject") String managedObject) {
	        this.id = id;
	        this.eventTime = eventTime;
	        this.severity = severity;
	        this.probablyCause = probablyCause;
	        this.managedObject = managedObject;
	    }
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getEventTime() {
		return eventTime;
	}
	
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	public int getSeverity() {
		return severity;
	}
	
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	public String getProbableCause() {
		return probablyCause;
	}
	
	public void setProbableCause(String probableCause) {
		this.probablyCause = probableCause;
	}
	
	public String getManagedObject() {
		return managedObject;
	}
	
	public void setManagedObject(String managedObject) {
		this.managedObject = managedObject;
	}
	
	private static final TypeReference<Alarm> typeRef = new TypeReference<Alarm>() {};
    public static TypeReference<Alarm> typeRef() {
        return typeRef;
    }
    
    private static final TypeReference<List<Alarm>> listTypeRef = new TypeReference<List<Alarm>>() {};
    public static TypeReference<List<Alarm>> listTypeRef() {
        return listTypeRef;
    }

	@Override
	public String toString() {
		return "[id=" + id + ", eventTime=" + eventTime + ", severity=" + severity + ", probablyCause="
				+ probablyCause + ", managedObject=" + managedObject + "]";
	}
}

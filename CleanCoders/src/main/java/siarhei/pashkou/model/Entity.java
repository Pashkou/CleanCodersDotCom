package siarhei.pashkou.model;

public class Entity implements Cloneable {
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean equals(Object o){
		Entity entity = (Entity)o;
		if((id != null) && (id.equalsIgnoreCase(entity.getId())))
			return true;
		return false;
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
}

package siarhei.pashkou.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import siarhei.pashkou.model.Entity;


public class GatewayUtilities<T extends Entity> {
	HashMap<String, T> entities;
	
	public GatewayUtilities(){ entities = new HashMap<String, T>(); }
	
	@SuppressWarnings("unchecked")
	public T save(T entity){
		if(entity.getId() == null)
			entity.setId(UUID.randomUUID().toString());
		T clonedEntity = (T)entity.clone();
		entities.put(clonedEntity.getId(), clonedEntity);
		return entity;
	}
	
	public List<T> findAll(){
		List<T> clonedEntities = new ArrayList<T>();
		for(T entity: entities.values()){
			clonedEntities.add((T)entity.clone()); 
		}
		return clonedEntities;
	}
	
	public void delete(T entity){
		entities.remove(entity.getId());
	}
}

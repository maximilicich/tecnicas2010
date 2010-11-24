package mat7510.smartBuilding.dao;

import java.util.Set;

import mat7510.smartBuilding.model.Rule;
import mat7510.smartBuilding.model.SmartBuildingException;

/**
 * DAO para los Rules:
 * Es el punto de comunicacion con la persistencia de las class de Dominio

 * @author Grupo 10
 *
 */
public interface RuleDAO {
	
	/**
	 * 
	 * Vuelca todas las Rules en el Repositorio
	 * Reemplaza todas las existentes por este conjunto
	 * 
	 * La unicidad de ID esta asegurada por set un SET
	 * (y el equals de las Rules asegura la igualdad por IDs
	 * 
	 * @param rules
	 * @throws SmartBuildingException 
	 */
	public void setRules(Set<Rule> rules) throws SmartBuildingException;	



	/**
	 * 
	 * @return El conjunto de reglas configuradas. Set vacio si no hay reglas configuradas
	 * @throws SmartBuildingException
	 */
	public Set<Rule> getRules() throws SmartBuildingException;
		
}
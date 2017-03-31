package navigation;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.cache.Caching;

import csv.CSVConfig;
import csv.CSVException;
import data.Data;
import data.DataModel;
import data.Deserializer;
import data.Serializer;
import models.*;

@SuppressWarnings("serial")
class AssocFrame extends AssociationJFrame<Employee, Competence> {
	
	public AssocFrame(Data data, Employee entity, Class<Competence> nEntityClass) throws HeadlessException {
		super(data, entity, nEntityClass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	ArrayList<Competence> getAssociatedEntities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	void AssociateEntity(Competence nEntity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	void DissociateEntity(Competence nEntity) {
		// TODO Auto-generated method stub
		
	}
	
}

public class Test {
	public static void main(String[] args) throws CSVException, InstantiationException, IllegalAccessException {
		CSVConfig config = new CSVConfig(
				Caching.getCachingProvider().getCacheManager(), DataModel.class, Deserializer.class, Serializer.class
		);
		Data data = new Data(config);
		AssocFrame frame = new AssocFrame(data, data.Employes().parID(1), Competence.class);
		frame.saveChanges();
	}
}

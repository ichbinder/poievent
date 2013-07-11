package de.htw_berlin.opentoken.model;

import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="PolygonPoiModel")
public class PolygonPoiModel extends PoiModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -249477407937258847L;
	@OneToMany(mappedBy="polygonPoi")
	private List<CoordinateModel> polygon;

	public PolygonPoiModel(){	
		super();
	}
	
	public PolygonPoiModel(String name, Set<TagModel> tags, UserModel createdBy, List<CoordinateModel> polygon){
		this.name =name;
		this.tags = tags;
		this.createdBy = createdBy;
		this.polygon = polygon;
	}
	
	public List<CoordinateModel> getPolygon() {
		return polygon;
	}

	public void setPolygon(List<CoordinateModel> polygon) {
		this.polygon = polygon;
	}	
}

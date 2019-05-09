package kr.co.oneul.vo;

import java.util.List;

public class MaterialVO {
	
	Material writeMaterial;
	List<MapVO> mapMaterial;
	
	public Material getWriteMaterial() {
		return writeMaterial;
	}
	public void setWriteMaterial(Material writeMaterial) {
		this.writeMaterial = writeMaterial;
	}
	public List<MapVO> getMapMaterial() {
		return mapMaterial;
	}
	public void setMapMaterial(List<MapVO> mapMaterial) {
		this.mapMaterial = mapMaterial;
	}
	
	@Override
	public String toString() {
		return "MaterialVO [writeMaterial=" + writeMaterial + ", mapMaterial=" + mapMaterial + "]";
	}
}

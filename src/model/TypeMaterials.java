package model;

import java.util.List;

public class TypeMaterials {

    private String typeName;
    private String typeNameJps;
    private String typeId;
    private String typeMate;
    private String typeQuantity;
    private List<Materials> mateList;



    public String getTypeNameJps() {
        return typeNameJps;
    }
    public void setTypeNameJps(String typeNameJps) {
        this.typeNameJps = typeNameJps;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeMate() {
        return typeMate;
    }
    public void setTypeMate(String typeMate) {
        this.typeMate = typeMate;
    }
    public String getTypeQuantity() {
        return typeQuantity;
    }
    public void setTypeQuantity(String typeQuantity) {
        this.typeQuantity = typeQuantity;
    }
    public List<Materials> getMateList() {
        return mateList;
    }
    public void setMateList(List<Materials> mateList) {
        this.mateList = mateList;
    }


}

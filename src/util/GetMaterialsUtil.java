package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.Materials;
import model.MaterialsType;
import model.TypeMaterials;

public class GetMaterialsUtil {
    public static void getMaterials(EntityManager em, HttpServletRequest request){

        List<TypeMaterials> typeMateList =new ArrayList<TypeMaterials>();
        String[] types=MaterialsType.TYPES;

        for(String type: types) {

            TypeMaterials typeMate = new TypeMaterials();

            typeMate.setTypeId(type+"_id");
            typeMate.setTypeName(type);
            typeMate.setTypeQuantity(type+"_quantity");
            typeMate.setTypeMate(type+"_materials");
            typeMate.setMateList(em.createNamedQuery("getAllMaterials", Materials.class)
                    .setParameter("type",type)
                    .getResultList());

            typeMateList.add(typeMate);
        }
        request.setAttribute("typeMate", typeMateList);
    }
}

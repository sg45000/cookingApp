package controller.recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.MaterialsType;
import model.Recipe_Materials;
import model.Recipes;
import util.DBUtil;
import util.GetMaterialsUtil;
import validators.RecipeValidator;

/**
 * Servlet implementation class RecipesCreateServlet
 */
@WebServlet("/recipes/create")
@MultipartConfig(maxFileSize = 1048576 )
public class RecipesCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String _token = getParamVal(request.getPart("_token"));
	    System.out.println(_token);
	    if(_token!=null && _token.equals(request.getSession().getId())) {
	        EntityManager em = DBUtil.createEM();
	        Boolean commit_flag = true;

   //材料以外の処理
	        Recipes r =new Recipes();
	        Collection<Part> parts = request.getParts();

	        String name=getParamVal(request.getPart("name"));
	        String how_to=getParamVal(request.getPart("how_to"));
            String time=getParamVal(request.getPart("time"));
            String how_many=getParamVal(request.getPart("how_many"));



            Timestamp currentTime =new Timestamp(System.currentTimeMillis());
	        r.setName(name);
	        r.setHow_to(how_to);
	        r.setTime(time);
	        r.setHow_many(how_many);
	        r.setCreated_at(currentTime);
	        r.setUpdated_at(currentTime);

	        Part part = request.getPart("file");
	        String fileName = extractFileName(part);
	        part.write(getServletContext().getRealPath("/recipes_image")+"/"+ fileName);
	        r.setImage_name(fileName);


	        em.getTransaction().begin();
            em.persist(r);




    //材料の処理
            String[] types=MaterialsType.TYPES;
            Integer recipe_id=(Integer)em.createNamedQuery("getLastRecipeId", Integer.class)
                    .getSingleResult();

            for(String type: types) {
                String[] ids=getParamArray(parts,type+"_id");
                String[] quantities=getParamArray(parts,type+"_quantity");
                List<String> quantityList =new ArrayList<String>();
                int count=0;
                if(ids!=null) {
                    for(String quantity :quantities) {
                        if(!quantity.equals("")) {
                            quantityList.add(quantity);
                            count++;
                        }
                    }
                    if(ids.length!=count) {
                        request.setAttribute("flush", "チェックを入れた材料には、量を指定してください。");
                        commit_flag=false;
                        break;
                    }else {

                        for(int i=0;i<ids.length;i++) {
                            Integer id_i = Integer.parseInt(ids[i]);
                            Double quantity = Double.valueOf(quantityList.get(i));
                            Recipe_Materials rm = new Recipe_Materials();
                            rm.setMaterial_id(id_i);
                            rm.setRecipe_id(recipe_id);
                            rm.setQuantity(quantity);
                            em.persist(rm);
                        }
                    }
                }
            }
            List<String> errors =RecipeValidator.validate(name,how_to);

     //コミット判断
            if(errors.size()>0 || commit_flag==false) {
                request.setAttribute("errors", errors);
                request.setAttribute("name", name);
                request.setAttribute("how_to", how_to);
                request.setAttribute("time", time);
                request.setAttribute("_token", request.getSession().getId());
                GetMaterialsUtil.getMaterials(em, request);
                em.getTransaction().rollback();
                em.close();
                RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/views/recipes/new.jsp");
                rd.forward(request, response);
            }else {
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "レシピの登録が完了しました。");

            }
	    }

	    response.sendRedirect(request.getContextPath()+"/top/index");

	}

	// INPUTデータの取得
    private String getParamVal(Part part) {
        String pp=part.getContentType();
        if (pp == null) { // INPUTの文字列データはContentTypeがnull
            try {
                InputStream inputStream = part.getInputStream();
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
                return bufReader.lines().collect(Collectors.joining());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

	private String extractFileName(Part part) {
        String[] splitedHeader = part.getHeader("Content-Disposition").split(";");

        String fileName = null;
        for(String item: splitedHeader) {
            if(item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf('"')).replaceAll("\"", "");
            }
        }
        return fileName;
      }

	// 同名前のパラメータのデータ配列を取得
    private String[] getParamArray(Collection<Part> parts, String name) {
        List<String> params = new ArrayList<String>();

        for (Part part : parts) {
            if (name.equals(part.getName())) {
                params.add(getParamVal(part));
            }
        }

        return params.toArray(new String[params.size()]);
    }

}

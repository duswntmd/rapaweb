package com.test.sku.pet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PetService {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public PetService() {}

    public PetService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String process() {
        String cmd = request.getParameter("cmd");
        String viewPath = null;

        if (cmd == null) {
            return "/pet/pet_index.jsp";
        }

        switch (cmd) {
            case "list":
                handleListPets();
                viewPath = "/pet/petList.jsp";
                break;
            case "detail":
                handlePetDetail();
                viewPath = "/pet/petDetail.jsp";
                break;
            case "inputForm":
                viewPath = "/pet/petInput.jsp";
                break;
            case "add":
                handleAddPet();
                break;
            case "editForm":
                handleEditForm();
                viewPath = "/pet/petEdit.jsp";
                break;
            case "edit":
                handleEditPet();
                break;
            case "delete":
                handleDeletePet();
                break;
            case "search":
                handleSearchPets();
                viewPath = "/pet/petList.jsp";
                break;
            default:
                viewPath = "/pet/pet_index.jsp";
        }

        return viewPath;
    }

    private void handleListPets() {
        PetDAO dao = new PetDAO();
        List<PetVO> list = dao.getAllPets();
        request.setAttribute("list", list);
    }

    private void handlePetDetail() {
        int no = Integer.parseInt(request.getParameter("no"));
        PetDAO dao = new PetDAO();
        PetVO pet = dao.getPetByNo(no);
        request.setAttribute("pet", pet);
    }

    private void handleAddPet() {
        PetVO pet = new PetVO();
        pet.setNo(Integer.parseInt(request.getParameter("no")));  // 수동으로 NO 설정
        pet.setName(request.getParameter("name"));
        pet.setOrigin(request.getParameter("origin"));
        pet.setWeight(Double.parseDouble(request.getParameter("weight")));
        pet.setBirth(java.sql.Date.valueOf(request.getParameter("birth")));
        pet.setPrice(Integer.parseInt(request.getParameter("price")));
        pet.setPic(request.getParameter("pic"));
        
        PetDAO dao = new PetDAO();
        boolean added = dao.insertPet(pet);
        sendJSON("added", String.valueOf(added));
    }

    private void handleEditForm() {
        int no = Integer.parseInt(request.getParameter("no"));
        PetDAO dao = new PetDAO();
        PetVO pet = dao.getPetByNo(no);
        request.setAttribute("pet", pet);
    }

    private void handleEditPet() {
        PetVO pet = new PetVO();
        pet.setNo(Integer.parseInt(request.getParameter("no")));
        pet.setName(request.getParameter("name"));
        pet.setOrigin(request.getParameter("origin"));
        pet.setWeight(Double.parseDouble(request.getParameter("weight")));
        pet.setBirth(java.sql.Date.valueOf(request.getParameter("birth")));
        pet.setPrice(Integer.parseInt(request.getParameter("price")));
        pet.setPic(request.getParameter("pic"));

        PetDAO dao = new PetDAO();
        boolean updated = dao.updatePet(pet);
        sendJSON("updated", String.valueOf(updated));
    }

    private void handleDeletePet() {
        int no = Integer.parseInt(request.getParameter("no"));
        PetDAO dao = new PetDAO();
        boolean deleted = dao.deletePet(no);
        sendJSON("deleted", String.valueOf(deleted));
    }
    
    private void handleSearchPets() {
        String category = request.getParameter("category");
        String keyword = request.getParameter("keyword");

        PetDAO dao = new PetDAO();
        List<PetVO> list = dao.searchPets(category, keyword);

        // list를 JSONArray로 변환
        JSONArray jsonArray = new JSONArray();
        for (PetVO pet : list) {
            jsonArray.add(pet.toJSON());
        }

        sendJSON("list", jsonArray);
    }

    private void sendJSON(String key, String value) {
        String json = String.format("{\"%s\":%s}", key, value);
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendJSON(String key, Object value) {
        JSONObject jsobj = new JSONObject();
        jsobj.put(key, value);
        String js = jsobj.toJSONString();
        try (PrintWriter out = response.getWriter()) {
            out.print(js);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendJSON(Map<String, Object> map) {
        JSONObject jsobj = new JSONObject(map);
        String js = jsobj.toJSONString();
        try (PrintWriter out = response.getWriter()) {
            out.print(js);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

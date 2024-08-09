package com.test.sku.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pet")
public class PetServlet extends HttpServlet 
{
   /* Pet 판매점 사이트를 가정한 웹사이트 제작
    * Class : PetServlet, PetService, PetVO, PetDAO
    * JSP : petInput, petList, petDetail, petEdit
    * DB Table : pet(no,name,origin,weight,birth,price,pic)
    * 정수 자료형 : no, price
    * 실수 자료형 : weight
    * 날짜 자료형 : birth
    * 문자열형   : name, origin(원산지), pic(사진)
    * 파라미터 없이 서블릿을 호출하면 pet_index.jsp 화면이 표시되도록 한다
    * 모든 요청에 대한 응답은 필수
    * 상세보기 화면에서는 pet의 사진도 표시되어야 한다
    * webapp/pet : 필요한 모든 jsp 파일 저장
    * webapp/img/pet : 모든 이미지 파일 저장
    * 기능 구현 : 추가, 목록, 검색, 수정, 삭제
    */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        
        PetService service = new PetService(request, response);
        String viewPath = new PetService(request, response).process();
        
        if(viewPath != null) {
            getServletContext().getRequestDispatcher(viewPath).forward(request, response);
        }
    }
}
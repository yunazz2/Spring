package controller;

import java.util.Map;

/**
 * MVC에서 Model, View 정보를 관리하는 객체
 * @author LEEYUNA
 *
 */

public class ModelView {
	
	Map<String, Object> model; // 어떤 이름으로 어떤 데이터를 담을지
	String view;
	boolean redirect = false;	// redirect 여부

	
	// 기본 생성자
	public ModelView() {
		
	}

	
	// 생성자
	public ModelView(Map<String, Object> model, String view) {
		this.model = model;
		this.view = view;
	}
	
	
	// getter, setter - getter만 자동 생성했고 setter는 직접 타이핑 함..
	// redirect는 후에 추가하고 자동 생성 함
	public Map<String, Object> getModel() {
		return model;
	}
	
	public void addModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getView() {
		return view;
	}
	
	public void setView(String view) {
		this.view = view;
	}


	public boolean isRedirect() {
		return redirect;
	}


	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	

}

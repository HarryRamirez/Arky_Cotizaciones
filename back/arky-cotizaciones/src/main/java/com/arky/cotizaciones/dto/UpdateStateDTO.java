package com.arky.cotizaciones.dto;



public class UpdateStateDTO {


    private Integer stateId;


    public UpdateStateDTO() {

    }

	public UpdateStateDTO(Integer stateId) {
		super();
		this.stateId = stateId;
	}


	public Integer getStateId() {
		return stateId;
	}


	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}



}

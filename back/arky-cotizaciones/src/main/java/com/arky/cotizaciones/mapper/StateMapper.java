package com.arky.cotizaciones.mapper;

import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.StateRequestDTO;
import com.arky.cotizaciones.dto.StateResponseDTO;
import com.arky.cotizaciones.model.State;

@Component
public class StateMapper {

    public StateResponseDTO toDto(State state){
        StateResponseDTO dto = new StateResponseDTO();
        dto.setStateId(state.getStateId());
        dto.setName(state.getName());

        return dto;
    }

    

    public State toEntity(StateRequestDTO dto){
        State state = new State();
        state.setName(dto.getName());

        return state;
    }
}

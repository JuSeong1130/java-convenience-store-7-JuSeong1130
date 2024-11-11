package store.controller.dto;

import java.util.List;

public record OrderStateDTO(String state, List<StateContextDTO> stateContexts ) {
}

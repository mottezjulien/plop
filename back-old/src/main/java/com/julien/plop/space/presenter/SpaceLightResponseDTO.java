package com.julien.plop.board.presenter;

import java.util.List;

public record SpaceLightResponseDTO(String id, String lat, List<SpacePointDTO> points) {

}

package com.julien.plop.space;

import java.util.List;

public record SpaceLightResponseDTO(String id, String lat, List<SpacePointDTO> points) {

}

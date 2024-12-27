package com.julien.plop.board.presenter;

import com.julien.plop.board.persistence.SpacePointEntity;
import com.julien.plop.board.persistence.SpacePointRepository;
import com.julien.plop.board.persistence.SpaceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

    private final SpaceRepository spaceRepository;
    private final SpacePointRepository spacePointRepository;

    public SpaceController(SpaceRepository spaceRepository, SpacePointRepository spacePointRepository) {
        this.spaceRepository = spaceRepository;
        this.spacePointRepository = spacePointRepository;
    }

    @PostMapping("/")
    public void save(@RequestBody SpaceSaveRequestDTO request) {
        Optional<SpaceEntity> opt = spaceRepository.findByLabel(request.label());
        SpaceEntity entity = opt.orElseGet(() -> {
            SpaceEntity newEntity = new SpaceEntity();
            newEntity.setLabel(request.label());
            return spaceRepository.save(newEntity);
        });
        SpacePointEntity pointEntity = new SpacePointEntity();
        pointEntity.setLat(request.coord().lat());
        pointEntity.setLng(request.coord().lng());
        spacePointRepository.save(pointEntity);
        entity.getPoints().add(pointEntity);
        spaceRepository.save(entity);
    }

    @GetMapping("/")
    public ResponseEntity<RestResponse> findAll() {
        List<SpaceLightResponseDTO> dto = spaceRepository.findAll()
                .stream()
                .map(this::toDTOLightSpace)
                .toList();
        return ResponseEntity.ok(new RestResponse.Success<>(dto));
    }

    private SpaceLightResponseDTO toDTOLightSpace(SpaceEntity entity) {
        return new SpaceLightResponseDTO(entity.getId(), entity.getLabel(), toDTOPoint(entity.getPoints()));
    }

    private List<SpacePointDTO> toDTOPoint(Set<SpacePointEntity> points) {
        return points.stream()
                .map(entity -> new SpacePointDTO(entity.getLat(), entity.getLng()))
                .toList();
    }

    public sealed interface RestResponse permits RestResponse.Success, RestResponse.Failure  {

        record Success<DTO>(DTO data) implements RestResponse {

        }
        record Failure() implements RestResponse {

        }

    }


}

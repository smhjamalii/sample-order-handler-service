package ir.encoding.order.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.encoding.order.service.interfaces.HighSeasonService;
import ir.encoding.order.validation.groups.Edit;
import ir.encoding.order.view.dto.product.HighSeasonDTO;
import ir.encoding.order.view.dto.results.CreateResult;
import ir.encoding.order.view.dto.results.DeleteResult;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/highseasons", produces = OrderServiceMediaTypes.V1)
@Validated
public class HighSeasonResource {

	@Autowired
	private HighSeasonService highSeasonService;
	
	@PostMapping
	public ResponseEntity<EntityModel<CreateResult>> create(@RequestBody @Valid HighSeasonDTO highSeasonDTO) {
		highSeasonDTO = highSeasonService.save(highSeasonDTO);
		CreateResult result = new CreateResult(highSeasonDTO.getId());
		return ResponseEntity.ok(EntityModel.of(result, linkTo(methodOn(HighSeasonResource.class).edit(highSeasonDTO)).withSelfRel()));
	}
	
	@PutMapping
	public ResponseEntity<EntityModel<HighSeasonDTO>> edit(
			@RequestBody 
			@Valid
			@Validated(Edit.class)
			HighSeasonDTO highSeasonDTO) {
		highSeasonDTO = highSeasonService.edit(highSeasonDTO);				
		return ResponseEntity.ok(EntityModel.of(highSeasonDTO, linkTo(methodOn(HighSeasonResource.class).delete(highSeasonDTO.getId())).withSelfRel()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EntityModel<DeleteResult>> delete(@PathVariable("id") Long id){
		boolean result = highSeasonService.delete(id);
		DeleteResult deleteResult = new DeleteResult(result);
		return ResponseEntity.ok(EntityModel.of(deleteResult));
	}
	
}

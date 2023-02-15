package com.foodieblog.foodieblog.controllers;

import com.foodieblog.foodieblog.dtos.RoleGetDto;
import com.foodieblog.foodieblog.entities.Role;
import com.foodieblog.foodieblog.exceptionHandlers.RoleNotFoundException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.services.LogService;
import com.foodieblog.foodieblog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final MapStructMapper mapstructMapper;
    private final RoleService roleService;
    private final LogService logService;

    @Autowired
    public RoleController(MapStructMapper mapstructMapper, RoleService roleService, LogService logService) {
        this.mapstructMapper = mapstructMapper;
        this.roleService = roleService;
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<RoleGetDto>> getAllRoles() {
        return new ResponseEntity<List<RoleGetDto>>(
                mapstructMapper.roleGetAllDto(roleService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody RoleGetDto roleGetDto) {

        LogLevel logLevel = LogLevel.INFO;
        roleService.save(roleGetDto);
        logService.createLog(Role.class, logLevel);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleGetDto> getById(
            @PathVariable(value = "id") int id) throws RoleNotFoundException {
        return new ResponseEntity<>(
                mapstructMapper.roleGetDto(
                        roleService.findById(id).orElseThrow(() ->
                        new RoleNotFoundException("Role not found due to invalid role id: " + id))),
                        HttpStatus.OK
        );
    }
}
